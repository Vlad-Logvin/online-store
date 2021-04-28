package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.bean.Basket;
import by.logvin.onlinestore.dao.exception.DAOException;

public interface BasketDAO {
    Basket takeBasketByUserID(int userID) throws DAOException;

    boolean addProduct(int basketID, int productID) throws DAOException;

    boolean removeProduct(int basketID, int productID) throws DAOException;

    boolean deleteBasketByUserID(int userID) throws DAOException;

    boolean createBasket(int userID) throws DAOException;
}
