package by.logvin.onlinestore.service.impl;

import by.logvin.onlinestore.bean.RegistrationInfo;
import by.logvin.onlinestore.bean.User;
import by.logvin.onlinestore.dao.DAOProvider;
import by.logvin.onlinestore.dao.UserDAO;
import by.logvin.onlinestore.dao.exception.DAOException;
import by.logvin.onlinestore.dao.impl.SQLUserDAO;
import by.logvin.onlinestore.service.UserService;
import by.logvin.onlinestore.service.exception.ServiceException;
import by.logvin.onlinestore.service.validator.ValidatorProvider;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * The class UserServiceImpl is used for providing user data
 * @author bylogvin
 * @see by.logvin.onlinestore.service.UserService
 */
public class UserServiceImpl implements UserService {
    private final static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public boolean signUp(RegistrationInfo info) throws ServiceException {

        if (!ValidatorProvider.getInstance().getAuthorizationValidator().validate(info)) {
            logger.info("Not valid registration information");
            return false;
        }
        UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
        boolean isSignUp;
        try {
            SQLUserDAO sqlUserDAO = new SQLUserDAO();
            sqlUserDAO.signUp(info);
            isSignUp = userDAO.signUp(info);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during user sign up");
            throw new ServiceException("Error during user sign up", e);
        }
        return isSignUp;
    }

    @Override
    public User signIn(String email, String password) throws ServiceException {

        if (!ValidatorProvider.getInstance().getAuthorizationValidator().validate(email, password)) {
            logger.info("Not valid email or password");
            return null;
        }

        UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
        User user;
        try {
            user = userDAO.signIn(email, password);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during user sign in");
            throw new ServiceException("Error during user sign in", e);
        }
        return user;
    }

    @Override
    public boolean editUserInfo(int userID, String email, String password, String firstName, String lastName, String dateOfBirth) throws ServiceException {
        if (!ValidatorProvider.getInstance().getAuthorizationValidator().validate(
                new RegistrationInfo(email, password, firstName, lastName, dateOfBirth))) {
            logger.info("Not valid user editing data");
            return false;
        }

        UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
        boolean isEdit;
        try {
            isEdit = userDAO.editUserInfo(userID, email, password, firstName, lastName, dateOfBirth);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during editing user information");
            throw new ServiceException("Error during editing user information", e);
        }

        return isEdit;
    }

    @Override
    public boolean editUserRole(int userID, int roleID) throws ServiceException {
        UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
        boolean isEdit;
        try {
            isEdit = userDAO.editUserRole(userID, roleID);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during user editing");
            throw new ServiceException("Error during user editing", e);
        }
        return isEdit;
    }

    @Override
    public boolean editUserAccess(int userID, int accessID) throws ServiceException {
        UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
        boolean isEdit;
        try {
            isEdit = userDAO.editUserAccess(userID, accessID);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during user editing");
            throw new ServiceException("Error during user editing", e);
        }
        return isEdit;
    }

    @Override
    public List<User> takeUsers() throws ServiceException {
        UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
        List<User> users;
        try {
            users = userDAO.takeUsers();
        } catch (DAOException e) {
            logger.error("DAOException was thrown during users getting");
            throw new ServiceException("Error during users getting", e);
        }
        return users;
    }
}
