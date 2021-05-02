package by.logvin.onlinestore.service;

import by.logvin.onlinestore.bean.Basket;
import by.logvin.onlinestore.service.exception.ServiceException;

/**
 * The interface BasketService provides basket data
 *
 * @author bylogvin
 */
public interface BasketService {

    /**
     * The method takeBasketByUserID provides taking basket by user id
     *
     * @param userID int user id
     * @return {@link Basket} by user id
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    Basket takeBasketByUserID(int userID) throws ServiceException;

    /**
     * The method addProduct provides adding product by product id to basket by basket id
     *
     * @param basketID  int basket id
     * @param productID int product id
     * @return boolean true if adding is successful
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    boolean addProduct(int basketID, int productID) throws ServiceException;

    /**
     * The method removeProduct provides removing product by product id from basket by basket id
     *
     * @param basketID  int basket id
     * @param productID int product id
     * @return boolean true if removing is successful
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    boolean removeProduct(int basketID, int productID) throws ServiceException;

    /**
     * The method deleteBasketByUserID provides deleting basket by user id
     *
     * @param userID int user id
     * @return boolean true if deleting basket is successful
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    boolean deleteBasketByUserID(int userID) throws ServiceException;

    /**
     * The method createBasket provides creating basket by user id
     *
     * @param userID int user id
     * @return boolean true if creating basket is successful
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    boolean createBasket(int userID) throws ServiceException;
}
