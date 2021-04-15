package by.logvin.onlinestore.controller.command.impl;

import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.bean.User;
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
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MakeOrder implements Command {
    private final Logger logger = Logger.getLogger(MakeOrder.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ExistenceProvider.getInstance().getUserExistence().isUserExist(request, response)) {
            return;
        }

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(Message.ATTRIBUTE_USER);

        String[] productID = request.getParameterValues(Message.ATTRIBUTE_PRODUCT_ID);
        String[] productQuantity = request.getParameterValues(Message.ATTRIBUTE_PRODUCT_QUANTITY);
        int basketSize = productID.length;

        Map<Product, Integer> products = new HashMap<>();

        try {
            Product product = null;
            for (int i = 0; i < basketSize; i++) {
                product = ServiceProvider.getInstance().getProductService().takeByProductID(Integer.parseInt(productID[i]));
                products.put(product, Integer.valueOf(productQuantity[i]));
                ServiceProvider.getInstance().getProductService().buyProduct(Integer.parseInt(productID[i]), product.getQuantity() - Integer.parseInt(productQuantity[i]));
            }
            if (ServiceProvider.getInstance().getOrderService().makeOrder(
                    user.getId(),
                    products,
                    Integer.parseInt(request.getParameter(Message.ATTRIBUTE_CARD_ID)),
                    new Timestamp(new Date().getTime()))) {
                session.setAttribute(Message.MESSAGE, Message.CORRECT_MAKE_ORDER);
                ServiceProvider.getInstance().getBasketService().deleteBasketByUserID(user.getId());
                ServiceProvider.getInstance().getBasketService().createBasket(user.getId());
                user.getUserDetails().setBasket(ServiceProvider.getInstance().getBasketService().getBasketByUserID(user.getId()));
                user.getUserDetails().setOrders(ServiceProvider.getInstance().getOrderService().getUserOrders(user.getId()));
            } else {
                session.setAttribute(Message.MESSAGE, Message.ERROR_MAKE_ORDER);
            }
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        } catch (ServiceException e) {
            request.getSession(true).setAttribute(Message.MESSAGE, Message.SERVICE_EXCEPTION);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        } catch (NumberFormatException e) {
            request.getSession(true).setAttribute(Message.MESSAGE, Message.WRONG_MAKE_ORDER_INPUT);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        }

    }
}