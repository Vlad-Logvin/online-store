package by.logvin.onlinestore.service;

import by.logvin.onlinestore.service.impl.ProductServiceImpl;
import by.logvin.onlinestore.service.impl.UserServiceImpl;

public class ServiceProvider {
    private final static ServiceProvider instance = new ServiceProvider();

    private ServiceProvider(){

    }

    private final UserService userService = new UserServiceImpl();

    public static ServiceProvider getInstance(){
        return instance;
    }

    public UserService getUserService(){
        return userService;
    }

    private final ProductService productService = new ProductServiceImpl();

    public ProductService getProductService(){
        return productService;
    }
}
