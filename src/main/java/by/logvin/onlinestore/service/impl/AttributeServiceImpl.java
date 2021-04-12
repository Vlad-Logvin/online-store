package by.logvin.onlinestore.service.impl;

import by.logvin.onlinestore.bean.Attribute;
import by.logvin.onlinestore.dao.AttributeDAO;
import by.logvin.onlinestore.dao.DAOProvider;
import by.logvin.onlinestore.dao.exception.DAOException;
import by.logvin.onlinestore.service.AttributeService;
import by.logvin.onlinestore.service.exception.ServiceException;
import by.logvin.onlinestore.service.util.AttributeParser;
import by.logvin.onlinestore.service.util.impl.AttributeParserImpl;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AttributeServiceImpl implements AttributeService {

    private final static Logger logger = Logger.getLogger(AttributeServiceImpl.class);

    @Override
    public boolean addAttributes(int productID, Map<String, String> attributes) throws ServiceException {
        AttributeDAO attributeDAO = DAOProvider.getInstance().getAttributeDAO();
        boolean isAdd = false;
        try {
            isAdd = attributeDAO.addAttributes(productID, attributes);
            logger.info("Attributes add: " + isAdd);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during attributes adding", e);
            throw new ServiceException("Error during attributes adding", e);
        }
        return isAdd;
    }

    @Override
    public boolean updateAttributes(int productID, Map<String, String> attributes) throws ServiceException {
        AttributeDAO attributeDAO = DAOProvider.getInstance().getAttributeDAO();
        boolean isUpdate = false;
        try {
            isUpdate = attributeDAO.deleteAttributes(productID);
            isUpdate = attributeDAO.addAttributes(productID, attributes) & isUpdate;
            logger.info("Attributes update: " + isUpdate);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during attributes updating", e);
            throw new ServiceException("Error during attributes updating", e);
        }
        return isUpdate;
    }

    @Override
    public List<Attribute> getAttributes(int productID) throws ServiceException {
        AttributeDAO attributeDAO = DAOProvider.getInstance().getAttributeDAO();
        List<Attribute> attributes = null;
        try {
            attributes = attributeDAO.getAttributes(productID);
            logger.info("Attributes get: " + attributes);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during attributes getting", e);
            throw new ServiceException("Error during attributes getting", e);
        }
        return attributes;
    }

    @Override
    public boolean deleteAttributes(int productID) throws ServiceException {
        AttributeDAO attributeDAO = DAOProvider.getInstance().getAttributeDAO();
        boolean isDelete = false;
        try {
            isDelete = attributeDAO.deleteAttributes(productID);
            logger.info("Attributes delete: " + isDelete);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during attributes deleting", e);
            throw new ServiceException("Error during attributes deleting", e);
        }
        return isDelete;
    }

    @Override
    public String getParsedAttributes(int productID) throws ServiceException {
        AttributeDAO attributeDAO = DAOProvider.getInstance().getAttributeDAO();
        List<Attribute> attributes = null;
        String parsedAttributes = null;
        AttributeParser parser = new AttributeParserImpl();
        try {
            attributes = attributeDAO.getAttributes(productID);
            parsedAttributes = parser.unparse(attributes);
            logger.info("Attributes get: " + parsedAttributes);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during attributes getting", e);
            throw new ServiceException("Error during attributes getting", e);
        }
        return parsedAttributes;
    }
}
