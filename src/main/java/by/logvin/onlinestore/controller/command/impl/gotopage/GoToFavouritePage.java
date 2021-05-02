package by.logvin.onlinestore.controller.command.impl.gotopage;

import by.logvin.onlinestore.bean.User;
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
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * The GoToFavouritePage class is responsible for going to favourite page
 *
 * @author bylogvin
 * @see by.logvin.onlinestore.controller.command.Command
 */
public class GoToFavouritePage implements Command {

    private static final Logger logger = Logger.getLogger(GoToFavouritePage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ExistenceProvider.getInstance().getUserExistence().isUserExist(request, response)) {
            return;
        }
        HttpSession session = request.getSession(false);
        String sessionMessage = (String) session.getAttribute(Message.MESSAGE);
        if (sessionMessage != null) {
            request.setAttribute(Message.MESSAGE, sessionMessage);
            session.removeAttribute(Message.MESSAGE);
        }

        try {
            request.setAttribute(Message.ATTRIBUTE_PRODUCTS, ServiceProvider.getInstance().getFavouriteService().takeFavouriteByUserID(((User)session.getAttribute(Message.ATTRIBUTE_USER)).getId()).getProducts());
            request.getSession(false).setAttribute(Message.ATTRIBUTE_URL, GoToPage.REDIRECT_FAVOURITE_PAGE);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(GoToPage.FORWARD_FAVOURITE_PAGE);
            logger.info("Forward to favourite page");
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.error("Error while getting favourite by user id", e);
            request.getSession(true).setAttribute(Message.MESSAGE, Message.SERVICE_EXCEPTION);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        }

    }
}