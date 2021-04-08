package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.bean.Card;
import by.logvin.onlinestore.dao.exception.DAOException;

import java.util.List;

public interface CardDAO {
    Card getCardByUserID(int userID) throws DAOException;
    List<Card> getCardsByUserID(int userID) throws DAOException;
    boolean addCard(Card card) throws DAOException;
    boolean deleteCard(Card card) throws DAOException;
    boolean editCardInfo(Card card) throws DAOException;
}
