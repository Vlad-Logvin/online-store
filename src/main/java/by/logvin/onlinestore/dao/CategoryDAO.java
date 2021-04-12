package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.bean.Category;
import by.logvin.onlinestore.dao.exception.DAOException;

import java.util.List;

public interface CategoryDAO {
    boolean addCategory(String name) throws DAOException;

    boolean deleteCategory(int categoryID) throws DAOException;

    Category getCategory(int categoryID) throws DAOException;

    List<Category> getCategories() throws DAOException;

    Category getCategory(String name) throws DAOException;
}
