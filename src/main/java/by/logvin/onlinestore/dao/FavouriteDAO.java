package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.bean.Favourite;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.dao.exception.DAOException;

public interface FavouriteDAO {
    Favourite getFavouriteByUserID(int userID) throws DAOException;
    boolean addProduct(Favourite favourite, Product product) throws DAOException;
    boolean removeProduct(Favourite favourite, Product product) throws DAOException;
    Favourite deleteFavouriteByUserID(int userID) throws DAOException;
    Favourite createFavourite(int userID) throws DAOException;
}
