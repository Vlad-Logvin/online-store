package by.logvin.onlinestore.service;

import by.logvin.onlinestore.bean.RegistrationInfo;
import by.logvin.onlinestore.bean.User;
import by.logvin.onlinestore.service.exception.ServiceException;

import java.util.List;

/**
 * The interface UserService provides user data
 *
 * @author bylogvin
 */
public interface UserService {

    /**
     * The method signUp provides signing up the online store
     *
     * @param info {@link RegistrationInfo} of user
     * @return boolean if signing up is successful
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    boolean signUp(RegistrationInfo info) throws ServiceException;

    /**
     * The method signIn provides signing in the online store
     *
     * @param login {@link String} user login
     * @param password {@link String} user password
     * @return {@link User} if exist with this email and password and null if user with this data doesn't exist
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    User signIn(String login, String password) throws ServiceException;

    /**
     * The method editUserInfo provides editing user data by its id
     *
     * @param userID      int user id
     * @param email       {@link String} user email
     * @param password    {@link String} user password
     * @param firstName   {@link String} user first name
     * @param lastName    {@link String} user last name
     * @param dateOfBirth {@link String} date of birth
     * @return boolean true if editing is successful
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    boolean editUserInfo(int userID, String email, String password, String firstName, String lastName, String dateOfBirth) throws ServiceException;

    /**
     * The method editUserRole provides editing user role by its id
     *
     * @param userID int user id
     * @param roleID int role id
     * @return boolean true if editing user role is successful
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    boolean editUserRole(int userID, int roleID) throws ServiceException;

    /**
     * The method editUserAccess provides editing user access by its id
     *
     * @param userID int user id
     * @param accessID int access id
     * @return boolean true if editing user access is successful
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    boolean editUserAccess(int userID, int accessID) throws ServiceException;

    /**
     * The method takeUsers provides taking all users
     *
     * @return {@link List} of all users
     * @throws ServiceException can be thrown due to {@link by.logvin.onlinestore.dao.exception.DAOException}
     */
    List<User> takeUsers() throws ServiceException;
}
