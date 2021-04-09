package by.logvin.onlinestore.service.impl;

import by.logvin.onlinestore.bean.Favourite;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.service.FavouriteService;
import by.logvin.onlinestore.service.exception.ServiceException;

public class FavouriteServiceImpl implements FavouriteService {
    @Override
    public Favourite getFavouriteByUserID(int userID) throws ServiceException {
        return null;
    }

    @Override
    public boolean addProduct(Favourite favourite, Product product) throws ServiceException {
        return false;
    }

    @Override
    public boolean removeProduct(Favourite favourite, Product product) throws ServiceException {
        return false;
    }

    @Override
    public Favourite deleteFavouriteByUserID(int userID) throws ServiceException {
        return null;
    }

    @Override
    public Favourite createFavourite(int userID) throws ServiceException {
        return null;
    }
}
