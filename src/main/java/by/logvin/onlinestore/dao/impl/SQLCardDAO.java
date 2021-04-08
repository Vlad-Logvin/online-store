package by.logvin.onlinestore.dao.impl;

import by.logvin.onlinestore.bean.Card;
import by.logvin.onlinestore.dao.CardDAO;
import by.logvin.onlinestore.dao.exception.DAOException;

import java.util.List;

public class SQLCardDAO implements CardDAO {
    @Override
    public Card getCardByUserID(int userID) throws DAOException {
        return null;
    }

    @Override
    public List<Card> getCardsByUserID(int userID) throws DAOException {
        return null;
    }

    @Override
    public boolean addCard(Card card) throws DAOException {
        return false;
    }

    @Override
    public boolean deleteCard(Card card) throws DAOException {
        return false;
    }

    @Override
    public boolean editCardInfo(Card card) throws DAOException {
        return false;
    }
}
