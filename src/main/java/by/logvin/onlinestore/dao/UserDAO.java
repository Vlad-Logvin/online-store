package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.bean.RegistrationInfo;
import by.logvin.onlinestore.bean.User;
import by.logvin.onlinestore.dao.exception.DAOException;

import java.util.List;

/**
 * The UserDAO interface is used to manipulate user data in the database
 * @author bylogvin
 */
public interface UserDAO {

    /**
     * The method signUp allows user to sign up the online store
     *
     * @param info {@link RegistrationInfo} need for sign up the application
     * @return boolean true if signing up is successful
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    boolean signUp(RegistrationInfo info) throws DAOException;

    /**
     * The method sign in allows user to sign in the online store
     *
     * @param email    {@link String} user email
     * @param password {@link String} user password
     * @return {@link User} if exist with this email and password and null if user with this data doesn't exist
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    User signIn(String email, String password) throws DAOException;

    /**
     * The method editUserInfo edits user information by its id
     *
     * @param userID      int user id
     * @param email       {@link String} user email
     * @param password    {@link String} user password
     * @param firstName   {@link String} user first name
     * @param lastName    {@link String} user last name
     * @param dateOfBirth {@link String} date of birth
     * @return boolean true if editing is successful
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    boolean editUserInfo(int userID, String email, String password, String firstName, String lastName, String dateOfBirth) throws DAOException;

    /**
     * The method editUserRole edits user role by its id
     *
     * @param userID int user id
     * @param roleID int role id
     * @return boolean true if editing is successful
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    boolean editUserRole(int userID, int roleID) throws DAOException;

    /**
     * The method editUserAccess edits user role by its id
     *
     * @param userID   int user id
     * @param accessID int access id
     * @return boolean true if editing is successful
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    boolean editUserAccess(int userID, int accessID) throws DAOException;

    /**
     * The method takeUsers takes all users from database
     *
     * @return {@link List} of all users
     * @throws DAOException Can be thrown due to {@link by.logvin.onlinestore.dao.connection.ConnectionPoolException}
     *                      or {@link java.sql.SQLException}
     */
    List<User> takeUsers() throws DAOException;
}
