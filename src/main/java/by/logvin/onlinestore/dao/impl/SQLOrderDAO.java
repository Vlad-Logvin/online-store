package by.logvin.onlinestore.dao.impl;

import by.logvin.onlinestore.bean.Order;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.dao.OrderDAO;
import by.logvin.onlinestore.dao.connection.ConnectionPool;
import by.logvin.onlinestore.dao.connection.ConnectionPoolException;
import by.logvin.onlinestore.dao.exception.DAOException;
import by.logvin.onlinestore.dao.impl.sqlparameter.SQLOrderParameter;
import by.logvin.onlinestore.dao.impl.sqlrequest.OrderSQLRequest;
import by.logvin.onlinestore.service.ServiceProvider;
import by.logvin.onlinestore.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

public class SQLOrderDAO implements OrderDAO {

    private final static Logger logger = Logger.getLogger(SQLOrderDAO.class);

    @Override
    public List<Order> takeUserOrders(int userID) throws DAOException {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        List<Order> orders;
        Map<Integer, List<Product>> products = new HashMap<>();
        Map<Integer, Integer> cards = new HashMap<>();
        Map<Integer, Timestamp> dateOfPurchases = new HashMap<>();
        Map<Integer, Double> grandTotal = new HashMap<>();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(OrderSQLRequest.SELECT_ALL_ORDERS_BY_USER_ID)
        ) {
            preparedStatement.setInt(1, userID);
            resultSet = preparedStatement.executeQuery();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
            fillOrderData(resultSet, products, grandTotal, cards, dateOfPurchases);
            orders = takeOrders(products, grandTotal, cards, dateOfPurchases);
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution");
            throw new DAOException("Error prepared statement updating or setting data", e);
        } catch (ServiceException e) {
            logger.error("ServiceException was thrown due to an error during getting product|card information by product|card id");
            throw new DAOException("Error product|card information by product|card id", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("Result set has been already closed", e);
            }
            if (connection != null) {
                removeConnection(connection);
            }
        }
        return orders;
    }

    @Override
    public boolean makeOrder(int userID, Map<Product, Integer> products, int cardID, Timestamp dateOfPurchase) throws DAOException {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        int numberOfUpdatedLines;
        try (
                PreparedStatement orderInsertingStatement = connection.prepareStatement(OrderSQLRequest.INSERT_ORDER);
                PreparedStatement orderSelectingStatement = connection.prepareStatement(OrderSQLRequest.SELECT_LAST_ORDER_ID);
                PreparedStatement productInsertingStatement = connection.prepareStatement(OrderSQLRequest.INSERT_PRODUCT_TO_ORDER)
        ) {
            orderInsertingStatement.setInt(1, userID);
            orderInsertingStatement.setDouble(2, getGrandTotal(products));
            orderInsertingStatement.setInt(3, cardID);
            orderInsertingStatement.setTimestamp(4, dateOfPurchase);
            numberOfUpdatedLines = orderInsertingStatement.executeUpdate();
            logger.info("Request (" + orderInsertingStatement.toString() + ") was completed");
            orderSelectingStatement.setInt(1, userID);
            resultSet = orderSelectingStatement.executeQuery();
            logger.info("Request (" + orderSelectingStatement.toString() + ") was completed");
            resultSet.next();
            int orderID = resultSet.getInt("o_id");
            productInsertingStatement.setInt(1, orderID);
            for (Product product : products.keySet()) {
                productInsertingStatement.setInt(2, product.getId());
                productInsertingStatement.setInt(3, products.get(product));
                numberOfUpdatedLines += productInsertingStatement.executeUpdate();
                logger.info("Request (" + productInsertingStatement.toString() + ") was completed");
            }
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution");
            throw new DAOException("Error prepared statement updating or setting data", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("Result set has been already closed", e);
            }
            if (connection != null) {
                removeConnection(connection);
            }
        }
        return numberOfUpdatedLines != 0;
    }

    private double getGrandTotal(Map<Product, Integer> products) {
        double grandTotal = 0.0;
        for (Product product : products.keySet()) {
            grandTotal += product.getPrice() * products.get(product);
        }
        return grandTotal;
    }

    @Override
    public List<Order> takeOrderLog() throws DAOException {
        Connection connection = getConnection();
        List<Order> orders;
        Map<Integer, List<Product>> products = new HashMap<>();
        Map<Integer, Integer> cards = new HashMap<>();
        Map<Integer, Timestamp> dateOfPurchases = new HashMap<>();
        Map<Integer, Double> grandTotal = new HashMap<>();
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(OrderSQLRequest.SELECT_ALL_ORDERS)
        ) {
            logger.info("Request (" + statement.toString() + ") was completed");
            fillOrderData(resultSet, products, grandTotal, cards, dateOfPurchases);
            orders = takeOrders(products, grandTotal, cards, dateOfPurchases);
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution");
            throw new DAOException("Error prepared statement updating", e);
        } catch (ServiceException e) {
            logger.error("ServiceException was thrown due to an error during getting product|card information by product|card id");
            throw new DAOException("Error product|card information by product|card id", e);
        } finally {
            if (connection != null) {
                removeConnection(connection);
            }
        }
        return orders;
    }

    private List<Order> takeOrders(Map<Integer, List<Product>> products,
                                   Map<Integer, Double> grandTotal,
                                   Map<Integer, Integer> cards,
                                   Map<Integer, Timestamp> dateOfPurchases) throws ServiceException {
        List<Order> orders = null;
        if (!products.isEmpty()) {
            orders = new ArrayList<>();
        }
        for (Integer orderID : products.keySet()) {
            orders.add(new Order(
                    orderID,
                    grandTotal.get(orderID),
                    ServiceProvider.getInstance().getCardService().takeCard(cards.get(orderID)),
                    dateOfPurchases.get(orderID),
                    products.get(orderID)
            ));
        }
        return orders;
    }

    private void fillOrderData(ResultSet resultSet,
                               Map<Integer, List<Product>> products,
                               Map<Integer, Double> grandTotal,
                               Map<Integer, Integer> cards,
                               Map<Integer, Timestamp> dateOfPurchases) throws SQLException, ServiceException {
        while (resultSet.next()) {
            Integer orderID = resultSet.getInt(SQLOrderParameter.ORDER_ID);
            List<Product> productList;
            if (products.containsKey(orderID)) {
                productList = products.get(orderID);
            } else {
                productList = new ArrayList<>();
            }
            Product product = ServiceProvider.getInstance().getProductService().takeByProductID(resultSet.getInt(SQLOrderParameter.ORDER_PRODUCT_ID));
            product.setQuantity(resultSet.getInt(SQLOrderParameter.ORDER_PRODUCT_QUANTITY));
            productList.add(product);
            products.put(orderID, productList);
            if (!cards.containsKey(orderID)) {
                cards.put(orderID, resultSet.getInt(SQLOrderParameter.ORDER_CARD_ID));
            }
            if (!dateOfPurchases.containsKey(orderID)) {
                dateOfPurchases.put(orderID, resultSet.getTimestamp(SQLOrderParameter.ORDER_DATE_OF_PURCHASE));
            }
            if (!grandTotal.containsKey(orderID)) {
                grandTotal.put(orderID, resultSet.getDouble(SQLOrderParameter.ORDER_GRAND_TOTAL));
            }
        }
    }

    private Connection getConnection() throws DAOException {
        Connection connection;
        try {
            connection = ConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            logger.error("ConnectionPoolException was thrown due to an error during getting connection from connection pool");
            throw new DAOException("Error with getting pool from server", e);
        }
        return connection;
    }

    private boolean removeConnection(Connection connection) {
        return ConnectionPool.getInstance().removeConnection(connection);
    }
}
