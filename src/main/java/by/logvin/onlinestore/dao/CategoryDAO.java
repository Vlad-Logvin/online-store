package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.bean.Category;
import by.logvin.onlinestore.dao.exception.DAOException;

public interface CategoryDAO {
    boolean addCategory(String name) throws DAOException;

    boolean deleteCategory(int categoryID) throws DAOException;

    Category getCategory(int categoryID) throws DAOException;
}
