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

import java.io.IOException;

public class GoToEditCardFormPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!ExistenceProvider.getInstance().getUserExistence().isUserExist(request, response)) {
            return;
        }

        try {
            request.setAttribute(Message.ATTRIBUTE_ACTION, Message.ATTRIBUTE_EDIT_CARD);
            request.setAttribute(Message.ATTRIBUTE_CARD, ServiceProvider.getInstance().getCardService().getCard(Integer.parseInt(request.getParameter(Message.ATTRIBUTE_CARD_ID))));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(GoToPage.FORWARD_CARD_FORM_PAGE);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            request.getSession(true).setAttribute(Message.MESSAGE, Message.SERVICE_EXCEPTION);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        } catch (NumberFormatException e) {
            request.getSession(true).setAttribute(Message.MESSAGE, Message.WRONG_CARD_INPUT);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        }

    }
}