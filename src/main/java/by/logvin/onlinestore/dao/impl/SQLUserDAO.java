package by.logvin.onlinestore.dao.impl;

import by.logvin.onlinestore.bean.*;
import by.logvin.onlinestore.dao.UserDAO;
import by.logvin.onlinestore.dao.connection.ConnectionPool;
import by.logvin.onlinestore.dao.connection.ConnectionPoolException;
import by.logvin.onlinestore.dao.exception.DAOException;
import by.logvin.onlinestore.dao.impl.sqlparameter.SQLUserParameter;
import by.logvin.onlinestore.dao.impl.sqlrequest.UserSQLRequest;
import by.logvin.onlinestore.service.ServiceProvider;
import by.logvin.onlinestore.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLUserDAO implements UserDAO {
    private final static Logger logger = Logger.getLogger(SQLUserDAO.class);

    @Override
    public boolean signUp(RegistrationInfo info) throws DAOException {
        Connection connection = getConnection();
        int numberOfUpdateLines;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(UserSQLRequest.INSERT_USER)
                ){
            preparedStatement.setString(1, info.getEmail());
            preparedStatement.setString(2, info.getPassword());
            preparedStatement.setString(3, info.getFirstName());
            preparedStatement.setString(4, info.getLastName());
            preparedStatement.setString(5, info.getDateOfBirth());
            numberOfUpdateLines = preparedStatement.executeUpdate();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during request creation or execution");
            throw new DAOException("Error prepared statement creating or setting data", e);
        } finally {
            if (connection != null) {
                removeConnection(connection);
            }
        }

        return numberOfUpdateLines != 0;
    }

    @Override
    public User signIn(String email, String password) throws DAOException {
        Connection connection = getConnection();
        ResultSet userResultSet = null;
        User user;
        int userID;
        List<Card> cards;
        Basket basket;
        Favourite favourite;
        List<Order> orders;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(UserSQLRequest.SELECT_USER_WITH_LOGIN_AND_PASSWORD)
                ){
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            userResultSet = preparedStatement.executeQuery();
            logger.info("Request (" + preparedStatement.toString() + ") completed");
            if (!userResultSet.next()) {
                logger.info("User with this email and password was not found");
                return null;
            }
            userID = userResultSet.getInt(SQLUserParameter.USER_ID);
            cards = ServiceProvider.getInstance().getCardService().takeCardsByUserID(userID);
            if ((basket = ServiceProvider.getInstance().getBasketService().takeBasketByUserID(userID)) == null) {
                ServiceProvider.getInstance().getBasketService().createBasket(userID);
                basket = ServiceProvider.getInstance().getBasketService().takeBasketByUserID(userID);
            }
            if ((favourite = ServiceProvider.getInstance().getFavouriteService().takeFavouriteByUserID(userID)) == null) {
                ServiceProvider.getInstance().getFavouriteService().createFavourite(userID);
                favourite = ServiceProvider.getInstance().getFavouriteService().takeFavouriteByUserID(userID);
            }
            orders = ServiceProvider.getInstance().getOrderService().takeUserOrders(userID);
            user = new User(
                    userID,
                    userResultSet.getString(SQLUserParameter.USER_EMAIL),
                    userResultSet.getString(SQLUserParameter.USER_PASSWORD),
                    userResultSet.getString(SQLUserParameter.USER_FIRST_NAME),
                    userResultSet.getString(SQLUserParameter.USER_LAST_NAME),
                    userResultSet.getDate(SQLUserParameter.USER_DATE_OF_BIRTH),
                    new UserDetails(
                            cards,
                            userResultSet.getBoolean(SQLUserParameter.USER_ACCESS),
                            userResultSet.getString(SQLUserParameter.USER_ROLE),
                            basket,
                            favourite,
                            orders
                    )
            );
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution");
            throw new DAOException("Error prepared statement updating or setting data", e);
        } catch (ServiceException e) {
            logger.error("ServiceException was thrown due to an error during getting product information by product id");
            throw new DAOException("Error product information by product id", e);
        } finally {
            try {
                if (userResultSet != null) {
                    userResultSet.close();
                }
            } catch (SQLException e) {
                logger.error("Result set has been already closed", e);
            }
            if (connection != null) {
                removeConnection(connection);
            }
        }

        return user;
    }

    @Override
    public boolean editUserInfo(int userID, String email, String password, String firstName, String lastName, String dateOfBirth) throws DAOException {
        Connection connection = getConnection();
        int numberOfUpdateLines;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(UserSQLRequest.UPDATE_USER)
                ){
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, lastName);
            preparedStatement.setString(5, dateOfBirth);
            preparedStatement.setInt(6, userID);
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
    public boolean editUserRole(int userID, int roleID) throws DAOException {
        Connection connection = getConnection();
        int numberOfUpdateLines;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(UserSQLRequest.UPDATE_USER_ROLE)
                ){
            preparedStatement.setInt(1, roleID);
            preparedStatement.setInt(2, userID);
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
    public boolean editUserAccess(int userID, int accessID) throws DAOException {
        Connection connection = getConnection();
        int numberOfUpdateLines;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(UserSQLRequest.UPDATE_USER_ACCESS)
        ) {
            preparedStatement.setInt(1, accessID);
            preparedStatement.setInt(2, userID);
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
    public List<User> takeUsers() throws DAOException {
        Connection connection = getConnection();
        List<User> users = null;
        try (
                Statement statement = connection.createStatement();
                ResultSet userResultSet = statement.executeQuery(UserSQLRequest.SELECT_USER_EMAIL_AND_PASSWORD)
        ) {
            logger.info("Request (" + statement.toString() + ") completed");
            while (userResultSet.next()) {
                if (users == null) {
                    users = new ArrayList<>();
                }
                users.add(signIn(userResultSet.getString(SQLUserParameter.USER_PARAMETER + SQLUserParameter.USER_EMAIL),
                        userResultSet.getString(SQLUserParameter.USER_PARAMETER + SQLUserParameter.USER_PASSWORD)));
            }
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution");
            throw new DAOException("Error prepared statement updating or setting data", e);
        } finally {
            if (connection != null) {
                removeConnection(connection);
            }
        }

        return users;
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
