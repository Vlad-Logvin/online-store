package by.logvin.onlinestore.dao.impl;

import by.logvin.onlinestore.bean.Favourite;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.dao.FavouriteDAO;
import by.logvin.onlinestore.dao.connection.ConnectionPool;
import by.logvin.onlinestore.dao.connection.ConnectionPoolException;
import by.logvin.onlinestore.dao.exception.DAOException;
import by.logvin.onlinestore.dao.impl.sqlparameter.SQLFavouriteParameter;
import by.logvin.onlinestore.dao.impl.sqlrequest.FavouriteSQLRequest;
import by.logvin.onlinestore.service.ServiceProvider;
import by.logvin.onlinestore.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class SQLFavouriteDAO is used for manipulating favourite data in the database
 * @author bylogvin
 * @see by.logvin.onlinestore.dao.FavouriteDAO
 */
public class SQLFavouriteDAO implements FavouriteDAO {
    private final static Logger logger = Logger.getLogger(SQLFavouriteDAO.class);

    @Override
    public Favourite takeFavouriteByUserID(int userID) throws DAOException {
        Connection connection = getConnection();
        Favourite favourite = null;
        ResultSet resultSet = null;
        List<Product> products = null;
        int favouriteID = 0;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(FavouriteSQLRequest.SELECT_FAVOURITE_BY_USER_ID)
        ) {
            preparedStatement.setInt(1, userID);
            resultSet = preparedStatement.executeQuery();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
            while (resultSet.next()) {
                if (favouriteID == 0) {
                    favouriteID = resultSet.getInt(SQLFavouriteParameter.FAVOURITE_ID);
                }
                if (products == null) {
                    products = new ArrayList<>();
                }
                if (resultSet.getInt(SQLFavouriteParameter.FAVOURITE_PRODUCT_ID) != 0) {
                    products.add(ServiceProvider.getInstance().getProductService().takeByProductID(resultSet.getInt(SQLFavouriteParameter.FAVOURITE_PRODUCT_ID)));
                }
            }
            if (favouriteID != 0) {
                favourite = new Favourite(
                        favouriteID,
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
        return favourite;
    }

    @Override
    public boolean addProduct(int favouriteID, int productID) throws DAOException {
        Connection connection = getConnection();
        int numberOfUpdatedLines;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(FavouriteSQLRequest.ADD_PRODUCT_TO_FAVOURITE)
        ) {
            preparedStatement.setInt(1, favouriteID);
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
    public boolean removeProduct(int favouriteID, int productID) throws DAOException {
        Connection connection = getConnection();
        int numberOfUpdatedLines;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(FavouriteSQLRequest.REMOVE_PRODUCT_FROM_FAVOURITE)
        ) {
            preparedStatement.setInt(1, favouriteID);
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
    public boolean deleteFavouriteByUserID(int userID) throws DAOException {
        Connection connection = getConnection();
        int numberOfUpdatedLines;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(FavouriteSQLRequest.DELETE_FAVOURITE_BY_USER_ID)
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
    public boolean createFavourite(int userID) throws DAOException {
        Connection connection = getConnection();
        int numberOfUpdatedLines;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(FavouriteSQLRequest.INSERT_FAVOURITE)
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
