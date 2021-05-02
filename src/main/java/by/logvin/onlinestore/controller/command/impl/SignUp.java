package by.logvin.onlinestore.controller.command.impl;

import by.logvin.onlinestore.bean.RegistrationInfo;
import by.logvin.onlinestore.controller.command.Command;

import by.logvin.onlinestore.controller.message.GoToPage;
import by.logvin.onlinestore.controller.message.Message;
import by.logvin.onlinestore.service.ServiceProvider;
import by.logvin.onlinestore.service.exception.ServiceException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.apache.log4j.Logger;
import java.io.IOException;

/**
 * The SignUp class is responsible for signing up
 *
 * @author bylogvin
 * @see by.logvin.onlinestore.controller.command.Command
 */
public class SignUp implements Command {

    private final static Logger logger = Logger.getLogger(SignUp.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession(true);
        try {
            if(ServiceProvider.getInstance().getUserService().signUp(new RegistrationInfo(
                    request.getParameter(Message.ATTRIBUTE_EMAIL),
                    request.getParameter(Message.ATTRIBUTE_PASSWORD),
                    request.getParameter(Message.ATTRIBUTE_FIRST_NAME),
                    request.getParameter(Message.ATTRIBUTE_LAST_NAME),
                    request.getParameter(Message.ATTRIBUTE_DATE_OF_BIRTH)
            ))){
                session.setAttribute(Message.MESSAGE, Message.CORRECT_SIGN_UP);
            }else {
                session.setAttribute(Message.MESSAGE, Message.ERROR_SIGN_UP);
            }
            logger.info("Redirect to authorization page");
            response.sendRedirect(GoToPage.REDIRECT_AUTHORIZATION_PAGE);
        } catch (ServiceException e) {
            logger.error("Error while signing up", e);
            request.getSession(true).setAttribute(Message.MESSAGE, Message.SERVICE_EXCEPTION);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        }
    }
}
