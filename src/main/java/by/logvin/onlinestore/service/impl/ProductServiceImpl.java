package by.logvin.onlinestore.service.impl;

import by.logvin.onlinestore.bean.Category;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.dao.DAOProvider;
import by.logvin.onlinestore.dao.ProductDAO;
import by.logvin.onlinestore.dao.exception.DAOException;
import by.logvin.onlinestore.service.ProductService;
import by.logvin.onlinestore.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * The class ProductServiceImpl is used for providing product data
 * @author bylogvin
 * @see by.logvin.onlinestore.service.ProductService
 */
public class ProductServiceImpl implements ProductService {

    private final static Logger logger = Logger.getLogger(ProductServiceImpl.class);

    @Override
    public boolean orderProduct(int productID, int quantity) throws ServiceException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAO();
        boolean isOrder;
        try {
            isOrder = productDAO.orderProduct(productID, quantity);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during product ordering");
            throw new ServiceException("Error during product ordering", e);
        }
        return isOrder;
    }

    @Override
    public Product takeByProductID(int productID) throws ServiceException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAO();
        Product product;
        try {
            product = productDAO.takeByProductID(productID);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during take product by it id");
            throw new ServiceException("Error during take product by it id", e);
        }
        return product;
    }

    @Override
    public boolean add(String name, double price, String description, int quantity, String photoURL, int categoryID, Map<String, String> attributes) throws ServiceException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAO();
        boolean isAdd;
        try {
            isAdd = productDAO.add(name, price, description, quantity, photoURL, categoryID, attributes);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during product adding");
            throw new ServiceException("Error during product adding", e);
        }
        return isAdd;
    }

    @Override
    public boolean remove(int productID) throws ServiceException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAO();
        boolean isRemove;
        try {
            isRemove = productDAO.remove(productID);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during product removing");
            throw new ServiceException("Error during product removing", e);
        }
        return isRemove;
    }

    @Override
    public boolean edit(int productID, String name, double price, String description, int quantity, String photoURL, int categoryID, Map<String, String> attributes) throws ServiceException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAO();
        boolean isEdit;
        try {
            isEdit = productDAO.edit(productID, name, price, description, quantity, photoURL, categoryID, attributes);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during product editing");
            throw new ServiceException("Error during product editing", e);
        }
        return isEdit;
    }

    @Override
    public Product take() throws ServiceException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAO();
        Product product;
        try {
            product = productDAO.take();
        } catch (DAOException e) {
            logger.error("DAOException was thrown during product taking");
            throw new ServiceException("Error during product taking", e);
        }
        return product;
    }

    @Override
    public List<Product> take(int number) throws ServiceException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAO();
        List<Product> products;
        try {
            products = productDAO.take(number);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during product taking");
            throw new ServiceException("Error during product taking", e);
        }
        return products;
    }

    @Override
    public List<Product> takeAll() throws ServiceException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAO();
        List<Product> products;
        try {
            products = productDAO.takeAll();
        } catch (DAOException e) {
            logger.error("DAOException was thrown during products taking");
            throw new ServiceException("Error during products taking", e);
        }
        return products;
    }

    @Override
    public List<Product> take(Category category) throws ServiceException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAO();
        List<Product> products;
        try {
            products = productDAO.take(category);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during products taking");
            throw new ServiceException("Error during products taking", e);
        }
        return products;
    }

    @Override
    public boolean buyProduct(int productID, int quantity) throws ServiceException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAO();
        boolean isBuy;
        try {
            isBuy = productDAO.buyProduct(productID, quantity);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during product buying");
            throw new ServiceException("Error during product buying", e);
        }
        return isBuy;
    }
}
