package by.logvin.onlinestore.dao.impl;

import by.logvin.onlinestore.bean.Card;
import by.logvin.onlinestore.bean.Order;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.dao.OrderDAO;
import by.logvin.onlinestore.dao.exception.DAOException;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class SQLOrderDAO implements OrderDAO {
    @Override
    public List<Order> getUserOrders(int userID) throws DAOException {
        return null;
    }

    @Override
    public boolean makeOrder(Map<Product, Integer> products, Card card, Date date) throws DAOException {
        return false;
    }

    @Override
    public List<Order> getOrderLog() throws DAOException {
        return null;
    }
}
