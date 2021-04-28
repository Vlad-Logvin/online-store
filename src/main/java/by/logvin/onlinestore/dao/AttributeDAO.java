package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.bean.Attribute;
import by.logvin.onlinestore.dao.exception.DAOException;

import java.util.List;
import java.util.Map;

public interface AttributeDAO {
    boolean addAttributes(int productID, Map<String, String> attributes) throws DAOException;

    List<Attribute> takeAttributes(int productID) throws DAOException;

    boolean deleteAttributes(int productID) throws DAOException;
}
