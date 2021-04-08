package by.logvin.onlinestore.service.impl;

import by.logvin.onlinestore.bean.Criteria;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.dao.DAOProvider;
import by.logvin.onlinestore.dao.ProductDAO;
import by.logvin.onlinestore.dao.exception.DAOException;
import by.logvin.onlinestore.service.ProductService;
import by.logvin.onlinestore.service.exception.ServiceException;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public Product takeByProductID(int productID) throws ServiceException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAO();
        Product product = null;
        try {
            product = productDAO.takeByProductID(productID);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return product;
    }

    @Override
    public boolean add(Product product) throws ServiceException {
        return false;
    }

    @Override
    public boolean remove(Product product) throws ServiceException {
        return false;
    }

    @Override
    public boolean edit(Product product) throws ServiceException {
        return false;
    }

    @Override
    public Product take() throws ServiceException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAO();
        Product product = null;
        try {
            product = productDAO.take();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return product;
    }

    @Override
    public List<Product> take(int number) throws ServiceException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAO();
        List<Product> products = null;
        try {
            products = productDAO.take(number);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return products;
    }

    @Override
    public List<Product> takeAll() throws ServiceException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAO();
        List<Product> products = null;
        try {
            products = productDAO.takeAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return products;
    }

    @Override
    public List<Product> take(Criteria criteria) throws ServiceException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAO();
        List<Product> products = null;
        try {
            products = productDAO.take(criteria);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return products;
    }
}
