package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.bean.Favourite;
import by.logvin.onlinestore.dao.exception.DAOException;

/**
 * The FavouriteDAO interface is used to manipulate favourite data in the database
 * @author bylogvin
 */
public interface FavouriteDAO {

    /**
     * The method takeFavouriteByUserID takes favourite by user id
     *
     * @param userID user id
     * @return {@link Favourite} by user id
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    Favourite takeFavouriteByUserID(int userID) throws DAOException;

    /**
     * The method addProduct adds product by product id to favourite by favourite id
     *
     * @param favouriteID favourite id
     * @param productID product id
     * @return boolean true if adding is successful
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    boolean addProduct(int favouriteID, int productID) throws DAOException;

    /**
     * The method removeProduct removes product by product id from favourite by favourite id
     *
     * @param favouriteID favourite id
     * @param productID product id
     * @return boolean true if removing is successful
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    boolean removeProduct(int favouriteID, int productID) throws DAOException;

    /**
     * The method deleteFavouriteByUserID deletes favourite by user id
     *
     * @param userID user id
     * @return boolean true if deleting favourite is successful
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    boolean deleteFavouriteByUserID(int userID) throws DAOException;

    /**
     * The method createFavourite creates favourite by user id
     *
     * @param userID user id
     * @return boolean true if creating favourite is successful
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    boolean createFavourite(int userID) throws DAOException;
}
