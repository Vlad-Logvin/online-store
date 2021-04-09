package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.bean.RegistrationInfo;
import by.logvin.onlinestore.bean.User;
import by.logvin.onlinestore.dao.exception.DAOException;

import java.util.Date;

public interface UserDAO {
    boolean signUp(RegistrationInfo info) throws DAOException;

    User signIn(String email, String password) throws DAOException;

    boolean editUserInfo(int userID, String email, String password, String firstName, String lastName, Date dateOfBirth) throws DAOException;
}
