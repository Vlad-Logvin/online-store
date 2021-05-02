package by.logvin.onlinestore.service;

import by.logvin.onlinestore.bean.Attribute;
import by.logvin.onlinestore.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * The interface AttributeService provides attribute data
 *
 * @author bylogvin
 */
public interface AttributeService {

    /**
     * The method addAttributes provides adding attributes
     *
     * @param productID  int product id
     * @param attributes {@link Map} key: attribute name, value: attribute value
     * @return boolean true if adding is successful
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    boolean addAttributes(int productID, Map<String, String> attributes) throws ServiceException;

    /**
     * The method updateAttributes provides updating attributes
     *
     * @param productID  int product id
     * @param attributes {@link Map} key: attribute name, value: attribute value
     * @return boolean true if updating is successful
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    boolean updateAttributes(int productID, Map<String, String> attributes) throws ServiceException;

    /**
     * The method takeAttributes provides taking attributes by product id
     *
     * @param productID int product id
     * @return {@link List} of attributes suitable for the product
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    List<Attribute> takeAttributes(int productID) throws ServiceException;

    /**
     * The method takeParsedAttributes parse list of attributes to String
     *
     * @param productID int product id
     * @return {@link String} parsed String
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    String takeParsedAttributes(int productID) throws ServiceException;

    /**
     * The method deleteAttributes provides deleting attributes
     *
     * @param productID int product id
     * @return boolean true if deleting is successful
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    boolean deleteAttributes(int productID) throws ServiceException;
}
