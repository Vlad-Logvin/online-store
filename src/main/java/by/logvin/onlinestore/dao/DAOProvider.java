package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.dao.impl.SQLProductDAO;
import by.logvin.onlinestore.dao.impl.SQLUserDAO;

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
}
