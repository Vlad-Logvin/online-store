package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.bean.Attribute;
import by.logvin.onlinestore.bean.Category;
import by.logvin.onlinestore.bean.Criteria;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.dao.exception.DAOException;

import java.util.List;
import java.util.Map;

public interface ProductDAO {
    Product take() throws DAOException;

    List<Product> take(int number) throws DAOException;

    List<Product> takeAll() throws DAOException;

    List<Product> take(Category category) throws DAOException;

    Product takeByProductID(int productID) throws DAOException;

    boolean add(String name, double price, String description, int quantity, String photoURL, int categoryID, Map<String, String> attributes) throws DAOException;

    boolean remove(int productID) throws DAOException;

    boolean edit(int productID, String name, double price, String description, int quantity, String photoURL, int categoryID, Map<String, String> attributes) throws DAOException;

    boolean buyProduct(int productID, int quantity) throws DAOException;

    boolean orderProduct(int productID, int quantity) throws DAOException;
}
