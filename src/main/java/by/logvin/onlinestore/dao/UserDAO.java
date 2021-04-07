package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.bean.RegistrationInfo;
import by.logvin.onlinestore.bean.User;
import by.logvin.onlinestore.dao.exception.DAOException;

public interface UserDAO {
    boolean signUp(RegistrationInfo info) throws DAOException;
    User signIn(String login, String password) throws DAOException;
}
