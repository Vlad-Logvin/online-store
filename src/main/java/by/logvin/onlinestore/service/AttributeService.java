package by.logvin.onlinestore.service;

import by.logvin.onlinestore.bean.Attribute;
import by.logvin.onlinestore.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface AttributeService {
    boolean addAttributes(int productID, Map<String, String> attributes) throws ServiceException;

    boolean updateAttributes(int productID, Map<String, String> attributes) throws ServiceException;

    List<Attribute> takeAttributes(int productID) throws ServiceException;

    String takeParsedAttributes(int productID) throws ServiceException;

    boolean deleteAttributes(int productID) throws ServiceException;
}
