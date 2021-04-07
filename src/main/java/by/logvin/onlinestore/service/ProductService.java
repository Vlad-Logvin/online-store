package by.logvin.onlinestore.service;

import by.logvin.onlinestore.bean.Criteria;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.service.exception.ServiceException;

import java.util.List;

public interface ProductService {
    Product take() throws ServiceException;
    List<Product> take(int number) throws ServiceException;
    List<Product> takeAll() throws ServiceException;
    List<Product> take(Criteria criteria) throws ServiceException;
}
