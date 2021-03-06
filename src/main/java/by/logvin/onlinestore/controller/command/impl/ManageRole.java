package by.logvin.onlinestore.controller.command.impl;

import by.logvin.onlinestore.controller.command.Command;
import by.logvin.onlinestore.controller.message.GoToPage;
import by.logvin.onlinestore.controller.message.Message;
import by.logvin.onlinestore.controller.util.ExistenceProvider;
import by.logvin.onlinestore.service.ServiceProvider;
import by.logvin.onlinestore.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * The ManageRole class is responsible for role managing
 *
 * @author bylogvin
 * @see by.logvin.onlinestore.controller.command.Command
 */
public class ManageRole implements Command {

    private static final Logger logger = Logger.getLogger(ManageRole.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ExistenceProvider.getInstance().getUserExistence().isAdmin(request, response)) {
            return;
        }

        HttpSession session = request.getSession(false);

        try {
            if (ServiceProvider.getInstance().getUserService().editUserRole(
                    Integer.parseInt(request.getParameter(Message.ATTRIBUTE_USER_ID)),
                    Integer.parseInt(request.getParameter(Message.ATTRIBUTE_ROLE)))) {
                session.setAttribute(Message.MESSAGE, Message.CORRECT_ROLE_CHANGING);
            } else {
                session.setAttribute(Message.MESSAGE, Message.ERROR_ROLE_CHANGING);
            }
            logger.info("Redirect to show user page");
            response.sendRedirect(GoToPage.REDIRECT_SHOW_USER_PAGE);
        } catch (ServiceException e) {
            request.getSession(true).setAttribute(Message.MESSAGE, Message.SERVICE_EXCEPTION);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        }
    }
}