package by.logvin.onlinestore.service.impl;

import by.logvin.onlinestore.bean.Order;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.dao.DAOProvider;
import by.logvin.onlinestore.dao.OrderDAO;
import by.logvin.onlinestore.dao.exception.DAOException;
import by.logvin.onlinestore.service.OrderService;
import by.logvin.onlinestore.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private final static Logger logger = Logger.getLogger(OrderServiceImpl.class);

    @Override
    public List<Order> getUserOrders(int userID) throws ServiceException {
        OrderDAO orderDAO = DAOProvider.getInstance().getOrderDAO();
        List<Order> orders = null;
        try {
            orders = orderDAO.getUserOrders(userID);
            logger.info("Orders get: " + orders);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during orders getting", e);
            throw new ServiceException("Error during orders getting", e);
        }
        return orders;
    }

    @Override
    public boolean makeOrder(int userID, Map<Product, Integer> products, int cardID, Date dateOfPurchase) throws ServiceException {
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
    public List<Order> getOrderLog() throws ServiceException {
        OrderDAO orderDAO = DAOProvider.getInstance().getOrderDAO();
        List<Order> orders = null;
        try {
            orders = orderDAO.getOrderLog();
            logger.info("Orders get: " + orders);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during orders getting", e);
            throw new ServiceException("DAOException was thrown during orders getting", e);
        }
        return orders;
    }
}
