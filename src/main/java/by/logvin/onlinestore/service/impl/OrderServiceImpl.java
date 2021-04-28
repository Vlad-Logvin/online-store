package by.logvin.onlinestore.service.impl;

import by.logvin.onlinestore.bean.Order;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.dao.DAOProvider;
import by.logvin.onlinestore.dao.OrderDAO;
import by.logvin.onlinestore.dao.exception.DAOException;
import by.logvin.onlinestore.service.OrderService;
import by.logvin.onlinestore.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private final static Logger logger = Logger.getLogger(OrderServiceImpl.class);

    @Override
    public List<Order> takeUserOrders(int userID) throws ServiceException {
        OrderDAO orderDAO = DAOProvider.getInstance().getOrderDAO();
        List<Order> orders = null;
        try {
            orders = orderDAO.takeUserOrders(userID);
            logger.info("Orders get: " + orders);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during orders getting", e);
            throw new ServiceException("Error during orders getting", e);
        }
        return orders;
    }

    @Override
    public boolean makeOrder(int userID, Map<Product, Integer> products, int cardID, Timestamp dateOfPurchase) throws ServiceException {
        OrderDAO orderDAO = DAOProvider.getInstance().getOrderDAO();
        boolean isMade = false;
        try {
            isMade = orderDAO.makeOrder(userID, products, cardID, dateOfPurchase);
            logger.info("Order makes: " + isMade);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during order making", e);
            throw new ServiceException("Error during order making", e);
        }
        return isMade;
    }

    @Override
    public List<Order> takeOrderLog() throws ServiceException {
        OrderDAO orderDAO = DAOProvider.getInstance().getOrderDAO();
        List<Order> orders = null;
        try {
            orders = orderDAO.takeOrderLog();
            logger.info("Orders get: " + orders);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during orders getting", e);
            throw new ServiceException("DAOException was thrown during orders getting", e);
        }
        return orders;
    }
}
