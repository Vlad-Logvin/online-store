package by.logvin.onlinestore.service;

import by.logvin.onlinestore.bean.Category;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface ProductService {
    Product take() throws ServiceException;

    List<Product> take(int number) throws ServiceException;

    List<Product> takeAll() throws ServiceException;

    List<Product> take(Category category) throws ServiceException;

    Product takeByProductID(int productID) throws ServiceException;

    boolean add(String name, double price, String description, int quantity, String photoURL, int categoryID, Map<String, String> attributes) throws ServiceException;

    boolean remove(int productID) throws ServiceException;

    boolean edit(int productID, String name, double price, String description, int quantity, String photoURL, int categoryID, Map<String, String> attributes) throws ServiceException;

    boolean buyProduct(int productID, int quantity) throws ServiceException;

    boolean orderProduct(int productID, int quantity) throws ServiceException;
}