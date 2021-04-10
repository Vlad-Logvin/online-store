package by.logvin.onlinestore.service.impl;

import by.logvin.onlinestore.bean.Category;
import by.logvin.onlinestore.dao.CategoryDAO;
import by.logvin.onlinestore.dao.DAOProvider;
import by.logvin.onlinestore.dao.exception.DAOException;
import by.logvin.onlinestore.service.CategoryService;
import by.logvin.onlinestore.service.exception.ServiceException;
import org.apache.log4j.Logger;

public class CategoryServiceImpl implements CategoryService {

    private final static Logger logger = Logger.getLogger(CategoryServiceImpl.class);

    @Override
    public boolean addCategory(String name) throws ServiceException {
        CategoryDAO categoryDAO = DAOProvider.getInstance().getCategoryDAO();
        boolean isAdd = false;
        try {
            isAdd = categoryDAO.addCategory(name);
            logger.info("Category adds: " + isAdd);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during category adding", e);
            throw new ServiceException("Error during category adding", e);
        }
        return isAdd;
    }

    @Override
    public boolean deleteCategory(int categoryID) throws ServiceException {
        CategoryDAO categoryDAO = DAOProvider.getInstance().getCategoryDAO();
        boolean isDelete = false;
        try {
            isDelete = categoryDAO.deleteCategory(categoryID);
            logger.info("Category deletes: " + isDelete);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during category deleting", e);
            throw new ServiceException("Error during category deleting", e);
        }
        return isDelete;
    }

    @Override
    public Category getCategory(int categoryID) throws ServiceException {
        CategoryDAO categoryDAO = DAOProvider.getInstance().getCategoryDAO();
        Category category = null;
        try {
            category = categoryDAO.getCategory(categoryID);
            logger.info("Category gets: " + category);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during category getting", e);
            throw new ServiceException("Error during category getting", e);
        }
        return category;
    }
}
