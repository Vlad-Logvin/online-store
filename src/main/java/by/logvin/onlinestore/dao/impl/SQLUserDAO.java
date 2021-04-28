package by.logvin.onlinestore.dao.impl;

import by.logvin.onlinestore.bean.*;
import by.logvin.onlinestore.dao.UserDAO;
import by.logvin.onlinestore.dao.connection.ConnectionPool;
import by.logvin.onlinestore.dao.connection.ConnectionPoolException;
import by.logvin.onlinestore.dao.exception.DAOException;
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
        logger.info("Connection established");
        PreparedStatement preparedStatement = null;
        int numberOfUpdateLines = 0;

        try {
            preparedStatement = connection.prepareStatement(UserSQLRequest.INSERT_USER);
            preparedStatement.setString(1, info.getEmail());
            preparedStatement.setString(2, info.getPassword());
            preparedStatement.setString(3, info.getFirstName());
            preparedStatement.setString(4, info.getLastName());
            preparedStatement.setString(5, info.getDateOfBirth());
            numberOfUpdateLines = preparedStatement.executeUpdate();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during request creation or execution", e);
            throw new DAOException("Error prepared statement creating or setting data", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("Prepared statement has been already closed", e);
            }
            if (connection != null) {
                removeConnection(connection);
                logger.info("Connection is broken");
            }
        }

        return numberOfUpdateLines != 0;
    }

    @Override
    public User signIn(String email, String password) throws DAOException {
        Connection connection = getConnection();
        logger.info("Connection established");
        PreparedStatement preparedStatement = null;
        ResultSet userResultSet = null;
        User user = null;
        int userID = 0;
        List<Card> cards = null;
        Basket basket = null;
        Favourite favourite = null;
        List<Order> orders = null;
        try {
            preparedStatement = connection.prepareStatement(UserSQLRequest.SELECT_USER_WITH_LOGIN_AND_PASSWORD);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            userResultSet = preparedStatement.executeQuery();
            logger.info("Request (" + preparedStatement.toString() + ") completed");
            if (!userResultSet.next()) {
                logger.info("User with this email and password was not found");
                return null;
            }
            userID = userResultSet.getInt("u_id");
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
                            favourite,
                            orders
                    )
            );
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution", e);
            throw new DAOException("Error prepared statement updating or setting data", e);
        } catch (ServiceException e) {
            logger.error("ServiceException was thrown due to an error during getting product information by product id", e);
            throw new DAOException("Error product information by product id", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("Prepared statement has been already closed", e);
            }
            try {
                if (userResultSet != null) {
                    userResultSet.close();
                }
            } catch (SQLException e) {
                logger.error("Result set has been already closed", e);
            }
            if (connection != null) {
                removeConnection(connection);
                logger.info("Connection is broken");
            }
        }

        return user;
    }

    @Override
    public boolean editUserInfo(int userID, String email, String password, String firstName, String lastName, String dateOfBirth) throws DAOException {
        Connection connection = getConnection();
        logger.info("Connection established");
        PreparedStatement preparedStatement = null;
        int numberOfUpdateLines = 0;

        try {
            preparedStatement = connection.prepareStatement(UserSQLRequest.UPDATE_USER);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, lastName);
            preparedStatement.setString(5, dateOfBirth);
            preparedStatement.setInt(6, userID);
            numberOfUpdateLines = preparedStatement.executeUpdate();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution", e);
            throw new DAOException("Error prepared statement updating or setting data", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("Prepared statement has been already closed", e);
            }
            if (connection != null) {
                removeConnection(connection);
                logger.info("Connection is broken");
            }
        }

        return numberOfUpdateLines != 0;
    }

    @Override
    public boolean editUserRole(int userID, int roleID) throws DAOException {
        Connection connection = getConnection();
        logger.info("Connection established");
        PreparedStatement preparedStatement = null;
        int numberOfUpdateLines = 0;

        try {
            preparedStatement = connection.prepareStatement(UserSQLRequest.UPDATE_USER_ROLE);
            preparedStatement.setInt(1, roleID);
            preparedStatement.setInt(2, userID);
            numberOfUpdateLines = preparedStatement.executeUpdate();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution", e);
            throw new DAOException("Error prepared statement updating or setting data", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("Prepared statement has been already closed", e);
            }
            if (connection != null) {
                removeConnection(connection);
                logger.info("Connection is broken");
            }
        }

        return numberOfUpdateLines != 0;
    }

    @Override
    public boolean editUserAccess(int userID, int accessID) throws DAOException {
        Connection connection = getConnection();
        logger.info("Connection established");
        PreparedStatement preparedStatement = null;
        int numberOfUpdateLines = 0;

        try {
            preparedStatement = connection.prepareStatement(UserSQLRequest.UPDATE_USER_ACCESS);
            preparedStatement.setInt(1, accessID);
            preparedStatement.setInt(2, userID);
            numberOfUpdateLines = preparedStatement.executeUpdate();
            logger.info("Request (" + preparedStatement.toString() + ") was completed");
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution", e);
            throw new DAOException("Error prepared statement updating or setting data", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("Prepared statement has been already closed", e);
            }
            if (connection != null) {
                removeConnection(connection);
                logger.info("Connection is broken");
            }
        }

        return numberOfUpdateLines != 0;
    }

    @Override
    public List<User> takeUsers() throws DAOException {
        Connection connection = getConnection();
        logger.info("Connection established");
        Statement statement = null;
        ResultSet userResultSet = null;
        List<User> users = null;
        try {
            statement = connection.createStatement();
            userResultSet = statement.executeQuery(UserSQLRequest.SELECT_USER_EMAIL_AND_PASSWORD);
            logger.info("Request (" + statement.toString() + ") completed");
            while (userResultSet.next()) {
                if (users == null) {
                    users = new ArrayList<>();
                }
                users.add(signIn(userResultSet.getString("u.u_email"), userResultSet.getString("u.u_password")));
            }
        } catch (SQLException e) {
            logger.error("SQLException was thrown due to an error during prepared statement creation or execution", e);
            throw new DAOException("Error prepared statement updating or setting data", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("Prepared statement has been already closed", e);
            }
            try {
                if (userResultSet != null) {
                    userResultSet.close();
                }
            } catch (SQLException e) {
                logger.error("Result set has been already closed", e);
            }
            if (connection != null) {
                removeConnection(connection);
                logger.info("Connection is broken");
            }
        }

        return users;
    }

    private Connection getConnection() throws DAOException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            logger.info("Connection is gotten from connection pool");
        } catch (ConnectionPoolException e) {
            logger.error("ConnectionPoolException was thrown due to an error during getting connection from connection pool", e);
            throw new DAOException("Error with getting pool from server", e);
        }
        return connection;
    }

    private boolean removeConnection(Connection connection) {
        return ConnectionPool.getInstance().removeConnection(connection);
    }
}
