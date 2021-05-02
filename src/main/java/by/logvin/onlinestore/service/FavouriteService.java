package by.logvin.onlinestore.service;

import by.logvin.onlinestore.bean.Favourite;
import by.logvin.onlinestore.service.exception.ServiceException;

/**
 * The interface FavouriteService provides favourite data
 *
 * @author bylogvin
 */
public interface FavouriteService {
    /**
     * The method takeFavouriteByUserID provides taking favourite by user id
     *
     * @param userID int user id
     * @return {@link Favourite} with user id
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    Favourite takeFavouriteByUserID(int userID) throws ServiceException;

    /**
     * The method addProduct provides adding product by product id to favourite by favourite id
     *
     * @param favouriteID int favourite id
     * @param productID int product id
     * @return boolean true if adding is successful;
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    boolean addProduct(int favouriteID, int productID) throws ServiceException;

    /**
     * The method removeProduct provides removing product by product id from favourite by favourite id
     *
     * @param favouriteID int favourite id
     * @param productID int product id
     * @return boolean true if removing is successful;
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    boolean removeProduct(int favouriteID, int productID) throws ServiceException;

    /**
     * The method deleteFavouriteByUserID provides deleting favourite by user id
     *
     * @param userID int user id
     * @return boolean true if deleting favourite is successful
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    boolean deleteFavouriteByUserID(int userID) throws ServiceException;

    /**
     * The method createFavourite provides creating favourite by user id
     *
     * @param userID int user id
     * @return boolean true if creating favourite is successful
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    boolean createFavourite(int userID) throws ServiceException;
}
