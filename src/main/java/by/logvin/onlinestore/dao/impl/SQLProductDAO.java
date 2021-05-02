package by.logvin.onlinestore.dao.impl;

import by.logvin.onlinestore.bean.Attribute;
import by.logvin.onlinestore.bean.Category;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.dao.ProductDAO;
import by.logvin.onlinestore.dao.connection.ConnectionPool;
import by.logvin.onlinestore.dao.connection.ConnectionPoolException;
import by.logvin.onlinestore.dao.exception.DAOException;
import by.logvin.onlinestore.dao.impl.sqlparameter.SQLProductParameter;
import by.logvin.onlinestore.dao.impl.sqlrequest.ProductSQLRequest;
import by.logvin.onlinestore.service.ServiceProvider;
import by.logvin.onlinestore.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The class SQLProductDAO is used for manipulating product data in the database
 * @author bylogvin
 * @see by.logvin.onlinestore.dao.ProductDAO
 */
public class SQLProductDAO implements ProductDAO {

    private static final Logger logger = Logger.getLogger(SQLProductDAO.class);

    @Override
    public Product take() throws DAOException {
        Connection connection = getConnection();
        Product product;
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(ProductSQLRequest.SELECT_ONE_PRODUCT)
        ) {
            logger.info("Request (" + statement.toString() + ") completed");
            if (!resultSet.next()) {
                logger.info("Not found product in database");
                return null;
            }
            product = getProductFromResultSet(resultSet);
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution");
            throw new DAOException("Error prepared statement updating or setting data", e);
        } catch (ServiceException e) {
            logger.error("ServiceException was thrown due to an error during getting product information by product id");
            throw new DAOException("Error product information by product id", e);
        } finally {
            if (connection != null) {
                removeConnection(connection);
            }
        }
        return product;
    }

    @Override
    public boolean orderProduct(int productID, int quantity) throws DAOException {
        Connection connection = getConnection();
        int numberOfUpdatedLines;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(ProductSQLRequest.UPDATE_INCREASE_PRODUCT_QUANTITY_BY_ID)
        ) {
            preparedStatement.setInt(1, quantity);
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
    public List<Product> take(int number) throws DAOException {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        List<Product> products = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(ProductSQLRequest.SELECT_N_PRODUCTS)
        ) {
            preparedStatement.setInt(1, number);
            resultSet = preparedStatement.executeQuery();
            logger.info("Request (" + preparedStatement.toString() + ") completed");
            while (resultSet.next()) {
                if (products == null) {
                    products = new ArrayList<>();
                }
                products.add(getProductFromResultSet(resultSet));
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
        return products;
    }

    @Override
    public boolean add(String name, double price, String description, int quantity, String photoURL, int categoryID, Map<String, String> attributes) throws DAOException {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        int numberOfUpdatedLines;
        int productID;
        try (
                PreparedStatement productInsertingStatement = connection.prepareStatement(ProductSQLRequest.INSERT_PRODUCT);
                PreparedStatement productSelectingStatement = connection.prepareStatement(ProductSQLRequest.SELECT_PRODUCT_ID_BY_NAME_AND_CATEGORY)
        ) {
            productInsertingStatement.setString(1, name);
            productInsertingStatement.setDouble(2, price);
            productInsertingStatement.setInt(3, quantity);
            productInsertingStatement.setString(4, description);
            productInsertingStatement.setInt(5, categoryID);
            productInsertingStatement.setString(6, photoURL);
            numberOfUpdatedLines = productInsertingStatement.executeUpdate();
            logger.info("Request (" + productInsertingStatement.toString() + ") was completed");
            productSelectingStatement.setString(1, name);
            productSelectingStatement.setInt(2, categoryID);
            resultSet = productSelectingStatement.executeQuery();
            logger.info("Request (" + productSelectingStatement.toString() + ") was completed");
            if (!resultSet.next()) {
                logger.info("DAOException thrown due to an error with name and category id");
                throw new DAOException("Error with name or category id");
            }
            productID = resultSet.getInt(SQLProductParameter.PRODUCT_PARAMETER + SQLProductParameter.PRODUCT_ID);
            logger.info("Request (" + productSelectingStatement.toString() + ") was completed");
            ServiceProvider.getInstance().getAttributeService().addAttributes(productID, attributes);
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
        return numberOfUpdatedLines != 0;
    }

    @Override
    public List<Product> take(Category category) throws DAOException {
        if (category == null) {
            return null;
        }
        Connection connection = getConnection();
        ResultSet resultSet = null;
        List<Product> products = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(ProductSQLRequest.SELECT_PRODUCT_BY_CATEGORY_ID)
        ) {
            preparedStatement.setInt(1, category.getId());
            resultSet = preparedStatement.executeQuery();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
            while (resultSet.next()) {
                if (products == null) {
                    products = new ArrayList<>();
                }
                products.add(getProductFromResultSet(resultSet));
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
        return products;
    }

    @Override
    public boolean remove(int productID) throws DAOException {
        Connection connection = getConnection();
        int numberOfUpdatedLines;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(ProductSQLRequest.DELETE_PRODUCT_BY_ID)
        ) {
            preparedStatement.setInt(1, productID);
            numberOfUpdatedLines = preparedStatement.executeUpdate();
            logger.info("Request (" + preparedStatement.toString() + ") completed");

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
    public boolean edit(int productID, String name, double price, String description, int quantity, String photoURL, int categoryID, Map<String, String> attributes) throws DAOException {
        Connection connection = getConnection();
        int numberOfUpdatedLines;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(ProductSQLRequest.UPDATE_PRODUCT_BY_ID)
        ) {
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
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution");
            throw new DAOException("Error prepared statement updating or setting data", e);
        } catch (ServiceException e) {
            logger.error("ServiceException was thrown due to an error during getting product information by product id");
            throw new DAOException("Error product information by product id", e);
        } finally {
            if (connection != null) {
                removeConnection(connection);
            }
        }
        return numberOfUpdatedLines != 0;
    }

    @Override
    public List<Product> takeAll() throws DAOException {
        Connection connection = getConnection();
        List<Product> product = null;
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(ProductSQLRequest.SELECT_ALL_PRODUCTS)
        ) {
            logger.info("Request (" + statement.toString() + ") was completed");
            while (resultSet.next()) {
                if (product == null) {
                    product = new ArrayList<>();
                }
                product.add(getProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution");
            throw new DAOException("Error prepared statement updating or setting data", e);
        } catch (ServiceException e) {
            logger.error("ServiceException was thrown due to an error during getting product information by product id");
            throw new DAOException("Error product information by product id", e);
        } finally {
            if (connection != null) {
                removeConnection(connection);
            }
        }
        return product;
    }

    @Override
    public Product takeByProductID(int productID) throws DAOException {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        Product product;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(ProductSQLRequest.SELECT_PRODUCT_BY_PRODUCT_ID)
        ) {

            preparedStatement.setInt(1, productID);
            resultSet = preparedStatement.executeQuery();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
            if (!resultSet.next()) {
                logger.info("Not found product with such product id");
                return null;
            }
            product = getProductFromResultSet(resultSet);
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
        return product;
    }

    private Product getProductFromResultSet(ResultSet resultSet) throws SQLException, ServiceException {
        int productID = resultSet.getInt(SQLProductParameter.PRODUCT_ID);
        Category category = ServiceProvider.getInstance().getCategoryService().takeCategory(resultSet.getInt(SQLProductParameter.PRODUCT_CATEGORY_ID));
        List<Attribute> attributes = ServiceProvider.getInstance().getAttributeService().takeAttributes(productID);
        return new Product(
                productID,
                resultSet.getString(SQLProductParameter.PRODUCT_NAME),
                resultSet.getDouble(SQLProductParameter.PRODUCT_PRICE),
                resultSet.getString(SQLProductParameter.PRODUCT_DESCRIPTION),
                resultSet.getInt(SQLProductParameter.PRODUCT_QUANTITY),
                resultSet.getString(SQLProductParameter.PRODUCT_PHOTO_URL),
                category,
                attributes
        );
    }

    @Override
    public boolean buyProduct(int productID, int quantity) throws DAOException {
        Connection connection = getConnection();
        int numberOfUpdatedLines;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(ProductSQLRequest.UPDATE_PRODUCT_QUANTITY_BY_ID)
        ) {
            preparedStatement.setInt(1, quantity);
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
