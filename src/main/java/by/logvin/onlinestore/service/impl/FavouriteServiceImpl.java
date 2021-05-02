package by.logvin.onlinestore.service.impl;

import by.logvin.onlinestore.bean.Favourite;
import by.logvin.onlinestore.dao.DAOProvider;
import by.logvin.onlinestore.dao.FavouriteDAO;
import by.logvin.onlinestore.dao.exception.DAOException;
import by.logvin.onlinestore.service.FavouriteService;
import by.logvin.onlinestore.service.exception.ServiceException;
import org.apache.log4j.Logger;

/**
 * The class FavouriteServiceImpl is used for providing favourite data
 * @author bylogvin
 * @see by.logvin.onlinestore.service.FavouriteService
 */
public class FavouriteServiceImpl implements FavouriteService {

    private final static Logger logger = Logger.getLogger(FavouriteServiceImpl.class);

    @Override
    public Favourite takeFavouriteByUserID(int userID) throws ServiceException {
        FavouriteDAO favouriteDAO = DAOProvider.getInstance().getFavouriteDAO();
        Favourite favourite;
        try {
            favourite = favouriteDAO.takeFavouriteByUserID(userID);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during favourite getting");
            throw new ServiceException("Error during favourite getting", e);
        }
        return favourite;
    }

    @Override
    public boolean addProduct(int favouriteID, int productID) throws ServiceException {
        FavouriteDAO favouriteDAO = DAOProvider.getInstance().getFavouriteDAO();
        boolean isAdd;
        try {
            isAdd = favouriteDAO.addProduct(favouriteID, productID);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during favourite product adding");
            throw new ServiceException("Error during favourite product adding", e);
        }
        return isAdd;
    }

    @Override
    public boolean removeProduct(int favouriteID, int productID) throws ServiceException {
        FavouriteDAO favouriteDAO = DAOProvider.getInstance().getFavouriteDAO();
        boolean isRemove;
        try {
            isRemove = favouriteDAO.removeProduct(favouriteID, productID);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during favourite product removing");
            throw new ServiceException("Error during favourite product removing", e);
        }
        return isRemove;
    }

    @Override
    public boolean deleteFavouriteByUserID(int userID) throws ServiceException {
        FavouriteDAO favouriteDAO = DAOProvider.getInstance().getFavouriteDAO();
        boolean isDeleted;
        try {
            isDeleted = favouriteDAO.deleteFavouriteByUserID(userID);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during favourite deleting");
            throw new ServiceException("Error during favourite deleting", e);
        }
        return isDeleted;
    }

    @Override
    public boolean createFavourite(int userID) throws ServiceException {
        FavouriteDAO favouriteDAO = DAOProvider.getInstance().getFavouriteDAO();
        boolean isCreated;
        try {
            isCreated = favouriteDAO.createFavourite(userID);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during favourite creating");
            throw new ServiceException("Error during favourite creating", e);
        }
        return isCreated;
    }
}
