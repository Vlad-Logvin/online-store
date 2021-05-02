package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.bean.Basket;
import by.logvin.onlinestore.dao.exception.DAOException;

/**
 * The BasketDAO interface is used to manipulate basket data in the database
 * @author bylogvin
 */
public interface BasketDAO {

    /**
     * The method takeBasketByUserID takes user basket from database by user id
     *
     * @param userID int user id
     * @return {@link Basket} user basket
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    Basket takeBasketByUserID(int userID) throws DAOException;

    /**
     * The method addProduct adds product to basket by product id
     *
     * @param basketID  int basket id
     * @param productID int product id
     * @return boolean true if adding is successful
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    boolean addProduct(int basketID, int productID) throws DAOException;

    /**
     * The method removeProduct removes product from basket by product id
     *
     * @param basketID  int basket id
     * @param productID int product id
     * @return boolean true if removing is successful
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    boolean removeProduct(int basketID, int productID) throws DAOException;

    /**
     * The method deleteBasketByUserID deletes basket by user id
     *
     * @param userID int user id
     * @return boolean true if deleting basket is successful
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    boolean deleteBasketByUserID(int userID) throws DAOException;

    /**
     * The method createBasket creates basket by user id
     *
     * @param userID int user id
     * @return boolean true if creating basket is successful
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    boolean createBasket(int userID) throws DAOException;
}
