package by.logvin.onlinestore.service;

import by.logvin.onlinestore.bean.Favourite;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.service.exception.ServiceException;

public interface FavouriteService {
    Favourite getFavouriteByUserID(int userID) throws ServiceException;
    boolean addProduct(Favourite favourite, Product product) throws ServiceException;
    boolean removeProduct(Favourite favourite, Product product) throws ServiceException;
    Favourite deleteFavouriteByUserID(int userID) throws ServiceException;
}
