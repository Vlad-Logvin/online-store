package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.bean.Card;
import by.logvin.onlinestore.dao.exception.DAOException;

import java.util.List;

public interface CardDAO {
    Card takeCard(int cardID) throws DAOException;

    List<Card> takeCardsByUserID(int userID) throws DAOException;

    boolean addCard(long number, int validityPeriod, int authenticationCode, String cardholder, int userID) throws DAOException;

    boolean deleteCard(int cardID) throws DAOException;

    boolean editCardInfo(int cardID, long number, int validityPeriod, int authenticationCode, String cardholder) throws DAOException;
}
