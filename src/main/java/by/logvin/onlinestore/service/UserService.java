package by.logvin.onlinestore.service;

import by.logvin.onlinestore.bean.RegistrationInfo;
import by.logvin.onlinestore.bean.User;
import by.logvin.onlinestore.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    boolean signUp(RegistrationInfo info) throws ServiceException;

    User signIn(String login, String password) throws ServiceException;

    boolean editUserInfo(int userID, String email, String password, String firstName, String lastName, String dateOfBirth) throws ServiceException;

    boolean editUserRole(int userID, int roleID) throws ServiceException;

    boolean editUserAccess(int userID, int accessID) throws ServiceException;

    List<User> takeUsers() throws ServiceException;
}
