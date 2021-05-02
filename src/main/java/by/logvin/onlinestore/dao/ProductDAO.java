package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.bean.Category;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.dao.exception.DAOException;

import java.util.List;
import java.util.Map;

/**
 * The ProductDAO interface is used to manipulate product data in the database
 * @author bylogvin
 */
public interface ProductDAO {

    /**
     * The overload method take takes one random product from database
     *
     * @return {@link Product} random
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    Product take() throws DAOException;

    /**
     * The overload method take takes list of random number of product
     *
     * @param number int number of need products
     * @return {@link List} of number of products
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    List<Product> take(int number) throws DAOException;

    /**
     * The method takeAll takes all products from database
     *
     * @return {@link List} of all products
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    List<Product> takeAll() throws DAOException;

    /**
     * The overload method take takes all products that suit to category
     *
     * @param category {@link Category} of need products
     * @return {@link List} of all products that suit to category
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    List<Product> take(Category category) throws DAOException;

    /**
     * The method takeByProductID takes product by its id
     *
     * @param productID int product id
     * @return {@link Product} by its id
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    Product takeByProductID(int productID) throws DAOException;

    /**
     * The method add adds product to database by its data
     *
     * @param name        {@link String} product name
     * @param price       double product price
     * @param description {@link String} product description
     * @param quantity    int product quantity
     * @param photoURL    {@link String} product photo URL
     * @param categoryID  int category id
     * @param attributes  {@link Map} key: attribute name, value: attribute value
     * @return boolean true if adding is successful
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    boolean add(String name, double price, String description, int quantity, String photoURL, int categoryID, Map<String, String> attributes) throws DAOException;

    /**
     * The method remove removes product from database by its id
     *
     * @param productID int product id
     * @return boolean true if removing is successful
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    boolean remove(int productID) throws DAOException;

    /**
     * The method edit edits product by its id
     *
     * @param productID   int product id
     * @param name        {@link String} product name
     * @param price       double product price
     * @param description {@link String} product description
     * @param quantity    int product quantity
     * @param photoURL    {@link String} product photo URL
     * @param categoryID  int category id
     * @param attributes  {@link Map} key: attribute name, value: attribute value
     * @return boolean true if editing is successful
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    boolean edit(int productID, String name, double price, String description, int quantity, String photoURL, int categoryID, Map<String, String> attributes) throws DAOException;

    /**
     * The method buyProduct buys quantity products
     *
     * @param productID int product id
     * @param quantity  int product quantity
     * @return boolean true if buying is successful
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    boolean buyProduct(int productID, int quantity) throws DAOException;

    /**
     * The method orderProduct orders quantity products
     *
     * @param productID int product id
     * @param quantity  int product quantity
     * @return boolean true if ordering is successful
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    boolean orderProduct(int productID, int quantity) throws DAOException;
}
