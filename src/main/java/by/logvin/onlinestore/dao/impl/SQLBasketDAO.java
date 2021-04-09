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
    public boolean addProduct(int basketID, int productID) throws DAOException {
        return false;
    }

    @Override
    public boolean removeProduct(int basketID, int productID) throws DAOException {
        return false;
    }

    @Override
    public boolean deleteBasketByUserID(int userID) throws DAOException {
        return false;
    }

    @Override
    public Basket createBasket(int userID) throws DAOException {
        return null;
    }
}
