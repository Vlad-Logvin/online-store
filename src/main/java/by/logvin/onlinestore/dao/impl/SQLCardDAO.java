package by.logvin.onlinestore.dao.impl;

import by.logvin.onlinestore.bean.Card;
import by.logvin.onlinestore.dao.CardDAO;
import by.logvin.onlinestore.dao.connection.ConnectionPool;
import by.logvin.onlinestore.dao.connection.ConnectionPoolException;
import by.logvin.onlinestore.dao.exception.DAOException;
import by.logvin.onlinestore.dao.impl.sqlparameter.SQLCardParameter;
import by.logvin.onlinestore.dao.impl.sqlrequest.CardSQLRequest;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class SQLCardDAO is used for manipulating card data in the database
 * @author bylogvin
 * @see by.logvin.onlinestore.dao.CardDAO
 */
public class SQLCardDAO implements CardDAO {

    private final static Logger logger = Logger.getLogger(SQLCardDAO.class);

    @Override
    public Card takeCard(int cardID) throws DAOException {
        Connection connection = getConnection();
        Card card;
        ResultSet resultSet = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(CardSQLRequest.SELECT_CARD_BY_ID)
        ) {
            preparedStatement.setInt(1, cardID);
            resultSet = preparedStatement.executeQuery();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
            if (!resultSet.next()) {
                logger.info("Card with this id was not found");
                return null;
            }
            card = new Card(
                    resultSet.getInt(SQLCardParameter.CARD_ID),
                    resultSet.getLong(SQLCardParameter.CARD_NUMBER),
                    resultSet.getInt(SQLCardParameter.CARD_VALIDITY_PERIOD),
                    resultSet.getInt(SQLCardParameter.CARD_AUTHENTICATION_CODE),
                    resultSet.getString(SQLCardParameter.CARD_CARDHOLDER)
            );
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution");
            throw new DAOException("Error prepared statement updating or setting data", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("Result set has been already closed", e);
            }
            if (connection != null) {
                removeConnection(connection);
            }
        }
        return card;
    }

    @Override
    public List<Card> takeCardsByUserID(int userID) throws DAOException {
        Connection connection = getConnection();
        List<Card> cards = null;
        ResultSet resultSet = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(CardSQLRequest.SELECT_CARDS_BY_USER_ID)
        ) {
            preparedStatement.setInt(1, userID);
            resultSet = preparedStatement.executeQuery();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
            while (resultSet.next()) {
                if (cards == null) {
                    cards = new ArrayList<>();
                }
                cards.add(new Card(
                        resultSet.getInt(SQLCardParameter.CARD_ID),
                        resultSet.getLong(SQLCardParameter.CARD_NUMBER),
                        resultSet.getInt(SQLCardParameter.CARD_VALIDITY_PERIOD),
                        resultSet.getInt(SQLCardParameter.CARD_AUTHENTICATION_CODE),
                        resultSet.getString(SQLCardParameter.CARD_CARDHOLDER)
                ));
            }

        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution");
            throw new DAOException("Error prepared statement updating or setting data", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("Result set has been already closed", e);
            }
            if (connection != null) {
                removeConnection(connection);
            }
        }
        return cards;
    }

    @Override
    public boolean addCard(long number, int validityPeriod, int authenticationCode, String cardholder, int userID) throws DAOException {
        Connection connection = getConnection();
        int numberOfUpdateLines;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(CardSQLRequest.INSERT_CARD)
        ) {
            preparedStatement.setInt(1, userID);
            preparedStatement.setLong(2, number);
            preparedStatement.setInt(3, validityPeriod);
            preparedStatement.setInt(4, authenticationCode);
            preparedStatement.setString(5, cardholder);
            numberOfUpdateLines = preparedStatement.executeUpdate();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution");
            throw new DAOException("Error prepared statement updating or setting data", e);
        } finally {
            if (connection != null) {
                removeConnection(connection);
            }
        }
        return numberOfUpdateLines != 0;
    }

    @Override
    public boolean deleteCard(int cardID) throws DAOException {
        Connection connection = getConnection();
        int numberOfUpdateLines;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(CardSQLRequest.DELETE_CARD)
        ) {
            preparedStatement.setInt(1, cardID);
            numberOfUpdateLines = preparedStatement.executeUpdate();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution");
            throw new DAOException("Error prepared statement updating or setting data", e);
        } finally {
            if (connection != null) {
                removeConnection(connection);
            }
        }
        return numberOfUpdateLines != 0;
    }

    @Override
    public boolean editCardInfo(int cardID, long number, int validityPeriod, int authenticationCode, String cardholder) throws DAOException {
        Connection connection = getConnection();
        int numberOfUpdateLines;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(CardSQLRequest.UPDATE_CARD)
        ) {
            preparedStatement.setLong(1, number);
            preparedStatement.setInt(2, validityPeriod);
            preparedStatement.setInt(3, authenticationCode);
            preparedStatement.setString(4, cardholder);
            preparedStatement.setInt(5, cardID);
            numberOfUpdateLines = preparedStatement.executeUpdate();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution");
            throw new DAOException("Error prepared statement updating or setting data", e);
        } finally {
            if (connection != null) {
                removeConnection(connection);
            }
        }
        return numberOfUpdateLines != 0;
    }

    private Connection getConnection() throws DAOException {
        Connection connection;
        try {
            connection = ConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            logger.error("ConnectionPoolException was thrown due to an error during getting connection from connection pool");
            throw new DAOException("Error with getting pool from server", e);
        }
        return connection;
    }

    private boolean removeConnection(Connection connection) {
        return ConnectionPool.getInstance().removeConnection(connection);
    }
}
