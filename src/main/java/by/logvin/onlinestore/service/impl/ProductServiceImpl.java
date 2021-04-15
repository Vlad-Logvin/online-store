package by.logvin.onlinestore.service.impl;

import by.logvin.onlinestore.bean.Category;
import by.logvin.onlinestore.bean.Criteria;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.dao.DAOProvider;
import by.logvin.onlinestore.dao.ProductDAO;
import by.logvin.onlinestore.dao.exception.DAOException;
import by.logvin.onlinestore.service.ProductService;
import by.logvin.onlinestore.service.exception.ServiceException;
import by.logvin.onlinestore.service.util.ProductFilter;
import by.logvin.onlinestore.service.util.impl.CriteriaFilter;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {

    private final static Logger logger = Logger.getLogger(ProductServiceImpl.class);

    @Override
    public boolean orderProduct(int productID, int quantity) throws ServiceException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAO();
        boolean isOrder = false;
        try {
            isOrder = productDAO.orderProduct(productID, quantity);
            logger.info("Product ordering: " + isOrder);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during product ordering", e);
            throw new ServiceException("Error during product ordering", e);
        }
        return isOrder;
    }

    @Override
    public Product takeByProductID(int productID) throws ServiceException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAO();
        Product product = null;
        try {
            product = productDAO.takeByProductID(productID);
            logger.info("Product takes: " + product);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during take product by it id", e);
            throw new ServiceException("Error during take product by it id", e);
        }
        return product;
    }

    @Override
    public boolean add(String name, double price, String description, int quantity, String photoURL, int categoryID, Map<String, String> attributes) throws ServiceException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAO();
        boolean isAdd = false;
        try {
            isAdd = productDAO.add(name, price, description, quantity, photoURL, categoryID, attributes);
            logger.info("Product adds: " + isAdd);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during product adding", e);
            throw new ServiceException("Error during product adding", e);
        }
        return isAdd;
    }

    @Override
    public boolean remove(int productID) throws ServiceException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAO();
        boolean isRemove = false;
        try {
            isRemove = productDAO.remove(productID);
            logger.info("Product removes: " + isRemove);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during product removing", e);
            throw new ServiceException("Error during product removing", e);
        }
        return isRemove;
    }

    @Override
    public boolean edit(int productID, String name, double price, String description, int quantity, String photoURL, int categoryID, Map<String, String> attributes) throws ServiceException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAO();
        boolean isEdit = false;
        try {
            isEdit = productDAO.edit(productID, name, price, description, quantity, photoURL, categoryID, attributes);
            logger.info("Product edits: " + isEdit);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during product editing", e);
            throw new ServiceException("Error during product editing", e);
        }
        return isEdit;
    }

    @Override
    public Product take() throws ServiceException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAO();
        Product product = null;
        try {
            product = productDAO.take();
            logger.info("Product takes: " + product);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during product taking", e);
            throw new ServiceException("Error during product taking", e);
        }
        return product;
    }

    @Override
    public List<Product> take(int number) throws ServiceException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAO();
        List<Product> products = null;
        try {
            products = productDAO.take(number);
            logger.info("Products take: " + products);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during product taking", e);
            throw new ServiceException("Error during product taking", e);
        }
        return products;
    }

    @Override
    public List<Product> takeAll() throws ServiceException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAO();
        List<Product> products = null;
        try {
            products = productDAO.takeAll();
            logger.info("Products take: " + products);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during products taking", e);
            throw new ServiceException("Error during products taking", e);
        }
        return products;
    }

    @Override
    public List<Product> take(Criteria criteria) throws ServiceException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAO();
        List<Product> products = null;
        try {
            products = productDAO.takeAll();
            ProductFilter productFilter = new CriteriaFilter();
            products = productFilter.filter(products, criteria);
            logger.info("Products take: " + products);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during product taking", e);
            throw new ServiceException("Error during product taking", e);
        }
        return products;
    }

    @Override
    public List<Product> take(Category category) throws ServiceException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAO();
        List<Product> products = null;
        try {
            products = productDAO.take(category);
            logger.info("Products take: " + products);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during products taking", e);
            throw new ServiceException("Error during products taking", e);
        }
        return products;
    }

    @Override
    public boolean buyProduct(int productID, int quantity) throws ServiceException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAO();
        boolean isBuy = false;
        try {
            isBuy = productDAO.buyProduct(productID, quantity);
            logger.info("Product buying: " + isBuy);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during product buying", e);
            throw new ServiceException("Error during product buying", e);
        }
        return isBuy;
    }
}
