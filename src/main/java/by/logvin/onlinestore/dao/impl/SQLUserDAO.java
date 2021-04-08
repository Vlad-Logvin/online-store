package by.logvin.onlinestore.dao.impl;

import by.logvin.onlinestore.bean.*;
import by.logvin.onlinestore.dao.UserDAO;
import by.logvin.onlinestore.dao.connection.ConnectionPool;
import by.logvin.onlinestore.dao.connection.ConnectionPoolException;
import by.logvin.onlinestore.dao.exception.DAOException;
import by.logvin.onlinestore.dao.impl.sqlrequest.SQLRequest;
import by.logvin.onlinestore.service.BasketService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLUserDAO implements UserDAO {
    private final static Logger logger = Logger.getLogger(SQLUserDAO.class);

    @Override
    public boolean signUp(RegistrationInfo info) throws DAOException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        int numberOfUpdateLines = 0;
        try {
            preparedStatement = connection.prepareStatement(SQLRequest.insertNewUser);
            preparedStatement.setString(1, info.getEmail());
            preparedStatement.setString(2, info.getPassword());
            preparedStatement.setString(3, info.getFirstName());
            preparedStatement.setString(4, info.getLastName());
            preparedStatement.setString(5, info.getDateOfBirth());
            numberOfUpdateLines = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error prepared statement creating or setting data", e);
        }

        removeConnection(connection);

        return numberOfUpdateLines != 0;
    }

    @Override
    public User signIn(String login, String password) throws DAOException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet userResultSet = null;
        User user = null;
        int userID = 0;
        List<Card> cards = null;
        Basket basket = null;
        Favourite favourite = null;
        try {
            preparedStatement = connection.prepareStatement(SQLRequest.selectUserWithLoginAndPassword);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            userResultSet = preparedStatement.executeQuery();
            if (!userResultSet.next()) {
                throw new DAOException("Not correct login or password");
            }
            userID = userResultSet.getInt("u_id");
            cards = getCardsByUserID(connection, userID);
            user = new User(
                    userID,
                    userResultSet.getString("u_email"),
                    userResultSet.getString("u_password"),
                    userResultSet.getString("u_first_name"),
                    userResultSet.getString("u_last_name"),
                    userResultSet.getDate("u_date_of_birth"),
                    new UserDetails(
                            cards,
                            userResultSet.getBoolean("ua.ua_access"),
                            userResultSet.getString("ur.ur_role"),
                            basket,
                            favourite
                    )
            );
            logger.info(user);
        } catch (SQLException e) {
            throw new DAOException("Error prepared statement creating or setting data", e);
        }

        removeConnection(connection);

        return user;
    }

    @Override
    public boolean editUserInfo(User user) throws DAOException {
        return false;
    }

    private List<Card> getCardsByUserID(Connection connection, int userID) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet cardsResultSet = null;
        List<Card> cards = null;
        preparedStatement = connection.prepareStatement(SQLRequest.selectCardsByUserID);
        preparedStatement.setInt(1, userID);
        cardsResultSet = preparedStatement.executeQuery();
        while (cardsResultSet.next()) {
            if (cards == null) {
                cards = new ArrayList<>();
            }
            cards.add(new Card(cardsResultSet.getInt("c_id"),
                    cardsResultSet.getLong("c_number"),
                    cardsResultSet.getInt("c_validity_period"),
                    cardsResultSet.getInt("c_authentication_code"),
                    cardsResultSet.getString("c_cardholder"))
            );
        }
        return cards;
    }

    private Connection getConnection() throws DAOException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception throws while getting connection from pool", e);
        }
        return connection;
    }

    private boolean removeConnection(Connection connection) {
        return ConnectionPool.getInstance().removeConnection(connection);
    }
}
