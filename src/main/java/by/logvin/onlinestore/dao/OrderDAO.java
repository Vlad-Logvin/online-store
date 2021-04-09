package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.bean.Card;
import by.logvin.onlinestore.bean.Order;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.dao.exception.DAOException;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderDAO {
    boolean makeOrder(Map<Product, Integer> products, Card card, Date date) throws DAOException;
    List<Order> getOrderLog() throws DAOException;
    List<Order> getUserOrders(int userID) throws DAOException;
}
