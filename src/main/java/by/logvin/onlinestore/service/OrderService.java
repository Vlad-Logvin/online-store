package by.logvin.onlinestore.service;

import by.logvin.onlinestore.bean.Card;
import by.logvin.onlinestore.bean.Order;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.service.exception.ServiceException;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderService {
    boolean makeOrder(Map<Product, Integer> products, Card card, Date date) throws ServiceException;
    List<Order> getOrderLog() throws ServiceException;
    List<Order> getUserOrders(int userID) throws ServiceException;
}
