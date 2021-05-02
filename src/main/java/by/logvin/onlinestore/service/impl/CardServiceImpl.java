package by.logvin.onlinestore.service.impl;

import by.logvin.onlinestore.bean.Card;
import by.logvin.onlinestore.dao.CardDAO;
import by.logvin.onlinestore.dao.DAOProvider;
import by.logvin.onlinestore.dao.exception.DAOException;
import by.logvin.onlinestore.service.CardService;
import by.logvin.onlinestore.service.exception.ServiceException;
import by.logvin.onlinestore.service.validator.ValidatorProvider;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * The class CardServiceImpl is used for providing card data
 * @author bylogvin
 * @see by.logvin.onlinestore.service.CardService
 */
public class CardServiceImpl implements CardService {

    private final static Logger logger = Logger.getLogger(CardServiceImpl.class);

    @Override
    public Card takeCard(int cardID) throws ServiceException {
        CardDAO cardDAO = DAOProvider.getInstance().getCardDAO();
        Card card;
        try {
            card = cardDAO.takeCard(cardID);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during card getting");
            throw new ServiceException("Error during card getting", e);
        }
        return card;
    }

    @Override
    public List<Card> takeCardsByUserID(int userID) throws ServiceException {
        CardDAO cardDAO = DAOProvider.getInstance().getCardDAO();
        List<Card> cards;
        try {
            cards = cardDAO.takeCardsByUserID(userID);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during cards getting by user id");
            throw new ServiceException("Error during cards getting by user id", e);
        }
        return cards;
    }

    @Override
    public boolean addCard(long number, int validityPeriod, int authenticationCode, String cardholder, int userID) throws ServiceException {
        if (!ValidatorProvider.getInstance().getCardValidator().validate(number, validityPeriod, authenticationCode)) {
            logger.info("Not valid card data");
            return false;
        }
        CardDAO cardDAO = DAOProvider.getInstance().getCardDAO();
        boolean isAdd;
        try {
            isAdd = cardDAO.addCard(number, validityPeriod, authenticationCode, cardholder, userID);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during card adding");
            throw new ServiceException("Error during card adding", e);
        }
        return isAdd;
    }

    @Override
    public boolean deleteCard(int cardID) throws ServiceException {
        CardDAO cardDAO = DAOProvider.getInstance().getCardDAO();
        boolean isDelete;
        try {
            isDelete = cardDAO.deleteCard(cardID);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during card deleting");
            throw new ServiceException("DAOException was thrown during card deleting", e);
        }
        return isDelete;
    }

    @Override
    public boolean editCardInfo(int cardID, long number, int validityPeriod, int authenticationCode, String cardholder) throws ServiceException {
        if (!ValidatorProvider.getInstance().getCardValidator().validate(number, validityPeriod, authenticationCode)) {
            logger.info("Not valid card data");
            return false;
        }
        CardDAO cardDAO = DAOProvider.getInstance().getCardDAO();
        boolean isEdit;
        try {
            isEdit = cardDAO.editCardInfo(cardID, number, validityPeriod, authenticationCode, cardholder);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during card editing");
            throw new ServiceException("Error during card editing", e);
        }
        return isEdit;
    }
}
