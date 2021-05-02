package by.logvin.onlinestore.controller.command.impl.gotopage;

import by.logvin.onlinestore.controller.command.Command;

import by.logvin.onlinestore.controller.message.GoToPage;
import by.logvin.onlinestore.controller.message.Message;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * The GoToRegistrationPage class is responsible for going to registration page
 *
 * @author bylogvin
 * @see by.logvin.onlinestore.controller.command.Command
 */
public class GoToRegistrationPage implements Command {

    private final static Logger logger = Logger.getLogger(GoToRegistrationPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute(Message.ATTRIBUTE_URL, GoToPage.REDIRECT_REGISTRATION_PAGE);

        RequestDispatcher dispatcher = request.getRequestDispatcher(GoToPage.FORWARD_REGISTRATION_PAGE);
        logger.info("Forward to registration page");
        dispatcher.forward(request, response);
    }
}
