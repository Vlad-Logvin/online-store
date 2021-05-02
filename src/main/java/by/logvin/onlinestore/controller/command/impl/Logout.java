package by.logvin.onlinestore.controller.command.impl;

import by.logvin.onlinestore.controller.command.Command;

import by.logvin.onlinestore.controller.message.GoToPage;
import by.logvin.onlinestore.controller.message.Message;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * The Logout class is responsible for user logout
 *
 * @author bylogvin
 * @see by.logvin.onlinestore.controller.command.Command
 */
public class Logout implements Command {

    private static final Logger logger = Logger.getLogger(Logout.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.removeAttribute(Message.ATTRIBUTE_USER);

        session.setAttribute(Message.MESSAGE, Message.LOGOUT);
        logger.info("Redirect to main page");
        response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
    }
}
