package by.logvin.onlinestore.service;

import by.logvin.onlinestore.bean.Category;
import by.logvin.onlinestore.service.exception.ServiceException;

import java.util.List;

public interface CategoryService {
    boolean addCategory(String name) throws ServiceException;

    boolean deleteCategory(int categoryID) throws ServiceException;

    Category getCategory(int categoryID) throws ServiceException;

    List<Category> getCategories() throws ServiceException;

    Category getCategory(String name) throws ServiceException;
}
