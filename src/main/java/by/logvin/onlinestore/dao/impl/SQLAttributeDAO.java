package by.logvin.onlinestore.dao.impl;

import by.logvin.onlinestore.bean.Attribute;
import by.logvin.onlinestore.dao.AttributeDAO;
import by.logvin.onlinestore.dao.connection.ConnectionPool;
import by.logvin.onlinestore.dao.connection.ConnectionPoolException;
import by.logvin.onlinestore.dao.exception.DAOException;
import by.logvin.onlinestore.dao.impl.sqlrequest.AttributeSQLRequest;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SQLAttributeDAO implements AttributeDAO {

    private final static Logger logger = Logger.getLogger(SQLAttributeDAO.class);

    @Override
    public boolean addAttributes(int productID, Map<String, String> attributes) throws DAOException {
        Connection connection = getConnection();
        logger.info("Connection established");
        PreparedStatement preparedStatement = null;
        Set<String> attributeNames = attributes.keySet();
        int numberOfUpdatedLines = 0;
        try {
            preparedStatement = connection.prepareStatement(AttributeSQLRequest.insertAttribute);
            for (String attributeName : attributeNames) {
                preparedStatement.setString(1, attributeName);
                preparedStatement.setString(2, attributes.get(attributeName));
                preparedStatement.setInt(3, productID);
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
            if (connection != null) {
                removeConnection(connection);
                logger.info("Connection is broken");
            }
        }

        return numberOfUpdatedLines != 0;
    }

    @Override
    public boolean deleteAttributes(int productID) throws DAOException {
        Connection connection = getConnection();
        logger.info("Connection established");
        PreparedStatement preparedStatement = null;
        int numberOfUpdatedLines = 0;
        try {
            preparedStatement = connection.prepareStatement(AttributeSQLRequest.deleteAttributesByProductID);
            preparedStatement.setInt(1, productID);
            numberOfUpdatedLines = preparedStatement.executeUpdate();
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

        return numberOfUpdatedLines != 0;
    }

    @Override
    public List<Attribute> getAttributes(int productID) throws DAOException {
        Connection connection = getConnection();
        logger.info("Connection established");
        PreparedStatement preparedStatement = null;
        List<Attribute> attributes = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(AttributeSQLRequest.selectAttributesByProductID);
            preparedStatement.setInt(1, productID);
            resultSet = preparedStatement.executeQuery();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
            while (resultSet.next()) {
                if (attributes == null) {
                    attributes = new ArrayList<>();
                }
                attributes.add(new Attribute(
                        resultSet.getInt("a_id"),
                        resultSet.getString("a_name"),
                        resultSet.getString("a_value")
                ));
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
                logger.error("Result set has been already closed", e);
            }
            if (connection != null) {
                removeConnection(connection);
                logger.info("Connection is broken");
            }
        }
        return attributes;
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
