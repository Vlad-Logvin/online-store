package by.logvin.onlinestore.service;

import by.logvin.onlinestore.bean.RegistrationInfo;
import by.logvin.onlinestore.bean.User;
import by.logvin.onlinestore.service.exception.ServiceException;

public interface UserService {
    boolean signUp(RegistrationInfo info) throws ServiceException;
    User signIn(String login, String password) throws ServiceException;
}
