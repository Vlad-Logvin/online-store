package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.bean.RegistrationInfo;
import by.logvin.onlinestore.bean.User;
import by.logvin.onlinestore.dao.exception.DAOException;

import java.util.Date;
import java.util.List;

public interface UserDAO {
    boolean signUp(RegistrationInfo info) throws DAOException;

    User signIn(String email, String password) throws DAOException;

    boolean editUserInfo(int userID, String email, String password, String firstName, String lastName, String dateOfBirth) throws DAOException;

    boolean editUserRole(int userID, int roleID) throws DAOException;

    boolean editUserAccess(int userID, int accessID) throws DAOException;

    List<User> getUsers() throws DAOException;
}
