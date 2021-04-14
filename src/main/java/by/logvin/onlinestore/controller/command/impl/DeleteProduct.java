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

public class DeleteProduct implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ExistenceProvider.getInstance().getUserExistence().isAdmin(request, response)) {
            return;
        }

        HttpSession session = request.getSession(false);

        try {
            if (ServiceProvider.getInstance().getProductService().remove(Integer.parseInt(request.getParameter(Message.ATTRIBUTE_PRODUCT_ID)))) {
                session.setAttribute(Message.MESSAGE, Message.CORRECT_PRODUCT_DELETING);
            } else {
                session.setAttribute(Message.MESSAGE, Message.ERROR_PRODUCT_DELETING);
            }
            response.sendRedirect((String) session.getAttribute(Message.ATTRIBUTE_URL));
        } catch (ServiceException e) {
            request.getSession(true).setAttribute(Message.MESSAGE, Message.SERVICE_EXCEPTION);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        } catch (NumberFormatException e) {
            request.getSession(true).setAttribute(Message.MESSAGE, Message.WRONG_PRODUCT_INPUT);
            response.sendRedirect((String) session.getAttribute(Message.ATTRIBUTE_URL));
        }
    }
}