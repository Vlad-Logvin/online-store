package by.logvin.onlinestore.service.impl;

import by.logvin.onlinestore.bean.Card;
import by.logvin.onlinestore.service.CardService;
import by.logvin.onlinestore.service.exception.ServiceException;

import java.util.List;

public class CardServiceImpl implements CardService {
    @Override
    public Card getCardByUserID(int userID) throws ServiceException {
        return null;
    }

    @Override
    public List<Card> getCardsByUserID(int userID) throws ServiceException {
        return null;
    }

    @Override
    public boolean addCard(long number, int validityPeriod, int authenticationCode, String cardholder, int userID) throws ServiceException {
        return false;
    }

    @Override
    public boolean deleteCard(int cardID) throws ServiceException {
        return false;
    }

    @Override
    public boolean editCardInfo(int cardID, long number, int validityPeriod, int authenticationCode, String cardholder) throws ServiceException {
        return false;
    }
}
