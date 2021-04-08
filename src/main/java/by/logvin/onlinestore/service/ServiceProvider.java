package by.logvin.onlinestore.service;

import by.logvin.onlinestore.service.impl.*;

public class ServiceProvider {
    private final static ServiceProvider instance = new ServiceProvider();
    private ServiceProvider(){

    }
    public static ServiceProvider getInstance(){
        return instance;
    }

    private final UserService userService = new UserServiceImpl();
    public UserService getUserService(){
        return userService;
    }

    private final ProductService productService = new ProductServiceImpl();
    public ProductService getProductService(){
        return productService;
    }

    private final BasketService basketService = new BasketServiceImpl();
    public BasketService getBasketService() {
        return basketService;
    }

    private final CardService cardService = new CardServiceImpl();
    public CardService getCardService() {
        return cardService;
    }

    private final FavouriteService favouriteService = new FavouriteServiceImpl();
    public FavouriteService getFavouriteService() {
        return favouriteService;
    }

    private final OrderService orderService = new OrderServiceImpl();
    public OrderService getOrderService() {
        return orderService;
    }
}
