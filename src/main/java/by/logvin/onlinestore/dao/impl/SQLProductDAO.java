package by.logvin.onlinestore.dao.impl;

import by.logvin.onlinestore.bean.Attribute;
import by.logvin.onlinestore.bean.Category;
import by.logvin.onlinestore.bean.Criteria;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.dao.ProductDAO;
import by.logvin.onlinestore.dao.connection.ConnectionPool;
import by.logvin.onlinestore.dao.connection.ConnectionPoolException;
import by.logvin.onlinestore.dao.exception.DAOException;
import by.logvin.onlinestore.dao.impl.sqlrequest.SQLRequest;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLProductDAO implements ProductDAO {

    private static final Logger logger = Logger.getLogger(SQLProductDAO.class);

    @Override
    public Product take() throws DAOException {
        Connection connection = getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        Product product = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQLRequest.selectOneProduct);
            if (!resultSet.next()) {
                throw new DAOException("There aren't any products in the database");
            }
            product = getProductFromResultSet(connection, resultSet);
        } catch (SQLException e) {
            throw new DAOException("Error prepared statement creating or setting data", e);
        }
        removeConnection(connection);
        return product;
    }

    @Override
    public List<Product> take(int number) throws DAOException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Product> product = null;
        try {
            preparedStatement = connection.prepareStatement(SQLRequest.selectNProducts);
            preparedStatement.setInt(1, number);
            logger.info("1");
            resultSet = preparedStatement.executeQuery();
            logger.info("2");
            while (resultSet.next()){
                logger.info("3");
                if(product==null){
                    product = new ArrayList<>();
                }
                product.add(getProductFromResultSet(connection, resultSet));
                logger.info("4");
            }
        } catch (SQLException e) {
            throw new DAOException("Error prepared statement creating or setting data", e);
        }
        removeConnection(connection);
        return product;
    }

    @Override
    public List<Product> takeAll() throws DAOException {
        Connection connection = getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        List<Product> product = null;
        try {
            resultSet = statement.executeQuery(SQLRequest.selectAllProducts);
            while (resultSet.next()){
                if(product==null){
                    product = new ArrayList<>();
                }
                product.add(getProductFromResultSet(connection, resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Error prepared statement creating or setting data", e);
        }
        removeConnection(connection);
        return product;
    }

    @Override
    public List<Product> take(Criteria criteria) throws DAOException {
        return null;
    }

    private Product getProductFromResultSet(Connection connection, ResultSet resultSet) throws SQLException, DAOException {
        logger.info("5");
        PreparedStatement preparedStatement = connection.prepareStatement(SQLRequest.selectCategoryById);
        preparedStatement.setInt(1, resultSet.getInt("p_category_id"));
        ResultSet set = preparedStatement.executeQuery();
        int productID = resultSet.getInt("p_id");
        if(!set.next()){
            throw new DAOException("Error with data getting");
        }
        Category category = getCategoryById(connection, set, productID);
        return new Product(
                productID,
                resultSet.getString("p_name"),
                resultSet.getDouble("p_price"),
                resultSet.getString("p_description"),
                resultSet.getInt("p_quantity"),
                resultSet.getString("p_photo_url"),
                category
        );
    }

    private Category getCategoryById(Connection connection, ResultSet resultSet, int productID) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQLRequest.selectAttributesByCategoryAndProductId);
        preparedStatement.setInt(1, resultSet.getInt("c_id"));
        preparedStatement.setInt(2, productID);
        ResultSet set = preparedStatement.executeQuery();
        List<Attribute> attributes = getAttributeListFromResultSet(set);
        return new Category(resultSet.getInt("c_id"), resultSet.getString("c_name"), attributes);
    }

    private List<Attribute> getAttributeListFromResultSet(ResultSet resultSet) throws SQLException {
        List<Attribute> attributes = new ArrayList<>();
        while (resultSet.next()) {
            attributes.add(new Attribute(resultSet.getInt("a_id"),
                    resultSet.getString("a_name"), resultSet.getString("a_value")));
        }
        return attributes;
    }

    private Connection getConnection() throws DAOException{
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception throws while getting connection from pool", e);
        }
        return connection;
    }

    private boolean removeConnection(Connection connection) {
        return ConnectionPool.getInstance().removeConnection(connection);
    }
}
