package by.logvin.onlinestore.dao.impl;

import by.logvin.onlinestore.bean.Attribute;
import by.logvin.onlinestore.bean.Category;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.dao.ProductDAO;
import by.logvin.onlinestore.dao.connection.ConnectionPool;
import by.logvin.onlinestore.dao.connection.ConnectionPoolException;
import by.logvin.onlinestore.dao.exception.DAOException;
import by.logvin.onlinestore.dao.impl.sqlrequest.ProductSQLRequest;
import by.logvin.onlinestore.service.ServiceProvider;
import by.logvin.onlinestore.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SQLProductDAO implements ProductDAO {

    private static final Logger logger = Logger.getLogger(SQLProductDAO.class);

    @Override
    public Product take() throws DAOException {
        Connection connection = getConnection();
        logger.info("Connection established");
        Statement statement = null;
        ResultSet resultSet = null;
        Product product = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(ProductSQLRequest.selectOneProduct);
            logger.info("Request (" + statement.toString() + ") completed");
            if (!resultSet.next()) {
                logger.info("Throw DAO Exception due to non-existing some product in database");
                throw new DAOException("There aren't any products in the database");
            }
            product = getProductFromResultSet(connection, resultSet);
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution", e);
            throw new DAOException("Error prepared statement updating or setting data", e);
        } catch (ServiceException e) {
            logger.error("ServiceException was thrown due to an error during getting product information by product id", e);
            throw new DAOException("Error product information by product id", e);
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
        return product;
    }

    @Override
    public List<Product> take(int number) throws DAOException {
        Connection connection = getConnection();
        logger.info("Connection established");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Product> products = null;
        try {
            preparedStatement = connection.prepareStatement(ProductSQLRequest.selectNProducts);
            preparedStatement.setInt(1, number);
            resultSet = preparedStatement.executeQuery();
            logger.info("Request (" + preparedStatement.toString() + ") completed");
            while (resultSet.next()) {
                if (products == null) {
                    products = new ArrayList<>();
                }
                products.add(getProductFromResultSet(connection, resultSet));
            }
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution", e);
            throw new DAOException("Error prepared statement updating or setting data", e);
        } catch (ServiceException e) {
            logger.error("ServiceException was thrown due to an error during getting product information by product id", e);
            throw new DAOException("Error product information by product id", e);
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
        return products;
    }

    @Override
    public boolean add(String name, double price, String description, int quantity, String photoURL, int categoryID, Map<String, String> attributes) throws DAOException {
        Connection connection = getConnection();
        logger.info("Connection established");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int numberOfUpdatedLines = 0;
        int productID = 0;
        try {
            preparedStatement = connection.prepareStatement(ProductSQLRequest.insertProduct);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, price);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setString(4, description);
            preparedStatement.setInt(5, categoryID);
            preparedStatement.setString(6, photoURL);
            numberOfUpdatedLines = preparedStatement.executeUpdate();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
            preparedStatement = connection.prepareStatement(ProductSQLRequest.selectProductIDByNameAndCategory);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, categoryID);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                logger.info("DAOException thrown due to an error with name and category id");
                throw new DAOException("Error with name or category id");
            }
            productID = resultSet.getInt("p.p_id");
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
            ServiceProvider.getInstance().getAttributeService().addAttributes(productID, attributes);
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution", e);
            throw new DAOException("Error prepared statement updating or setting data", e);
        } catch (ServiceException e) {
            logger.error("ServiceException was thrown due to an error during getting product information by product id", e);
            throw new DAOException("Error product information by product id", e);
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
        return numberOfUpdatedLines != 0;
    }

    @Override
    public boolean remove(int productID) throws DAOException {
        Connection connection = getConnection();
        logger.info("Connection established");
        PreparedStatement preparedStatement = null;
        int numberOfUpdatedLines = 0;
        try {
            preparedStatement = connection.prepareStatement(ProductSQLRequest.deleteProductByID);
            preparedStatement.setInt(1, productID);
            numberOfUpdatedLines = preparedStatement.executeUpdate();
            logger.info("Request (" + preparedStatement.toString() + ") completed");

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
    public boolean edit(int productID, String name, double price, String description, int quantity, String photoURL, int categoryID, Map<String, String> attributes) throws DAOException {
        Connection connection = getConnection();
        logger.info("Connection established");
        PreparedStatement preparedStatement = null;
        int numberOfUpdatedLines = 0;
        try {
            preparedStatement = connection.prepareStatement(ProductSQLRequest.updateProductByID);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, price);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setString(4, description);
            preparedStatement.setInt(5, categoryID);
            preparedStatement.setString(6, photoURL);
            preparedStatement.setInt(7, productID);
            numberOfUpdatedLines = preparedStatement.executeUpdate();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
            ServiceProvider.getInstance().getAttributeService().updateAttributes(productID, attributes);
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution", e);
            throw new DAOException("Error prepared statement updating or setting data", e);
        } catch (ServiceException e) {
            logger.error("ServiceException was thrown due to an error during getting product information by product id", e);
            throw new DAOException("Error product information by product id", e);
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
    public List<Product> takeAll() throws DAOException {
        Connection connection = getConnection();
        logger.info("Connection established");
        Statement statement = null;
        ResultSet resultSet = null;
        List<Product> product = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(ProductSQLRequest.selectAllProducts);
            while (resultSet.next()) {
                if (product == null) {
                    product = new ArrayList<>();
                }
                product.add(getProductFromResultSet(connection, resultSet));
            }
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution", e);
            throw new DAOException("Error prepared statement updating or setting data", e);
        } catch (ServiceException e) {
            logger.error("ServiceException was thrown due to an error during getting product information by product id", e);
            throw new DAOException("Error product information by product id", e);
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
        return product;
    }

    @Override
    public Product takeByProductID(int productID) throws DAOException {
        Connection connection = getConnection();
        logger.info("Connection established");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Product product = null;
        try {
            preparedStatement = connection.prepareStatement(ProductSQLRequest.selectProductByProductID);
            preparedStatement.setInt(1, productID);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new DAOException("Error id");
            }
            product = getProductFromResultSet(connection, resultSet);
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution", e);
            throw new DAOException("Error prepared statement updating or setting data", e);
        } catch (ServiceException e) {
            logger.error("ServiceException was thrown due to an error during getting product information by product id", e);
            throw new DAOException("Error product information by product id", e);
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
        return product;
    }

    private Product getProductFromResultSet(Connection connection, ResultSet resultSet) throws SQLException, ServiceException {
        int productID = resultSet.getInt("p_id");
        Category category = ServiceProvider.getInstance().getCategoryService().getCategory(resultSet.getInt("p_category_id"));
        List<Attribute> attributes = ServiceProvider.getInstance().getAttributeService().getAttributes(productID);
        return new Product(
                productID,
                resultSet.getString("p_name"),
                resultSet.getDouble("p_price"),
                resultSet.getString("p_description"),
                resultSet.getInt("p_quantity"),
                resultSet.getString("p_photo_url"),
                category,
                attributes
        );
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
