package by.logvin.onlinestore.controller.command.impl;

import by.logvin.onlinestore.bean.User;
import by.logvin.onlinestore.controller.command.Command;
import by.logvin.onlinestore.controller.message.GoToPage;
import by.logvin.onlinestore.controller.message.Message;
import by.logvin.onlinestore.controller.util.ExistenceProvider;
import by.logvin.onlinestore.service.ServiceProvider;
import by.logvin.onlinestore.service.exception.ServiceException;
import by.logvin.onlinestore.service.util.impl.AttributeParserImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;

public class EditProduct implements Command {

    private static final Logger logger = Logger.getLogger(EditProduct.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!ExistenceProvider.getInstance().getUserExistence().isUserExist(request, response)){
            return;
        }
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(Message.ATTRIBUTE_USER);
        try {
            if (ServiceProvider.getInstance().getProductService().edit(
                    Integer.parseInt(request.getParameter(Message.ATTRIBUTE_PRODUCT_ID)),
                    request.getParameter(Message.ATTRIBUTE_PRODUCT_NAME),
                    Double.parseDouble(request.getParameter(Message.ATTRIBUTE_PRODUCT_PRICE)),
                    request.getParameter(Message.ATTRIBUTE_PRODUCT_DESCRIPTION),
                    Integer.parseInt(request.getParameter(Message.ATTRIBUTE_PRODUCT_QUANTITY)),
                    request.getParameter(Message.ATTRIBUTE_PRODUCT_PHOTO_URL),
                    ServiceProvider.getInstance().getCategoryService().takeCategory(request.getParameter(Message.ATTRIBUTE_PRODUCT_CATEGORY)).getId(),
                    new AttributeParserImpl().parse(request.getParameter(Message.ATTRIBUTE_PRODUCT_ATTRIBUTES))))
            {
                session.setAttribute(Message.ATTRIBUTE_USER, ServiceProvider.getInstance().getUserService().signIn(user.getEmail(), user.getPassword()));
                session.setAttribute(Message.MESSAGE, Message.CORRECT_PRODUCT_EDITING);
            } else {
                session.setAttribute(Message.MESSAGE, Message.ERROR_PRODUCT_EDITING);
            }
            logger.info("Redirect to last page");
            response.sendRedirect((String) session.getAttribute(Message.ATTRIBUTE_URL));
        } catch (ServiceException e) {
            logger.error("Error while product editing", e);
            request.getSession(true).setAttribute(Message.MESSAGE, Message.SERVICE_EXCEPTION);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        } catch (NumberFormatException | NullPointerException e) {
            logger.error("Error while parsing products data", e);
            request.getSession(true).setAttribute(Message.MESSAGE, Message.WRONG_PRODUCT_DATA_INPUT);
            response.sendRedirect((String) session.getAttribute(Message.ATTRIBUTE_URL));
        }
    }
}