package by.logvin.onlinestore.controller.command.impl.gotopage;

import by.logvin.onlinestore.controller.command.Command;
import by.logvin.onlinestore.controller.message.GoToPage;
import by.logvin.onlinestore.controller.message.Message;
import by.logvin.onlinestore.controller.util.ExistenceProvider;
import by.logvin.onlinestore.service.ServiceProvider;
import by.logvin.onlinestore.service.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;

public class GoToShowUserPage implements Command {

    private static final Logger logger = Logger.getLogger(GoToShowUserPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!ExistenceProvider.getInstance().getUserExistence().isAdmin(request, response)){
            return;
        }

        try {
            request.setAttribute(Message.ATTRIBUTE_USERS, ServiceProvider.getInstance().getUserService().takeUsers());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(GoToPage.FORWARD_SHOW_USER_PAGE);
            logger.info("Forward to show user page");
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.error("Error while getting users", e);
            request.getSession(true).setAttribute(Message.MESSAGE, Message.SERVICE_EXCEPTION);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        }
    }
}