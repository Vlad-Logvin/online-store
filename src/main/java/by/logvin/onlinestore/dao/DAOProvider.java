package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.dao.impl.*;

public final class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();
    private DAOProvider() {

    }
    public static DAOProvider getInstance() {
        return instance;
    }

    private final UserDAO userDAO = new SQLUserDAO();
    public UserDAO getUserDAO() {
        return userDAO;
    }

    private final ProductDAO productDAO = new SQLProductDAO();
    public ProductDAO getProductDAO() {
        return productDAO;
    }

    private final BasketDAO basketDAO = new SQLBasketDAO();
    public BasketDAO getBasketDAO() {
        return basketDAO;
    }

    private final CardDAO cardDAO = new SQLCardDAO();
    public CardDAO getCardDAO() {
        return cardDAO;
    }

    private final OrderDAO orderDAO = new SQLOrderDAO();
    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    private final FavouriteDAO favouriteDAO = new SQLFavouriteDAO();
    public FavouriteDAO getFavouriteDAO() {
        return favouriteDAO;
    }
}
