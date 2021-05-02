package by.logvin.onlinestore.controller.command;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

/**
 * The interface Command represent action when clients send requests
 *
 * @author bylogvin
 */
public interface Command {
    /**
     * The method execute input command
     *
     * @param request {@link HttpServletRequest} client request
     * @param response {@link HttpServletResponse} server response
     * @throws ServletException extends {@link Exception}
     * @throws IOException extends {@link Exception}
     */
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}



