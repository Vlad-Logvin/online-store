package by.logvin.onlinestore.controller.command.impl;

import by.logvin.onlinestore.bean.User;
import by.logvin.onlinestore.controller.command.Command;
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
 * The AddCard class is responsible for adding card
 *
 * @author bylogvin
 * @see by.logvin.onlinestore.controller.command.Command
 */
public class AddCard implements Command {

    private final static Logger logger = Logger.getLogger(AddCard.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ExistenceProvider.getInstance().getUserExistence().isUserExist(request, response)) {
            return;
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Message.ATTRIBUTE_USER);
        try {
            if (ServiceProvider.getInstance().getCardService().addCard(
                    Long.parseLong(request.getParameter(Message.ATTRIBUTE_CARD_NUMBER)),
                    Integer.parseInt(request.getParameter(Message.ATTRIBUTE_CARD_VALIDITY_PERIOD)),
                    Integer.parseInt(request.getParameter(Message.ATTRIBUTE_CARD_AUTHENTICATION_CODE)),
                    request.getParameter(Message.ATTRIBUTE_CARD_CARDHOLDER),
                    user.getId())) {
                session.setAttribute(Message.ATTRIBUTE_USER, ServiceProvider.getInstance().getUserService().signIn(user.getEmail(), user.getPassword()));
                session.setAttribute(Message.MESSAGE, Message.CORRECT_ADDING_CARD);
            } else {
                session.setAttribute(Message.MESSAGE, Message.ERROR_ADDING_CARD);
            }
            logger.info("Redirect to last page");
            response.sendRedirect((String) session.getAttribute(Message.ATTRIBUTE_URL));
        } catch (ServiceException e) {
            logger.error("Error while adding card", e);
            request.getSession(true).setAttribute(Message.MESSAGE, Message.SERVICE_EXCEPTION);
            response.sendRedirect((String) session.getAttribute(Message.ATTRIBUTE_URL));
        } catch (NumberFormatException e) {
            logger.error("Error while parsing card parameters to there types", e);
            request.getSession(true).setAttribute(Message.MESSAGE, Message.WRONG_CARD_DATA_INPUT);
            response.sendRedirect((String) session.getAttribute(Message.ATTRIBUTE_URL));
        }

    }
}