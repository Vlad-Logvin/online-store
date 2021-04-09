package by.logvin.onlinestore.service.impl;

import by.logvin.onlinestore.bean.Attribute;
import by.logvin.onlinestore.service.AttributeService;
import by.logvin.onlinestore.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public class AttributeServiceImpl implements AttributeService {
    @Override
    public boolean addAttributes(int productID, Map<String, String> attributes) throws ServiceException {
        return false;
    }

    @Override
    public boolean updateAttributes(int productID, Map<String, String> attributes) throws ServiceException {
        return false;
    }

    @Override
    public List<Attribute> getAttributes(int productID) throws ServiceException {
        return null;
    }
}
