package by.logvin.onlinestore.dao.impl;

import by.logvin.onlinestore.bean.Favourite;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.dao.FavouriteDAO;
import by.logvin.onlinestore.dao.exception.DAOException;

public class SQLFavouriteDAO implements FavouriteDAO {
    @Override
    public Favourite getFavouriteByUserID(int userID) throws DAOException {
        return null;
    }

    @Override
    public boolean addProduct(int favouriteID, int productID) throws DAOException {
        return false;
    }

    @Override
    public boolean removeProduct(int favouriteID, int productID) throws DAOException {
        return false;
    }

    @Override
    public boolean deleteFavouriteByUserID(int userID) throws DAOException {
        return false;
    }

    @Override
    public Favourite createFavourite(int userID) throws DAOException {
        return null;
    }
}
