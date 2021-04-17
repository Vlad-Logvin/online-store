package by.logvin.onlinestore.dao.impl;

import by.logvin.onlinestore.bean.Category;
import by.logvin.onlinestore.dao.CategoryDAO;
import by.logvin.onlinestore.dao.connection.ConnectionPool;
import by.logvin.onlinestore.dao.connection.ConnectionPoolException;
import by.logvin.onlinestore.dao.exception.DAOException;
import by.logvin.onlinestore.dao.impl.sqlrequest.CategorySQLRequest;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLCategoryDAO implements CategoryDAO {

    private final static Logger logger = Logger.getLogger(SQLCategoryDAO.class);

    @Override
    public boolean addCategory(String name) throws DAOException {
        Connection connection = getConnection();
        logger.info("Connection established");
        PreparedStatement preparedStatement = null;
        int numberOfUpdateLines = 0;
        try {
            preparedStatement = connection.prepareStatement(CategorySQLRequest.INSERT_CATEGORY);
            preparedStatement.setString(1, name);
            numberOfUpdateLines = preparedStatement.executeUpdate();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
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
            if (connection != null) {
                removeConnection(connection);
                logger.info("Connection is broken");
            }
        }

        return numberOfUpdateLines != 0;
    }

    @Override
    public boolean deleteCategory(int categoryID) throws DAOException {
        Connection connection = getConnection();
        logger.info("Connection established");
        PreparedStatement preparedStatement = null;
        int numberOfUpdateLines = 0;
        try {
            preparedStatement = connection.prepareStatement(CategorySQLRequest.DELETE_CATEGORY);
            preparedStatement.setInt(1, categoryID);
            numberOfUpdateLines = preparedStatement.executeUpdate();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
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
            if (connection != null) {
                removeConnection(connection);
                logger.info("Connection is broken");
            }
        }


        return numberOfUpdateLines != 0;
    }

    @Override
    public Category getCategory(int categoryID) throws DAOException {
        Connection connection = getConnection();
        logger.info("Connection established");
        PreparedStatement preparedStatement = null;
        Category category = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(CategorySQLRequest.SELECT_CATEGORY_BY_ID);
            preparedStatement.setInt(1, categoryID);
            resultSet = preparedStatement.executeQuery();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
            if (!resultSet.next()) {
                logger.info("Category with this id was not found");
                return null;
            }
            category = new Category(
                    resultSet.getInt("c_id"),
                    resultSet.getString("c_name")
            );
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
                logger.error("Result set has been already closed", e);
            }
            if (connection != null) {
                removeConnection(connection);
                logger.info("Connection is broken");
            }
        }
        return category;
    }

    @Override
    public List<Category> getCategories() throws DAOException {
        Connection connection = getConnection();
        logger.info("Connection established");
        Statement statement = null;
        List<Category> categories = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(CategorySQLRequest.SELECT_ALL_CATEGORIES);
            logger.info("Request (" + statement + ") was completed");
            while (resultSet.next()) {
                if (categories == null) {
                    categories = new ArrayList<>();
                }
                categories.add(new Category(
                   resultSet.getInt("c_id"),
                   resultSet.getString("c_name")
                ));
            }
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution", e);
            throw new DAOException("Error prepared statement updating or setting data", e);
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
        return categories;
    }

    @Override
    public Category getCategory(String name) throws DAOException {
        Connection connection = getConnection();
        logger.info("Connection established");
        PreparedStatement preparedStatement = null;
        Category category = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(CategorySQLRequest.SELECT_CATEGORY_BY_NAME);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
            if (!resultSet.next()) {
                logger.info("Category with this name was not found");
                return null;
            }
            category = new Category(
                    resultSet.getInt("c_id"),
                    resultSet.getString("c_name")
            );
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
                logger.error("Result set has been already closed", e);
            }
            if (connection != null) {
                removeConnection(connection);
                logger.info("Connection is broken");
            }
        }
        return category;
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
