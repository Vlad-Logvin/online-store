package by.logvin.onlinestore.service.impl;

import by.logvin.onlinestore.bean.Basket;
import by.logvin.onlinestore.dao.BasketDAO;
import by.logvin.onlinestore.dao.DAOProvider;
import by.logvin.onlinestore.dao.exception.DAOException;
import by.logvin.onlinestore.service.BasketService;
import by.logvin.onlinestore.service.exception.ServiceException;
import org.apache.log4j.Logger;

public class BasketServiceImpl implements BasketService {

    private final static Logger logger = Logger.getLogger(BasketServiceImpl.class);

    @Override
    public Basket getBasketByUserID(int userID) throws ServiceException {
        BasketDAO basketDAO = DAOProvider.getInstance().getBasketDAO();
        Basket basket = null;
        try {
            basket = basketDAO.getBasketByUserID(userID);
            logger.info("Basket gets: " + basket);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during basket getting by user id", e);
            throw new ServiceException("Error during basket getting by user id", e);
        }
        return basket;
    }

    @Override
    public boolean addProduct(int basketID, int productID) throws ServiceException {
        BasketDAO basketDAO = DAOProvider.getInstance().getBasketDAO();
        boolean isAdd = false;
        try {
            isAdd = basketDAO.addProduct(basketID, productID);
            logger.info("Basket product adds: " + isAdd);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during basket product adding", e);
            throw new ServiceException("Error during basket product adding", e);
        }
        return isAdd;
    }

    @Override
    public boolean removeProduct(int basketID, int productID) throws ServiceException {
        BasketDAO basketDAO = DAOProvider.getInstance().getBasketDAO();
        boolean isRemove = false;
        try {
            isRemove = basketDAO.removeProduct(basketID, productID);
            logger.info("Basket product removes: " + isRemove);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during basket product removing", e);
            throw new ServiceException("Error during basket product removing", e);
        }
        return isRemove;
    }

    @Override
    public boolean deleteBasketByUserID(int userID) throws ServiceException {
        BasketDAO basketDAO = DAOProvider.getInstance().getBasketDAO();
        boolean isDelete = false;
        try {
            isDelete = basketDAO.deleteBasketByUserID(userID);
            logger.info("Basket deletes: " + isDelete);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during basket deleting by user id", e);
            throw new ServiceException("Error during basket deleting by user id", e);
        }
        return isDelete;
    }

    @Override
    public boolean createBasket(int userID) throws ServiceException {
        BasketDAO basketDAO = DAOProvider.getInstance().getBasketDAO();
        boolean isCreate = false;
        try {
            isCreate = basketDAO.createBasket(userID);
            logger.info("Basket creates: " + isCreate);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during basket creating", e);
            throw new ServiceException("Error during basket creating", e);
        }
        return isCreate;
    }
}
