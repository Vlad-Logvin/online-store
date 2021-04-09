package by.logvin.onlinestore.dao.impl;

import by.logvin.onlinestore.bean.Category;
import by.logvin.onlinestore.dao.CategoryDAO;
import by.logvin.onlinestore.dao.exception.DAOException;

public class SQLCategoryDAO implements CategoryDAO {

    @Override
    public boolean addCategory(String name) throws DAOException {
        return false;
    }

    @Override
    public boolean deleteCategory(int categoryID) throws DAOException {
        return false;
    }

    @Override
    public Category getCategory(int categoryID) throws DAOException {
        return null;
    }
}
