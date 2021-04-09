package by.logvin.onlinestore.service;

import by.logvin.onlinestore.bean.Attribute;
import by.logvin.onlinestore.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface AttributeService {
    boolean addAttributes(int productID, Map<String, String> attributes) throws ServiceException;

    boolean updateAttributes(int productID, Map<String, String> attributes) throws ServiceException;

    List<Attribute> getAttributes(int productID) throws ServiceException;
}
