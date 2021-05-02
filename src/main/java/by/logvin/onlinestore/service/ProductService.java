package by.logvin.onlinestore.service;

import by.logvin.onlinestore.bean.Category;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * The interface ProductService provides product data
 *
 * @author bylogvin
 */
public interface ProductService {

    /**
     * The overload method take provides taking one random product
     *
     * @return One random {@link Product}
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    Product take() throws ServiceException;

    /**
     * The overload method take provides taking number of product
     *
     * @param number int number of need product
     * @return {@link List} of number of products
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    List<Product> take(int number) throws ServiceException;

    /**
     * The method takeAll provides taking all products
     *
     * @return {@link List} of all products
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    List<Product> takeAll() throws ServiceException;

    /**
     * The overload method take provides taking all products by category
     *
     * @param category {@link Category} of products
     * @return {@link List} of all products
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    List<Product> take(Category category) throws ServiceException;

    /**
     * The method takeByProductID provides taking product by its id
     *
     * @param productID int product id
     * @return {@link Product} with product id
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    Product takeByProductID(int productID) throws ServiceException;

    /**
     * The method add provides adding new product with product data
     *
     * @param name        {@link String} product name
     * @param price       double product price
     * @param description {@link String} product description
     * @param quantity    int product quantity
     * @param photoURL    {@link String} product photo URL
     * @param categoryID  int category id
     * @param attributes  {@link Map} key: attribute name, value: attribute value
     * @return boolean true if adding is successful
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    boolean add(String name, double price, String description, int quantity, String photoURL, int categoryID, Map<String, String> attributes) throws ServiceException;

    /**
     * The method remove provide removing product by its id
     *
     * @param productID int product id
     * @return boolean true if removing is successful
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    boolean remove(int productID) throws ServiceException;

    /**
     * The method edit provides editing product by its id with new product data
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
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    boolean edit(int productID, String name, double price, String description, int quantity, String photoURL, int categoryID, Map<String, String> attributes) throws ServiceException;

    /**
     * The method buyProduct provides buying quantity products
     *
     * @param productID int product id
     * @param quantity  int product quantity
     * @return boolean true if buying is successful
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    boolean buyProduct(int productID, int quantity) throws ServiceException;

    /**
     * The method orderProduct provides ordering quantity products
     *
     * @param productID int product id
     * @param quantity  int product quantity
     * @return boolean true if ordering is successful
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    boolean orderProduct(int productID, int quantity) throws ServiceException;
}