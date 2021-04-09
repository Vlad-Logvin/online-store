package by.logvin.onlinestore.service;

import by.logvin.onlinestore.bean.Favourite;
import by.logvin.onlinestore.service.exception.ServiceException;

public interface FavouriteService {
    Favourite getFavouriteByUserID(int userID) throws ServiceException;

    boolean addProduct(int favouriteID, int productID) throws ServiceException;

    boolean removeProduct(int favouriteID, int productID) throws ServiceException;

    boolean deleteFavouriteByUserID(int userID) throws ServiceException;

    Favourite createFavourite(int userID) throws ServiceException;
}
