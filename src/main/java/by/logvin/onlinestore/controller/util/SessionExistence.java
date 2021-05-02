package by.logvin.onlinestore.controller.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * The SessionExistence interface checks for session existence
 *
 * @author bylogvin
 */
public interface SessionExistence {

    /**
     * The method isSessionExist checks session existence
     *
     * @param request {@link HttpServletRequest} client request
     * @param response {@link HttpServletResponse} server response
     * @return boolean true if session exist
     * @throws IOException
     */
    boolean isSessionExist(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
