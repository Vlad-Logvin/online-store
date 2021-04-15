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

public class RemoveFromFavourite implements Command {
    private final static Logger logger = Logger.getLogger(RemoveFromFavourite.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ExistenceProvider.getInstance().getUserExistence().isUserExist(request, response)) {
            return;
        }

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(Message.ATTRIBUTE_USER);
        try {
            if (ServiceProvider.getInstance().getFavouriteService().removeProduct(user.getUserDetails().getFavourite().getId(), Integer.parseInt(request.getParameter(Message.ATTRIBUTE_PRODUCT_ID)))) {
                session.setAttribute(Message.MESSAGE, Message.CORRECT_REMOVE_FROM_FAVOURITE);
                user.getUserDetails().getFavourite().getProducts().add(ServiceProvider.getInstance().getProductService().takeByProductID(Integer.parseInt(request.getParameter(Message.ATTRIBUTE_PRODUCT_ID))));
            }else {
                session.setAttribute(Message.MESSAGE, Message.ERROR_REMOVE_FROM_FAVOURITE);
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