package by.logvin.onlinestore.service;

import by.logvin.onlinestore.bean.Card;
import by.logvin.onlinestore.service.exception.ServiceException;

import java.util.List;

/**
 * The interface CardService provides card data
 *
 * @author bylogvin
 */
public interface CardService {
    /**
     * The method takeCard provides taking card by card id
     *
     * @param cardID int card id
     * @return {@link Card} by card id
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    Card takeCard(int cardID) throws ServiceException;

    /**
     * The method takeCardByUserID provides taking cards by user id
     *
     * @param userID int user id
     * @return user {@link List} of cards
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    List<Card> takeCardsByUserID(int userID) throws ServiceException;

    /**
     * The method addCard provides adding card using card data by user id
     *
     * @param number             long card number
     * @param validityPeriod     int card validity period
     * @param authenticationCode int card authentication code
     * @param cardholder         {@link String} card cardholder
     * @param userID             int user id
     * @return boolean if adding is successful
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    boolean addCard(long number, int validityPeriod, int authenticationCode, String cardholder, int userID) throws ServiceException;

    /**
     * The method deleteCard provides deleting card by card id
     *
     * @param cardID int card id
     * @return boolean true if deleting is successful
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    boolean deleteCard(int cardID) throws ServiceException;

    /**
     * The method editCardInfo provides editing card information by card id
     *
     * @param cardID             int card id
     * @param number             long card number
     * @param validityPeriod     int card validity period
     * @param authenticationCode int card authentication code
     * @param cardholder         {@link String} card cardholder
     * @return boolean true if editing is successful
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    boolean editCardInfo(int cardID, long number, int validityPeriod, int authenticationCode, String cardholder) throws ServiceException;
}
