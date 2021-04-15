package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.bean.Order;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.dao.exception.DAOException;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface OrderDAO {
    boolean makeOrder(int userID, Map<Product, Integer> products, int cardID, Timestamp dateOfPurchase) throws DAOException;

    List<Order> getOrderLog() throws DAOException;

    List<Order> getUserOrders(int userID) throws DAOException;
}
