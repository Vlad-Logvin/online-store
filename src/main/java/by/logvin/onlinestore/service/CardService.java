package by.logvin.onlinestore.service;

import by.logvin.onlinestore.bean.Card;
import by.logvin.onlinestore.service.exception.ServiceException;

import java.util.List;

public interface CardService {
    Card getCardByUserID(int userID) throws ServiceException;

    List<Card> getCardsByUserID(int userID) throws ServiceException;

    boolean addCard(long number, int validityPeriod, int authenticationCode, String cardholder, int userID) throws ServiceException;

    boolean deleteCard(int cardID) throws ServiceException;

    boolean editCardInfo(int cardID, long number, int validityPeriod, int authenticationCode, String cardholder) throws ServiceException;
}
