package by.logvin.onlinestore.service;

import by.logvin.onlinestore.bean.Category;
import by.logvin.onlinestore.service.exception.ServiceException;

import java.util.List;

/**
 * The interface CategoryService provides category data
 *
 * @author bylogvin
 */
public interface CategoryService {

    /**
     * The method addCategory provides adding category using category name
     *
     * @param name {@link String} category name
     * @return boolean true if adding is successful
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    boolean addCategory(String name) throws ServiceException;

    /**
     * The method deleteCategory provides deleting category by its id
     *
     * @param categoryID int category id
     * @return boolean true if deleting is successful
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    boolean deleteCategory(int categoryID) throws ServiceException;

    /**
     * The overload method takeCategory provides taking category by its id
     *
     * @param categoryID int category id
     * @return {@link Category} with category id
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    Category takeCategory(int categoryID) throws ServiceException;

    /**
     * The method takeCategories provides taking all categories
     *
     * @return {@link List} of all categories
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    List<Category> takeCategories() throws ServiceException;

    /**
     * The overload method takeCategory provides taking category by its name
     *
     * @param name {@link String}
     * @return {@link Category} with category name
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    Category takeCategory(String name) throws ServiceException;
}
