package by.logvin.onlinestore.service;

import by.logvin.onlinestore.bean.Basket;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.service.exception.ServiceException;

public interface BasketService {
    Basket getBasketByUserID(int userID) throws ServiceException;
    boolean addProduct(Basket basket, Product product) throws ServiceException;
    boolean removeProduct(Basket basket, Product product) throws ServiceException;
    Basket deleteBasketByUserID(int userID) throws ServiceException;
}
