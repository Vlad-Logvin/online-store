package by.logvin.onlinestore.service.impl;

import by.logvin.onlinestore.bean.Basket;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.service.BasketService;
import by.logvin.onlinestore.service.exception.ServiceException;

public class BasketServiceImpl implements BasketService {
    @Override
    public Basket getBasketByUserID(int userID) throws ServiceException {
        return null;
    }

    @Override
    public boolean addProduct(int basketID, int productID) throws ServiceException {
        return false;
    }

    @Override
    public boolean removeProduct(int basketID, int productID) throws ServiceException {
        return false;
    }

    @Override
    public boolean deleteBasketByUserID(int userID) throws ServiceException {
        return false;
    }

    @Override
    public Basket createBasket(int userID) throws ServiceException {
        return null;
    }
}
