package by.logvin.onlinestore.service;

import by.logvin.onlinestore.bean.Attribute;
import by.logvin.onlinestore.bean.Category;
import by.logvin.onlinestore.bean.Criteria;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.service.exception.ServiceException;

import java.util.List;

public interface ProductService {
    Product take() throws ServiceException;

    List<Product> take(int number) throws ServiceException;

    List<Product> takeAll() throws ServiceException;

    List<Product> take(Criteria criteria) throws ServiceException;

    Product takeByProductID(int productID) throws ServiceException;

    boolean add(String name, double price, String description, int quantity, String photoURL, Category category, List<Attribute> attributes) throws ServiceException;

    boolean remove(int productID) throws ServiceException;

    boolean edit(int productID, String name, double price, String description, int quantity, String photoURL, Category category, List<Attribute> attributes) throws ServiceException;
}