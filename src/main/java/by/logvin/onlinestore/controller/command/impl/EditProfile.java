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

public class EditProfile implements Command {

    private static final Logger logger = Logger.getLogger(EditProfile.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ExistenceProvider.getInstance().getUserExistence().isUserExist(request, response)) {
            return;
        }

        HttpSession session = request.getSession(false);
        try {
            if (ServiceProvider.getInstance().getUserService().editUserInfo(
                    Integer.parseInt(request.getParameter(Message.ATTRIBUTE_USER_ID)),
                    request.getParameter(Message.ATTRIBUTE_EMAIL),
                    request.getParameter(Message.ATTRIBUTE_PASSWORD),
                    request.getParameter(Message.ATTRIBUTE_FIRST_NAME),
                    request.getParameter(Message.ATTRIBUTE_LAST_NAME),
                    request.getParameter(Message.ATTRIBUTE_DATE_OF_BIRTH)
            )) {
                session.setAttribute(Message.MESSAGE, Message.CORRECT_EDIT_PROFILE);
                session.setAttribute(Message.ATTRIBUTE_USER, ServiceProvider.getInstance().getUserService().signIn(request.getParameter(Message.ATTRIBUTE_EMAIL), request.getParameter(Message.ATTRIBUTE_PASSWORD)));
            } else {
                session.setAttribute(Message.MESSAGE, Message.ERROR_EDIT_PROFILE);
            }
            logger.info("Redirect to last page");
            response.sendRedirect((String) session.getAttribute(Message.ATTRIBUTE_URL));
        } catch (ServiceException e) {
            logger.error("Error while profile editing", e);
            request.getSession(true).setAttribute(Message.MESSAGE, Message.SERVICE_EXCEPTION);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        }
    }
}