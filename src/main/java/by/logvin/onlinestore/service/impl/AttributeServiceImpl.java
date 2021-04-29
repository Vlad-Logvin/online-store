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

import java.util.List;
import java.util.Map;

public class AttributeServiceImpl implements AttributeService {

    private final static Logger logger = Logger.getLogger(AttributeServiceImpl.class);

    @Override
    public boolean addAttributes(int productID, Map<String, String> attributes) throws ServiceException {
        AttributeDAO attributeDAO = DAOProvider.getInstance().getAttributeDAO();
        boolean isAdd;
        try {
            isAdd = attributeDAO.addAttributes(productID, attributes);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during attributes adding");
            throw new ServiceException("Error during attributes adding", e);
        }
        return isAdd;
    }

    @Override
    public boolean updateAttributes(int productID, Map<String, String> attributes) throws ServiceException {
        AttributeDAO attributeDAO = DAOProvider.getInstance().getAttributeDAO();
        boolean isUpdate;
        try {
            isUpdate = attributeDAO.deleteAttributes(productID);
            isUpdate = attributeDAO.addAttributes(productID, attributes) & isUpdate;
        } catch (DAOException e) {
            logger.error("DAOException was thrown during attributes updating");
            throw new ServiceException("Error during attributes updating", e);
        }
        return isUpdate;
    }

    @Override
    public List<Attribute> takeAttributes(int productID) throws ServiceException {
        AttributeDAO attributeDAO = DAOProvider.getInstance().getAttributeDAO();
        List<Attribute> attributes;
        try {
            attributes = attributeDAO.takeAttributes(productID);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during attributes getting");
            throw new ServiceException("Error during attributes getting", e);
        }
        return attributes;
    }

    @Override
    public boolean deleteAttributes(int productID) throws ServiceException {
        AttributeDAO attributeDAO = DAOProvider.getInstance().getAttributeDAO();
        boolean isDelete;
        try {
            isDelete = attributeDAO.deleteAttributes(productID);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during attributes deleting");
            throw new ServiceException("Error during attributes deleting", e);
        }
        return isDelete;
    }

    @Override
    public String takeParsedAttributes(int productID) throws ServiceException {
        AttributeDAO attributeDAO = DAOProvider.getInstance().getAttributeDAO();
        List<Attribute> attributes;
        String parsedAttributes;
        AttributeParser parser = new AttributeParserImpl();
        try {
            attributes = attributeDAO.takeAttributes(productID);
            parsedAttributes = parser.unparse(attributes);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during attributes getting");
            throw new ServiceException("Error during attributes getting", e);
        }
        return parsedAttributes;
    }
}
