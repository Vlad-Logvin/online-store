package by.logvin.onlinestore.dao.impl;

import by.logvin.onlinestore.bean.Basket;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.dao.BasketDAO;
import by.logvin.onlinestore.dao.exception.DAOException;

public class SQLBasketDAO implements BasketDAO {
    @Override
    public Basket getBasketByUserID(int userID) throws DAOException {
        return null;
    }

    @Override
    public boolean addProduct(Basket basket, Product product) throws DAOException {
        return false;
    }

    @Override
    public boolean removeProduct(Basket basket, Product product) throws DAOException {
        return false;
    }

    @Override
    public Basket deleteBasketByUserID(int userID) throws DAOException {
        return null;
    }
}
