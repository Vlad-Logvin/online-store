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

import java.io.IOException;

public class EditProfile implements Command {
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
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        } catch (ServiceException e) {
            request.getSession(true).setAttribute(Message.MESSAGE, Message.SERVICE_EXCEPTION);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        }
    }
}