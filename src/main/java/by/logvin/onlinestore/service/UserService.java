package by.logvin.onlinestore.service;

import by.logvin.onlinestore.bean.RegistrationInfo;
import by.logvin.onlinestore.bean.User;
import by.logvin.onlinestore.service.exception.ServiceException;

import java.util.Date;

public interface UserService {
    boolean signUp(RegistrationInfo info) throws ServiceException;

    User signIn(String login, String password) throws ServiceException;

    boolean editUserInfo(int userID, String email, String password, String firstName, String lastName, Date dateOfBirth) throws ServiceException;
}
