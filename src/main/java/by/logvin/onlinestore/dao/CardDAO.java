package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.bean.Card;
import by.logvin.onlinestore.dao.exception.DAOException;

import java.util.List;

/**
 * The CardDAO interface is used to manipulate card data in the database
 * @author bylogvin
 */
public interface CardDAO {

    /**
     * The method takeCard takes card by card id
     *
     * @param cardID card id
     * @return {@link Card} user card
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    Card takeCard(int cardID) throws DAOException;

    /**
     * The method takeCardsByUserID takes cards by user id
     *
     * @param userID user id
     * @return {@link List} of user cards
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    List<Card> takeCardsByUserID(int userID) throws DAOException;

    /**
     * The method addCard adds card using card data by user id
     *
     * @param number             card number
     * @param validityPeriod     card validity period
     * @param authenticationCode card authentication code
     * @param cardholder         card cardholder
     * @param userID             user id
     * @return boolean true if adding is successful
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    boolean addCard(long number, int validityPeriod, int authenticationCode, String cardholder, int userID) throws DAOException;

    /**
     * The method deleteCard deletes card by card id
     *
     * @param cardID card id
     * @return boolean true if deleting is successful
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    boolean deleteCard(int cardID) throws DAOException;

    /**
     * The method editCardInfo edits card data by card id
     *
     * @param cardID             card id
     * @param number             card number
     * @param validityPeriod     card validity period
     * @param authenticationCode card authentication code
     * @param cardholder         card cardholder
     * @return boolean true if editing is successful
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    boolean editCardInfo(int cardID, long number, int validityPeriod, int authenticationCode, String cardholder) throws DAOException;
}
