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

import java.io.IOException;

public class ManageAccess implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ExistenceProvider.getInstance().getUserExistence().isAdmin(request, response)) {
            return;
        }

        try {
            if(ServiceProvider.getInstance().getUserService().editUserAccess(
                    Integer.parseInt(request.getParameter(Message.ATTRIBUTE_USER_ID)),
                    Integer.parseInt(request.getParameter(Message.ATTRIBUTE_ACCESS)))){

            }else {

            }
            response.sendRedirect(GoToPage.REDIRECT_SHOW_USER_PAGE);
        } catch (ServiceException e) {
            request.getSession(true).setAttribute(Message.MESSAGE, Message.SERVICE_EXCEPTION);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        }
    }
}
