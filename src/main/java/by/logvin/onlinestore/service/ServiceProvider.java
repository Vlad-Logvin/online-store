package by.logvin.onlinestore.service;

import by.logvin.onlinestore.service.impl.*;

/**
 * The ServiceProvider class contain service instances
 *
 * @author bylogvin
 */
public final class ServiceProvider {
    /**
     * {@link ServiceProvider} instance
     */
    private final static ServiceProvider instance = new ServiceProvider();

    private ServiceProvider() {

    }

    /**
     * @return {@link ServiceProvider} instance
     */
    public static ServiceProvider getInstance() {
        return instance;
    }

    /**
     * {@link UserService} instance
     */
    private final UserService userService = new UserServiceImpl();

    public UserService getUserService() {
        return userService;
    }

    /**
     * {@link ProductService} instance
     */
    private final ProductService productService = new ProductServiceImpl();

    public ProductService getProductService() {
        return productService;
    }

    /**
     * {@link BasketService} instance
     */
    private final BasketService basketService = new BasketServiceImpl();

    public BasketService getBasketService() {
        return basketService;
    }

    /**
     * {@link CardService} instance
     */
    private final CardService cardService = new CardServiceImpl();

    public CardService getCardService() {
        return cardService;
    }

    /**
     * {@link FavouriteService} instance
     */
    private final FavouriteService favouriteService = new FavouriteServiceImpl();

    public FavouriteService getFavouriteService() {
        return favouriteService;
    }

    /**
     * {@link OrderService} instance
     */
    private final OrderService orderService = new OrderServiceImpl();

    public OrderService getOrderService() {
        return orderService;
    }

    /**
     * {@link AttributeService} instance
     */
    private final AttributeService attributeService = new AttributeServiceImpl();

    public AttributeService getAttributeService() {
        return attributeService;
    }

    /**
     * {@link CategoryService} instance
     */
    private final CategoryService categoryService = new CategoryServiceImpl();

    public CategoryService getCategoryService() {
        return categoryService;
    }
}
