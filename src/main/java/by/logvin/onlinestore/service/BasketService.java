package by.logvin.onlinestore.service;

import by.logvin.onlinestore.bean.Basket;
import by.logvin.onlinestore.service.exception.ServiceException;

public interface BasketService {
    Basket getBasketByUserID(int userID) throws ServiceException;

    boolean addProduct(int basketID, int productID) throws ServiceException;

    boolean removeProduct(int basketID, int productID) throws ServiceException;

    boolean deleteBasketByUserID(int userID) throws ServiceException;

    Basket createBasket(int userID) throws ServiceException;
}
