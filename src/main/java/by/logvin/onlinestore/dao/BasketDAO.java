package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.bean.Basket;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.dao.exception.DAOException;

public interface BasketDAO {
    Basket getBasketByUserID(int userID) throws DAOException;
    boolean addProduct(Basket basket, Product product) throws DAOException;
    boolean removeProduct(Basket basket, Product product) throws DAOException;
    Basket deleteBasketByUserID(int userID) throws DAOException;
    Basket createBasket(int userID) throws DAOException;
}
