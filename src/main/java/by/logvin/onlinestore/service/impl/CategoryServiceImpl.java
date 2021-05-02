package by.logvin.onlinestore.service.impl;

import by.logvin.onlinestore.bean.Category;
import by.logvin.onlinestore.dao.CategoryDAO;
import by.logvin.onlinestore.dao.DAOProvider;
import by.logvin.onlinestore.dao.exception.DAOException;
import by.logvin.onlinestore.service.CategoryService;
import by.logvin.onlinestore.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * The class CategoryServiceImpl is used for providing category data
 * @author bylogvin
 * @see by.logvin.onlinestore.service.CategoryService
 */
public class CategoryServiceImpl implements CategoryService {

    private final static Logger logger = Logger.getLogger(CategoryServiceImpl.class);

    @Override
    public boolean addCategory(String name) throws ServiceException {
        CategoryDAO categoryDAO = DAOProvider.getInstance().getCategoryDAO();
        boolean isAdd;
        try {
            isAdd = categoryDAO.addCategory(name);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during category adding");
            throw new ServiceException("Error during category adding", e);
        }
        return isAdd;
    }

    @Override
    public boolean deleteCategory(int categoryID) throws ServiceException {
        CategoryDAO categoryDAO = DAOProvider.getInstance().getCategoryDAO();
        boolean isDelete;
        try {
            isDelete = categoryDAO.deleteCategory(categoryID);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during category deleting");
            throw new ServiceException("Error during category deleting", e);
        }
        return isDelete;
    }

    @Override
    public Category takeCategory(int categoryID) throws ServiceException {
        CategoryDAO categoryDAO = DAOProvider.getInstance().getCategoryDAO();
        Category category;
        try {
            category = categoryDAO.takeCategory(categoryID);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during category getting");
            throw new ServiceException("Error during category getting", e);
        }
        return category;
    }

    @Override
    public List<Category> takeCategories() throws ServiceException {
        CategoryDAO categoryDAO = DAOProvider.getInstance().getCategoryDAO();
        List<Category> categories;
        try {
            categories = categoryDAO.takeCategories();
        } catch (DAOException e) {
            logger.error("DAOException was thrown during categories getting");
            throw new ServiceException("Error during categories getting", e);
        }
        return categories;
    }

    @Override
    public Category takeCategory(String name) throws ServiceException {
        CategoryDAO categoryDAO = DAOProvider.getInstance().getCategoryDAO();
        Category category;
        try {
            category = categoryDAO.takeCategory(name);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during category getting");
            throw new ServiceException("Error during category getting", e);
        }
        return category;
    }
}
