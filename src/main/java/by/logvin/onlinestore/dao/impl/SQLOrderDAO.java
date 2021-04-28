package by.logvin.onlinestore.dao.impl;

import by.logvin.onlinestore.bean.Order;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.dao.OrderDAO;
import by.logvin.onlinestore.dao.connection.ConnectionPool;
import by.logvin.onlinestore.dao.connection.ConnectionPoolException;
import by.logvin.onlinestore.dao.exception.DAOException;
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
        logger.info("Connection established");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Order> orders = null;
        Map<Integer, List<Product>> products = null;
        Map<Integer, Integer> cards = null;
        Map<Integer, Timestamp> dateOfPurchases = null;
        Map<Integer, Double> grandTotal = null;
        try {
            preparedStatement = connection.prepareStatement(OrderSQLRequest.SELECT_ALL_ORDERS_BY_USER_ID);
            preparedStatement.setInt(1, userID);
            resultSet = preparedStatement.executeQuery();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
            while (resultSet.next()) {
                if (products == null) {
                    products = new HashMap<>();
                }
                if (cards == null) {
                    cards = new HashMap<>();
                }
                if (dateOfPurchases == null) {
                    dateOfPurchases = new HashMap<>();
                }
                if (grandTotal == null) {
                    grandTotal = new HashMap<>();
                }
                Integer orderID = resultSet.getInt("o_id");
                List<Product> productList = null;
                if (products.containsKey(orderID)) {
                    productList = products.get(orderID);
                } else {
                    productList = new ArrayList<>();
                }
                productList.add(ServiceProvider.getInstance().getProductService().takeByProductID(resultSet.getInt("op_product_id")));
                products.put(orderID, productList);
                if (!cards.containsKey(orderID)) {
                    cards.put(orderID, resultSet.getInt("o_card_id"));
                }
                if (!dateOfPurchases.containsKey(orderID)) {
                    dateOfPurchases.put(orderID, resultSet.getTimestamp("o_date_of_purchase"));
                }
                if (!grandTotal.containsKey(orderID)) {
                    grandTotal.put(orderID, resultSet.getDouble("o_grand_total"));
                }
            }
            if (products != null) {
                for (Integer orderID : products.keySet()) {
                    if (orders == null) {
                        orders = new ArrayList<>();
                    }
                    orders.add(new Order(
                            orderID,
                            grandTotal.get(orderID),
                            ServiceProvider.getInstance().getCardService().takeCard(cards.get(orderID)),
                            dateOfPurchases.get(orderID),
                            products.get(orderID)
                    ));
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution", e);
            throw new DAOException("Error prepared statement updating or setting data", e);
        } catch (ServiceException e) {
            logger.error("ServiceException was thrown due to an error during getting product|card information by product|card id", e);
            throw new DAOException("Error product|card information by product|card id", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("Prepared statement has been already closed", e);
            }
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("Result set has been already closed", e);
            }
            if (connection != null) {
                removeConnection(connection);
                logger.info("Connection is broken");
            }
        }
        return orders;
    }

    @Override
    public boolean makeOrder(int userID, Map<Product, Integer> products, int cardID, Timestamp dateOfPurchase) throws DAOException {
        Connection connection = getConnection();
        logger.info("Connection established");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int numberOfUpdatedLines = 0;
        try {
            preparedStatement = connection.prepareStatement(OrderSQLRequest.INSERT_ORDER);
            preparedStatement.setInt(1, userID);
            preparedStatement.setDouble(2, getGrandTotal(products));
            preparedStatement.setInt(3, cardID);
            preparedStatement.setTimestamp(4,  dateOfPurchase);
            numberOfUpdatedLines = preparedStatement.executeUpdate();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
            preparedStatement = connection.prepareStatement(OrderSQLRequest.SELECT_LAST_ORDER_ID);
            preparedStatement.setInt(1, userID);
            resultSet = preparedStatement.executeQuery();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
            resultSet.next();
            int orderID = resultSet.getInt("o_id");
            preparedStatement = connection.prepareStatement(OrderSQLRequest.INSERT_PRODUCT_TO_ORDER);
            preparedStatement.setInt(1, orderID);
            for (Product product : products.keySet()) {
                preparedStatement.setInt(2, product.getId());
                preparedStatement.setInt(3, products.get(product));
                numberOfUpdatedLines += preparedStatement.executeUpdate();
                logger.info("Request (" + preparedStatement.toString() + ") was completed");
            }
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution", e);
            throw new DAOException("Error prepared statement updating or setting data", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("Prepared statement has been already closed", e);
            }
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("Result set has been already closed");
            }
            if (connection != null) {
                removeConnection(connection);
                logger.info("Connection is broken");
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
        logger.info("Connection established");
        Statement statement = null;
        ResultSet resultSet = null;
        List<Order> orders = null;
        Map<Integer, List<Product>> products = null;
        Map<Integer, Integer> cards = null;
        Map<Integer, Timestamp> dateOfPurchases = null;
        Map<Integer, Double> grandTotal = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(OrderSQLRequest.SELECT_ALL_ORDERS);
            logger.info("Request (" + statement.toString() + ") was completed");
            while (resultSet.next()) {
                if (products == null) {
                    products = new HashMap<>();
                }
                if (cards == null) {
                    cards = new HashMap<>();
                }
                if (dateOfPurchases == null) {
                    dateOfPurchases = new HashMap<>();
                }
                if (grandTotal == null) {
                    grandTotal = new HashMap<>();
                }
                Integer orderID = resultSet.getInt("o_id");
                List<Product> productList = null;
                if (products.containsKey(orderID)) {
                    productList = products.get(orderID);
                } else {
                    productList = new ArrayList<>();
                }
                Product product = ServiceProvider.getInstance().getProductService().takeByProductID(resultSet.getInt("op_product_id"));
                product.setQuantity(resultSet.getInt("op_quantity"));
                productList.add(product);
                products.put(orderID, productList);
                if (!cards.containsKey(orderID)) {
                    cards.put(orderID, resultSet.getInt("o_card_id"));
                }
                if (!dateOfPurchases.containsKey(orderID)) {
                    dateOfPurchases.put(orderID, resultSet.getTimestamp("o_date_of_purchase"));
                }
                if (!grandTotal.containsKey(orderID)) {
                    grandTotal.put(orderID, resultSet.getDouble("o_grand_total"));
                }
            }
            if (products != null) {
                for (Integer orderID : products.keySet()) {
                    if (orders == null) {
                        orders = new ArrayList<>();
                    }
                    orders.add(new Order(
                            orderID,
                            grandTotal.get(orderID),
                            ServiceProvider.getInstance().getCardService().takeCard(cards.get(orderID)),
                            dateOfPurchases.get(orderID),
                            products.get(orderID)
                    ));
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution", e);
            throw new DAOException("Error prepared statement updating", e);
        } catch (ServiceException e) {
            logger.error("ServiceException was thrown due to an error during getting product|card information by product|card id", e);
            throw new DAOException("Error product|card information by product|card id", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("Prepared statement has been already closed", e);
            }
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("Result set has been already closed", e);
            }
            if (connection != null) {
                removeConnection(connection);
                logger.info("Connection is broken");
            }
        }
        return orders;
    }

    private Connection getConnection() throws DAOException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            logger.info("Connection is gotten from connection pool");
        } catch (ConnectionPoolException e) {
            logger.error("ConnectionPoolException was thrown due to an error during getting connection from connection pool", e);
            throw new DAOException("Error with getting pool from server", e);
        }
        return connection;
    }

    private boolean removeConnection(Connection connection) {
        return ConnectionPool.getInstance().removeConnection(connection);
    }
}
