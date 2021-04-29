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
    public Basket takeBasketByUserID(int userID) throws ServiceException {
        BasketDAO basketDAO = DAOProvider.getInstance().getBasketDAO();
        Basket basket;
        try {
            basket = basketDAO.takeBasketByUserID(userID);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during basket getting by user id");
            throw new ServiceException("Error during basket getting by user id", e);
        }
        return basket;
    }

    @Override
    public boolean addProduct(int basketID, int productID) throws ServiceException {
        BasketDAO basketDAO = DAOProvider.getInstance().getBasketDAO();
        boolean isAdd;
        try {
            isAdd = basketDAO.addProduct(basketID, productID);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during basket product adding");
            throw new ServiceException("Error during basket product adding", e);
        }
        return isAdd;
    }

    @Override
    public boolean removeProduct(int basketID, int productID) throws ServiceException {
        BasketDAO basketDAO = DAOProvider.getInstance().getBasketDAO();
        boolean isRemove;
        try {
            isRemove = basketDAO.removeProduct(basketID, productID);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during basket product removing");
            throw new ServiceException("Error during basket product removing", e);
        }
        return isRemove;
    }

    @Override
    public boolean deleteBasketByUserID(int userID) throws ServiceException {
        BasketDAO basketDAO = DAOProvider.getInstance().getBasketDAO();
        boolean isDelete;
        try {
            isDelete = basketDAO.deleteBasketByUserID(userID);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during basket deleting by user id");
            throw new ServiceException("Error during basket deleting by user id", e);
        }
        return isDelete;
    }

    @Override
    public boolean createBasket(int userID) throws ServiceException {
        BasketDAO basketDAO = DAOProvider.getInstance().getBasketDAO();
        boolean isCreate;
        try {
            isCreate = basketDAO.createBasket(userID);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during basket creating");
            throw new ServiceException("Error during basket creating", e);
        }
        return isCreate;
    }
}
