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

public class GoToOrderPage implements Command {

    private final static Logger logger = Logger.getLogger(GoToOrderPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ExistenceProvider.getInstance().getUserExistence().isUserExist(request, response)) {
            return;
        }
        int userID = 0;
        try {
            userID = Integer.parseInt(request.getParameter(Message.ATTRIBUTE_USER_ID));
        } catch (NumberFormatException e) {
            request.getSession(true).setAttribute(Message.MESSAGE, Message.WRONG_USER_ID_INPUT);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
            return;
        }

        List<Order> orders = null;
        try {
            if (userID == 0) {
                if(!ExistenceProvider.getInstance().getUserExistence().isAdmin(request, response)){
                    return;
                }
                orders = ServiceProvider.getInstance().getOrderService().getOrderLog();
            } else {
                if (userID == ((User) request.getSession(false).getAttribute(Message.ATTRIBUTE_USER)).getId()) {
                    orders = ServiceProvider.getInstance().getOrderService().getUserOrders(userID);
                } else {
                    request.getSession(true).setAttribute(Message.MESSAGE, Message.WRONG_USER_ID_INPUT);
                    response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
                    return;
                }
            }
            request.setAttribute(Message.ATTRIBUTE_ORDERS, orders);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(GoToPage.FORWARD_ORDER_PAGE);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            request.getSession(true).setAttribute(Message.MESSAGE, Message.SERVICE_EXCEPTION);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        }
    }
}