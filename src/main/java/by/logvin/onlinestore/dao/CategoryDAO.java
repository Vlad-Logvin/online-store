package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.bean.Category;
import by.logvin.onlinestore.dao.exception.DAOException;

import java.util.List;

/**
 * The CategoryDAO interface is used to manipulate category data in the database
 * @author bylogvin
 */
public interface CategoryDAO {

    /**
     * The method addCategory adds category called by input parameter {@link String} name
     *
     * @param name {@link String} new category name
     * @return boolean true if adding is successful
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    boolean addCategory(String name) throws DAOException;

    /**
     * The method deleteCategory deletes category by its id
     *
     * @param categoryID int category id
     * @return boolean true if deleting is successful
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    boolean deleteCategory(int categoryID) throws DAOException;

    /**
     * The overload method takeCategory takes category by its id
     *
     * @param categoryID int category id
     * @return {@link Category} from database by category id
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    Category takeCategory(int categoryID) throws DAOException;

    /**
     * The method takeCategories takes all categories
     *
     * @return {@link List} of all categories
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    List<Category> takeCategories() throws DAOException;

    /**
     * The overload method takeCategory takes category by its name
     *
     * @param name {@link String} category name
     * @return {@link Category} from database by category name
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    Category takeCategory(String name) throws DAOException;
}
