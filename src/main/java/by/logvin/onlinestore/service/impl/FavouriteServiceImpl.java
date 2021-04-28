package by.logvin.onlinestore.service.impl;

import by.logvin.onlinestore.bean.Favourite;
import by.logvin.onlinestore.dao.DAOProvider;
import by.logvin.onlinestore.dao.FavouriteDAO;
import by.logvin.onlinestore.dao.exception.DAOException;
import by.logvin.onlinestore.service.FavouriteService;
import by.logvin.onlinestore.service.exception.ServiceException;
import org.apache.log4j.Logger;

public class FavouriteServiceImpl implements FavouriteService {

    private final static Logger logger = Logger.getLogger(FavouriteServiceImpl.class);

    @Override
    public Favourite takeFavouriteByUserID(int userID) throws ServiceException {
        FavouriteDAO favouriteDAO = DAOProvider.getInstance().getFavouriteDAO();
        Favourite favourite = null;
        try {
            favourite = favouriteDAO.takeFavouriteByUserID(userID);
            logger.info("Favourite gets: " + favourite);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during favourite getting", e);
            throw new ServiceException("Error during favourite getting", e);
        }
        return favourite;
    }

    @Override
    public boolean addProduct(int favouriteID, int productID) throws ServiceException {
        FavouriteDAO favouriteDAO = DAOProvider.getInstance().getFavouriteDAO();
        boolean isAdd = false;
        try {
            isAdd = favouriteDAO.addProduct(favouriteID, productID);
            logger.info("Favourite product adds: " + isAdd);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during favourite product adding", e);
            throw new ServiceException("Error during favourite product adding", e);
        }
        return isAdd;
    }

    @Override
    public boolean removeProduct(int favouriteID, int productID) throws ServiceException {
        FavouriteDAO favouriteDAO = DAOProvider.getInstance().getFavouriteDAO();
        boolean isRemove = false;
        try {
            isRemove = favouriteDAO.removeProduct(favouriteID, productID);
            logger.info("Favourite product removes: " + isRemove);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during favourite product removing", e);
            throw new ServiceException("Error during favourite product removing", e);
        }
        return isRemove;
    }

    @Override
    public boolean deleteFavouriteByUserID(int userID) throws ServiceException {
        FavouriteDAO favouriteDAO = DAOProvider.getInstance().getFavouriteDAO();
        boolean isDeleted = false;
        try {
            isDeleted = favouriteDAO.deleteFavouriteByUserID(userID);
            logger.info("Favourite deletes: " + isDeleted);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during favourite deleting", e);
            throw new ServiceException("Error during favourite deleting", e);
        }
        return isDeleted;
    }

    @Override
    public boolean createFavourite(int userID) throws ServiceException {
        FavouriteDAO favouriteDAO = DAOProvider.getInstance().getFavouriteDAO();
        boolean isCreated = false;
        try {
            isCreated = favouriteDAO.createFavourite(userID);
            logger.info("Favourite creates: " + isCreated);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during favourite creating", e);
            throw new ServiceException("Error during favourite creating", e);
        }
        return isCreated;
    }
}
