package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.bean.Attribute;
import by.logvin.onlinestore.dao.exception.DAOException;

import java.util.List;
import java.util.Map;

/**
 * The AttributeDAO interface is used to manipulate attribute data in the database
 * @author bylogvin
 */
public interface AttributeDAO {

    /**
     * The method addAttributes adds attributes to database by product id
     *
     * @param productID  int product id
     * @param attributes {@link Map} new product attributes
     * @return boolean true if adding is successful
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    boolean addAttributes(int productID, Map<String, String> attributes) throws DAOException;

    /**
     * The method takeAttributes takes product attributes by product id
     *
     * @param productID int product id
     * @return {@link List} of attributes of product
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    List<Attribute> takeAttributes(int productID) throws DAOException;

    /**
     * The method deleteAttributes deletes attributes from database by product id
     *
     * @param productID int product id
     * @return boolean true if deleting is successful
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    boolean deleteAttributes(int productID) throws DAOException;
}
