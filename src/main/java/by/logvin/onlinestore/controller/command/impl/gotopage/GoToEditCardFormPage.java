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

/**
 * The GoToEditCardFormPage class is responsible for going to edit card form page
 *
 * @author bylogvin
 * @see by.logvin.onlinestore.controller.command.Command
 */
public class GoToEditCardFormPage implements Command {

    private final static Logger logger = Logger.getLogger(GoToEditCardFormPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!ExistenceProvider.getInstance().getUserExistence().isUserExist(request, response)) {
            return;
        }

        try {
            request.setAttribute(Message.ATTRIBUTE_ACTION, Message.ATTRIBUTE_EDIT_CARD);
            request.setAttribute(Message.ATTRIBUTE_CARD, ServiceProvider.getInstance().getCardService().takeCard(Integer.parseInt(request.getParameter(Message.ATTRIBUTE_CARD_ID))));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(GoToPage.FORWARD_CARD_FORM_PAGE);
            logger.info("Forward to card form page");
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.error("Error while getting products or categories", e);
            request.getSession(true).setAttribute(Message.MESSAGE, Message.SERVICE_EXCEPTION);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        } catch (NumberFormatException e) {
            logger.error("Error while parsing values from site, not correct input data", e);
            request.getSession(true).setAttribute(Message.MESSAGE, Message.WRONG_CARD_INPUT);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        }

    }
}