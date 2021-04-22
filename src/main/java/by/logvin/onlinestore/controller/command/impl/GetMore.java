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
import org.apache.log4j.Logger;

import java.io.IOException;

public class GetMore implements Command {

    private static final Logger logger = Logger.getLogger(GetMore.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ExistenceProvider.getInstance().getUserExistence().isAdmin(request, response)) {
            return;
        }

        HttpSession session = request.getSession(false);


        try {
            if (ServiceProvider.getInstance().getProductService().orderProduct(
                    Integer.parseInt(request.getParameter(Message.ATTRIBUTE_PRODUCT_ID)),
                    Integer.parseInt(request.getParameter(Message.ATTRIBUTE_PRODUCT_QUANTITY))
            )) {
                session.setAttribute(Message.MESSAGE, Message.CORRECT_PRODUCT_GET_MORE);
            } else {
                session.setAttribute(Message.MESSAGE, Message.ERROR_PRODUCT_GET_MORE);
            }
            logger.info("Redirect to show product page");
            response.sendRedirect(GoToPage.REDIRECT_SHOW_PRODUCT_PAGE + Message.PARAMETER_PRODUCT_ID + Integer.parseInt(request.getParameter(Message.ATTRIBUTE_PRODUCT_ID)));
        } catch (ServiceException e) {
            logger.error("Error while product ordering", e);
            request.getSession(true).setAttribute(Message.MESSAGE, Message.SERVICE_EXCEPTION);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        } catch (NumberFormatException e) {
            logger.error("Error while parsing product id or quantity", e);
            request.getSession(true).setAttribute(Message.MESSAGE, Message.WRONG_PRODUCT_DATA_INPUT);
            response.sendRedirect((String) session.getAttribute(Message.ATTRIBUTE_URL));
        }

    }
}
