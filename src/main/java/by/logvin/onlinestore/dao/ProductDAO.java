package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.bean.Criteria;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.dao.exception.DAOException;

import java.util.List;

public interface ProductDAO {
    Product take() throws DAOException;
    List<Product> take(int number) throws DAOException;
    List<Product> takeAll() throws DAOException;
    List<Product> take(Criteria criteria) throws DAOException;
    Product takeByProductID(int productID) throws DAOException;
    boolean add(Product product) throws DAOException;
    boolean remove(Product product) throws DAOException;
    boolean edit(Product product) throws DAOException;
}
