package by.logvin.onlinestore.controller.command.impl;

import by.logvin.onlinestore.bean.User;
import by.logvin.onlinestore.controller.command.Command;
import by.logvin.onlinestore.controller.message.GoToPage;
import by.logvin.onlinestore.controller.message.Message;
import by.logvin.onlinestore.service.ServiceProvider;
import by.logvin.onlinestore.service.exception.ServiceException;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.apache.log4j.Logger;

import java.io.IOException;

public class SignIn implements Command {

    private final static Logger logger = Logger.getLogger(SignIn.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(Message.ATTRIBUTE_EMAIL);
        String password = request.getParameter(Message.ATTRIBUTE_PASSWORD);

        User user = null;
        HttpSession session = request.getSession(true);
        try {
            user = ServiceProvider.getInstance().getUserService().signIn(email, password);
            if (user == null) {
                session.setAttribute(Message.MESSAGE, Message.ERROR_SIGN_IN);
                logger.info("Redirect to authorization page");
                response.sendRedirect(GoToPage.REDIRECT_AUTHORIZATION_PAGE);
            } else if (!user.getUserDetails().isAccess()) {
                session.setAttribute(Message.MESSAGE, Message.BLOCKED_USER);
                logger.info("Redirect to authorization page");
                response.sendRedirect(GoToPage.REDIRECT_AUTHORIZATION_PAGE);
            } else {
                session.setAttribute(Message.ATTRIBUTE_USER, user);
                session.setAttribute(Message.MESSAGE, Message.CORRECT_SIGN_IN);
                logger.error("Redirect to main page");
                response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
            }

        } catch (ServiceException e) {
            logger.error("Error while signing in", e);
            request.getSession(true).setAttribute(Message.MESSAGE, Message.SERVICE_EXCEPTION);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        }
    }
}
