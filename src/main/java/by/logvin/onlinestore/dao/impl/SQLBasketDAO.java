package by.logvin.onlinestore.dao.impl;

import by.logvin.onlinestore.bean.Basket;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.dao.BasketDAO;
import by.logvin.onlinestore.dao.connection.ConnectionPool;
import by.logvin.onlinestore.dao.connection.ConnectionPoolException;
import by.logvin.onlinestore.dao.exception.DAOException;
import by.logvin.onlinestore.dao.impl.sqlparameter.SQLBasketParameter;
import by.logvin.onlinestore.dao.impl.sqlrequest.BasketSQLRequest;
import by.logvin.onlinestore.service.ServiceProvider;
import by.logvin.onlinestore.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLBasketDAO implements BasketDAO {

    private final static Logger logger = Logger.getLogger(SQLBasketDAO.class);

    @Override
    public Basket takeBasketByUserID(int userID) throws DAOException {
        Connection connection = getConnection();
        Basket basket = null;
        ResultSet resultSet = null;
        List<Product> products = null;
        int basketID = 0;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(BasketSQLRequest.SELECT_BASKET_BY_USER_ID)
        ) {
            preparedStatement.setInt(1, userID);
            resultSet = preparedStatement.executeQuery();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
            while (resultSet.next()) {
                if (basketID == 0) {
                    basketID = resultSet.getInt(SQLBasketParameter.BASKET_ID);
                }
                if (products == null) {
                    products = new ArrayList<>();
                }
                if (resultSet.getInt(SQLBasketParameter.BASKET_PRODUCT_ID) != 0) {
                    products.add(ServiceProvider.getInstance().getProductService().takeByProductID(resultSet.getInt(SQLBasketParameter.BASKET_PRODUCT_ID)));
                }
            }
            if (basketID != 0) {
                basket = new Basket(
                        basketID,
                        products
                );
            }
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution");
            throw new DAOException("Error prepared statement updating or setting data", e);
        } catch (ServiceException e) {
            logger.error("ServiceException was thrown due to an error during getting product information by product id");
            throw new DAOException("Error product information by product id", e);
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
        return basket;
    }

    @Override
    public boolean addProduct(int basketID, int productID) throws DAOException {
        Connection connection = getConnection();
        int numberOfUpdatedLines;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(BasketSQLRequest.ADD_PRODUCT_TO_BASKET)
        ) {
            preparedStatement.setInt(1, basketID);
            preparedStatement.setInt(2, productID);
            numberOfUpdatedLines = preparedStatement.executeUpdate();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution");
            throw new DAOException("Error prepared statement updating or setting data", e);
        } finally {
            if (connection != null) {
                removeConnection(connection);
            }
        }
        return numberOfUpdatedLines != 0;
    }

    @Override
    public boolean removeProduct(int basketID, int productID) throws DAOException {
        Connection connection = getConnection();
        int numberOfUpdatedLines;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(BasketSQLRequest.REMOVE_PRODUCT_FROM_BASKET)
        ) {
            preparedStatement.setInt(1, basketID);
            preparedStatement.setInt(2, productID);
            numberOfUpdatedLines = preparedStatement.executeUpdate();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution");
            throw new DAOException("Error prepared statement updating or setting data", e);
        } finally {
            if (connection != null) {
                removeConnection(connection);
            }
        }
        return numberOfUpdatedLines != 0;
    }

    @Override
    public boolean deleteBasketByUserID(int userID) throws DAOException {
        Connection connection = getConnection();
        int numberOfUpdatedLines;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(BasketSQLRequest.DELETE_BASKET_BY_USER_ID)
        ) {
            preparedStatement.setInt(1, userID);
            numberOfUpdatedLines = preparedStatement.executeUpdate();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution");
            throw new DAOException("Error prepared statement updating or setting data", e);
        } finally {
            if (connection != null) {
                removeConnection(connection);
            }
        }
        return numberOfUpdatedLines != 0;
    }

    @Override
    public boolean createBasket(int userID) throws DAOException {
        Connection connection = getConnection();
        int numberOfUpdatedLines;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(BasketSQLRequest.INSERT_BASKET)
        ) {
            preparedStatement.setInt(1, userID);
            numberOfUpdatedLines = preparedStatement.executeUpdate();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution");
            throw new DAOException("Error prepared statement updating or setting data", e);
        } finally {
            if (connection != null) {
                removeConnection(connection);
            }
        }
        return numberOfUpdatedLines != 0;
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
