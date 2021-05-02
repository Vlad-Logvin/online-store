package by.logvin.onlinestore.controller.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * The UserExistence interface checks for user existence
 *
 * @author bylogvin
 */
public interface UserExistence {

    /**
     * The method isUserExist checks user online
     *
     * @param request {@link HttpServletRequest} client request
     * @param response {@link HttpServletResponse} server response
     * @return boolean true if user is online
     * @throws IOException
     */
    boolean isUserExist(HttpServletRequest request, HttpServletResponse response) throws IOException;

    /**
     * The method isAdmin checks user online
     *
     * @param request {@link HttpServletRequest} client request
     * @param response {@link HttpServletResponse} server response
     * @return boolean true if admin is online
     * @throws IOException
     */
    boolean isAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
