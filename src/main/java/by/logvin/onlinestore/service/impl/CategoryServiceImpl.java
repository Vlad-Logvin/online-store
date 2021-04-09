package by.logvin.onlinestore.service.impl;

import by.logvin.onlinestore.bean.Category;
import by.logvin.onlinestore.service.CategoryService;
import by.logvin.onlinestore.service.exception.ServiceException;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public boolean addCategory(String name) throws ServiceException {
        return false;
    }

    @Override
    public boolean deleteCategory(int categoryID) throws ServiceException {
        return false;
    }

    @Override
    public Category getCategory(int categoryID) throws ServiceException {
        return null;
    }
}
