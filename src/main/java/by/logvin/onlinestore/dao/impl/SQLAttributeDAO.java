package by.logvin.onlinestore.dao.impl;

import by.logvin.onlinestore.bean.Attribute;
import by.logvin.onlinestore.dao.AttributeDAO;
import by.logvin.onlinestore.dao.exception.DAOException;

import java.util.List;
import java.util.Map;

public class SQLAttributeDAO implements AttributeDAO {
    @Override
    public boolean addAttributes(int productID, Map<String, String> attributes) throws DAOException {
        return false;
    }

    @Override
    public boolean updateAttributes(int productID, Map<String, String> attributes) throws DAOException {
        return false;
    }

    @Override
    public List<Attribute> getAttributes(int productID) throws DAOException {
        return null;
    }
}
