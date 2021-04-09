package by.logvin.onlinestore.service.impl;

import by.logvin.onlinestore.bean.Card;
import by.logvin.onlinestore.bean.Order;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.service.OrderService;
import by.logvin.onlinestore.service.exception.ServiceException;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    @Override
    public List<Order> getUserOrders(int userID) throws ServiceException {
        return null;
    }

    @Override
    public boolean makeOrder(Map<Product, Integer> products, Card card, Date date) throws ServiceException {
        return false;
    }

    @Override
    public List<Order> getOrderLog() throws ServiceException {
        return null;
    }
}
