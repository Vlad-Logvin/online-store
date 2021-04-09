package by.logvin.onlinestore.service;

import by.logvin.onlinestore.bean.Card;
import by.logvin.onlinestore.service.exception.ServiceException;

import java.util.List;

public interface CardService {
    Card getCardByUserID(int userID) throws ServiceException;
    List<Card> getCardsByUserID(int userID) throws ServiceException;
    boolean addCard(Card card, int userID) throws ServiceException;
    boolean deleteCard(Card card, int userID) throws ServiceException;
    boolean editCardInfo(Card card, int userID) throws ServiceException;
}
