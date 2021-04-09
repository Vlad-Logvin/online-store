package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.bean.Favourite;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.dao.exception.DAOException;

public interface FavouriteDAO {
    Favourite getFavouriteByUserID(int userID) throws DAOException;

    boolean addProduct(int favouriteID, int productID) throws DAOException;

    boolean removeProduct(int favouriteID, int productID) throws DAOException;

    boolean deleteFavouriteByUserID(int userID) throws DAOException;

    boolean createFavourite(int userID) throws DAOException;
}
