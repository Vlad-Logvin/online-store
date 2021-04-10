package by.logvin.onlinestore.service.impl;

import by.logvin.onlinestore.bean.RegistrationInfo;
import by.logvin.onlinestore.bean.User;
import by.logvin.onlinestore.dao.DAOProvider;
import by.logvin.onlinestore.dao.UserDAO;
import by.logvin.onlinestore.dao.exception.DAOException;
import by.logvin.onlinestore.service.UserService;
import by.logvin.onlinestore.service.exception.ServiceException;
import by.logvin.onlinestore.service.validator.ValidatorProvider;
import org.apache.log4j.Logger;

public class UserServiceImpl implements UserService {
    private final static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public boolean signUp(RegistrationInfo info) throws ServiceException {

        if (!ValidatorProvider.getInstance().getAuthorizationValidator().validate(info)) {
            logger.info("Not valid registration information");
            return false;
        }

        UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
        boolean isSignUp = false;
        try {
            isSignUp = userDAO.signUp(info);
            logger.info("User signs up: " + isSignUp);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during user sign up", e);
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
        User user = null;
        try {
            user = userDAO.signIn(email, password);
            logger.info("User signs in: " + user);
        } catch (DAOException e) {
            logger.error("DAOException was thrown during user sign in", e);
            throw new ServiceException("Error during user sign in", e);
        }
        return user;
    }

    @Override
    public boolean editUserInfo(int userID, String email, String password, String firstName, String lastName, String dateOfBirth) throws ServiceException {
        if (ValidatorProvider.getInstance().getAuthorizationValidator().validate(
                new RegistrationInfo(email, password, firstName, lastName, dateOfBirth))) {
            logger.info("Not valid user editing data");
            return false;
        }

        UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
        boolean isEdit = false;
        try {
            isEdit = userDAO.editUserInfo(userID, email, password, firstName, lastName, dateOfBirth);
        } catch (DAOException e) {

            logger.error("DAOException was thrown during editing user information", e);
            throw new ServiceException("Error during editing user information", e);
        }

        return isEdit;
    }
}
