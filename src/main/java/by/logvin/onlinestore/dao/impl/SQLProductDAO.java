package by.logvin.onlinestore.dao.impl;

import by.logvin.onlinestore.bean.Attribute;
import by.logvin.onlinestore.bean.Category;
import by.logvin.onlinestore.bean.Criteria;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.dao.ProductDAO;
import by.logvin.onlinestore.dao.connection.ConnectionPool;
import by.logvin.onlinestore.dao.connection.ConnectionPoolException;
import by.logvin.onlinestore.dao.exception.DAOException;
import by.logvin.onlinestore.dao.impl.sqlrequest.ProductSQLRequest;
import by.logvin.onlinestore.dao.impl.sqlrequest.SQLRequest;
import by.logvin.onlinestore.service.ServiceProvider;
import by.logvin.onlinestore.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            logger.error("SQLException was thrown due to an error during request creation or executing", e);
            throw new DAOException("Error prepared statement creating or setting data", e);
        } catch (ServiceException e) {
            throw new DAOException("Service exception");
        }
        removeConnection(connection);
        logger.info("Connection is broken");
        return product;
    }

    @Override
    public List<Product> take(int number) throws DAOException {
        Connection connection = getConnection();
        logger.info("Connection established");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Product> product = null;
        try {
            preparedStatement = connection.prepareStatement(ProductSQLRequest.selectNProducts);
            preparedStatement.setInt(1, number);
            resultSet = preparedStatement.executeQuery();
            logger.info("Request (" + preparedStatement.toString() + ") completed");
            while (resultSet.next()) {
                if (product == null) {
                    product = new ArrayList<>();
                }
                product.add(getProductFromResultSet(connection, resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Error prepared statement creating or setting data", e);
        } catch (ServiceException e) {
            throw new DAOException("Service exception");
        }
        removeConnection(connection);
        logger.info("Connection is broken");
        return product;
    }

    @Override
    public boolean add(String name, double price, String description, int quantity, String photoURL, Category category, List<Attribute> attributes) throws DAOException {
        Connection connection = getConnection();
        logger.info("Connection established");
        PreparedStatement preparedStatement = null;
        return false;
    }

    @Override
    public boolean remove(int productID) throws DAOException {
        return false;
    }

    @Override
    public boolean edit(int productID, String name, double price, String description, int quantity, String photoURL, Category category, List<Attribute> attributes) throws DAOException {
        return false;
    }

    @Override
    public List<Product> takeAll() throws DAOException {
        Connection connection = getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        List<Product> product = null;
        try {
            resultSet = statement.executeQuery(ProductSQLRequest.selectAllProducts);
            while (resultSet.next()) {
                if (product == null) {
                    product = new ArrayList<>();
                }
                product.add(getProductFromResultSet(connection, resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Error prepared statement creating or setting data", e);
        } catch (ServiceException e) {
            throw new DAOException("service exception");
        }
        removeConnection(connection);
        return product;
    }

    @Override
    public Product takeByProductID(int productID) throws DAOException {
        Connection connection = getConnection();
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
            throw new DAOException(e);
        } catch (ServiceException e) {
            throw new DAOException("Service exception");
        }
        removeConnection(connection);
        logger.info(product);
        return product;
    }

    private Product getProductFromResultSet(Connection connection, ResultSet resultSet) throws SQLException, DAOException, ServiceException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQLRequest.selectCategoryById);
        preparedStatement.setInt(1, resultSet.getInt("p_category_id"));
        ResultSet set = preparedStatement.executeQuery();
        logger.info("Request (" + set.toString() + ") completed");
        int productID = resultSet.getInt("p_id");
        Category category = ServiceProvider.getInstance().getCategoryService().getCategory(resultSet.getInt("p_category"));
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
