package by.logvin.onlinestore.controller.command.impl.gotopage;

import by.logvin.onlinestore.bean.Order;
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
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * The GoToOrderPage class is responsible for going to order page
 *
 * @author bylogvin
 * @see by.logvin.onlinestore.controller.command.Command
 */
public class GoToOrderPage implements Command {

    private final static Logger logger = Logger.getLogger(GoToOrderPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ExistenceProvider.getInstance().getUserExistence().isUserExist(request, response)) {
            return;
        }
        int userID;
        try {
            userID = Integer.parseInt(request.getParameter(Message.ATTRIBUTE_USER_ID));
        } catch (NumberFormatException e) {
            logger.error("Error while parsing number(user id) to int", e);
            request.getSession(true).setAttribute(Message.MESSAGE, Message.WRONG_USER_ID_INPUT);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
            return;
        }

        List<Order> orders;
        try {
            if (userID == 0) {
                if(!ExistenceProvider.getInstance().getUserExistence().isAdmin(request, response)){
                    return;
                }
                orders = ServiceProvider.getInstance().getOrderService().takeOrderLog();
            } else {
                if (userID == ((User) request.getSession(false).getAttribute(Message.ATTRIBUTE_USER)).getId()) {
                    orders = ServiceProvider.getInstance().getOrderService().takeUserOrders(userID);
                } else {
                    request.getSession(true).setAttribute(Message.MESSAGE, Message.WRONG_USER_ID_INPUT);
                    logger.info("Redirect to main page");
                    response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
                    return;
                }
            }
            request.setAttribute(Message.ATTRIBUTE_ORDERS, orders);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(GoToPage.FORWARD_ORDER_PAGE);
            logger.info("Forward to order page");
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.error("Error while getting orders", e);
            request.getSession(true).setAttribute(Message.MESSAGE, Message.SERVICE_EXCEPTION);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        }
    }
}