package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.bean.Order;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.dao.exception.DAOException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * The OrderDAO interface is used to manipulate order data in the database
 * @author bylogvin
 */
public interface OrderDAO {

    /**
     * The method makeOrder makes order by order data input
     *
     * @param userID         int user id
     * @param products       {@link Map} key: product, value: number of products
     * @param cardID         int card id of card that the order was made on
     * @param dateOfPurchase {@link Timestamp} date of purchase where the order was made on
     * @return boolean true if making order is successful
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    boolean makeOrder(int userID, Map<Product, Integer> products, int cardID, Timestamp dateOfPurchase) throws DAOException;

    /**
     * The method takeOrderLog takes all orders from database
     *
     * @return {@link List} of all orders
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    List<Order> takeOrderLog() throws DAOException;

    /**
     * The method takeUserOrders takes user orders by user id
     *
     * @param userID int user id
     * @return {@link List} of user orders
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    List<Order> takeUserOrders(int userID) throws DAOException;
}
