package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.dao.impl.*;

/**
 * The DAOProvider class contains DAO interface instances
 * @author bylogvin
 */
public final class DAOProvider {
    /**
     * {@link DAOProvider} instance
     */
    private static final DAOProvider instance = new DAOProvider();
    private DAOProvider() {
    }
    /**
     *
     * @return instance of {@link DAOProvider}
     */
    public static DAOProvider getInstance() {
        return instance;
    }

    /**
     * {@link UserDAO} instance
     */
    private final UserDAO userDAO = new SQLUserDAO();
    public UserDAO getUserDAO() {
        return userDAO;
    }

    /**
     * {@link ProductDAO} instance
     */
    private final ProductDAO productDAO = new SQLProductDAO();
    public ProductDAO getProductDAO() {
        return productDAO;
    }

    /**
     * {@link BasketDAO} instance
     */
    private final BasketDAO basketDAO = new SQLBasketDAO();
    public BasketDAO getBasketDAO() {
        return basketDAO;
    }

    /**
     * {@link CardDAO} instance
     */
    private final CardDAO cardDAO = new SQLCardDAO();
    public CardDAO getCardDAO() {
        return cardDAO;
    }

    /**
     * {@link OrderDAO} instance
     */
    private final OrderDAO orderDAO = new SQLOrderDAO();
    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    /**
     * {@link FavouriteDAO} instance
     */
    private final FavouriteDAO favouriteDAO = new SQLFavouriteDAO();
    public FavouriteDAO getFavouriteDAO() {
        return favouriteDAO;
    }

    /**
     * {@link AttributeDAO} instance
     */
    private final AttributeDAO attributeDAO = new SQLAttributeDAO();
    public AttributeDAO getAttributeDAO(){
        return attributeDAO;
    }

    /**
     * {@link CategoryDAO} instance
     */
    private final CategoryDAO categoryDAO = new SQLCategoryDAO();
    public CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }
}
