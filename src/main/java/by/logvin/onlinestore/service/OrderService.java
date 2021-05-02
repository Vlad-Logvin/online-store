package by.logvin.onlinestore.service;

import by.logvin.onlinestore.bean.Order;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.service.exception.ServiceException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * The interface FavouriteService provides favourite data
 *
 * @author bylogvin
 */
public interface OrderService {
    /**
     * The method makeOrder provides making order with user id and order input data
     *
     * @param userID int user id
     * @param products {@link Map} of product where key: {@link Product}, value: {@link Integer} number of products
     * @param cardID         int card id of card that the order was made on
     * @param dateOfPurchase {@link Timestamp} date of purchase where the order was made on
     * @return boolean true if making order is successful
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    boolean makeOrder(int userID, Map<Product, Integer> products, int cardID, Timestamp dateOfPurchase) throws ServiceException;

    /**
     * The method takeOrderLog provides taking all orders
     *
     * @return {@link List} of all orders
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    List<Order> takeOrderLog() throws ServiceException;

    /**
     * The method takeUserOrders provides taking user orders by user id
     *
     * @param userID int user id
     * @return {@link List} of user orders
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    List<Order> takeUserOrders(int userID) throws ServiceException;
}
