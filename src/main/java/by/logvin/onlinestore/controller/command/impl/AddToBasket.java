package by.logvin.onlinestore.controller.command.impl;

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

/**
 * The AddToBasket class is responsible for adding product to basket
 *
 * @author bylogvin
 * @see by.logvin.onlinestore.controller.command.Command
 */
public class AddToBasket implements Command {

    private static final Logger logger = Logger.getLogger(AddToBasket.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ExistenceProvider.getInstance().getUserExistence().isUserExist(request, response)) {
            return;
        }
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(Message.ATTRIBUTE_USER);
        try {
            if (ServiceProvider.getInstance().getBasketService().addProduct(user.getUserDetails().getBasket().getId(), Integer.parseInt(request.getParameter(Message.ATTRIBUTE_PRODUCT_ID)))) {
                session.setAttribute(Message.MESSAGE, Message.CORRECT_ADD_TO_BASKET);
                user.getUserDetails().getBasket().getProducts().add(ServiceProvider.getInstance().getProductService().takeByProductID(Integer.parseInt(request.getParameter(Message.ATTRIBUTE_PRODUCT_ID))));
            } else {
                session.setAttribute(Message.MESSAGE, Message.ERROR_ADD_TO_BASKET);
            }
            logger.info("Redirect to last page");
            response.sendRedirect((String) session.getAttribute(Message.ATTRIBUTE_URL));
        } catch (ServiceException e) {
            logger.error("Error while adding product to basket", e);
            request.getSession(true).setAttribute(Message.MESSAGE, Message.SERVICE_EXCEPTION);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        } catch (NumberFormatException e) {
            logger.error("Error while parsing product id", e);
            request.getSession(true).setAttribute(Message.MESSAGE, Message.WRONG_PRODUCT_INPUT);
            response.sendRedirect((String) session.getAttribute(Message.ATTRIBUTE_URL));
        }

    }
}
