package by.logvin.onlinestore.controller.command.impl;

import by.logvin.onlinestore.bean.Category;
import by.logvin.onlinestore.controller.command.Command;
import by.logvin.onlinestore.controller.message.GoToPage;
import by.logvin.onlinestore.controller.message.Message;
import by.logvin.onlinestore.controller.util.ExistenceProvider;
import by.logvin.onlinestore.service.ProductService;
import by.logvin.onlinestore.service.ServiceProvider;
import by.logvin.onlinestore.service.exception.ServiceException;
import by.logvin.onlinestore.service.util.impl.AttributeParserImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AddProduct implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!ExistenceProvider.getInstance().getUserExistence().isAdmin(request, response)) {
            return;
        }
        HttpSession session = request.getSession(false);
        try {
            if (ServiceProvider.getInstance().getProductService().add(
                    request.getParameter(Message.ATTRIBUTE_PRODUCT_NAME),
                    Double.parseDouble(request.getParameter(Message.ATTRIBUTE_PRODUCT_PRICE)),
                    request.getParameter(Message.ATTRIBUTE_PRODUCT_DESCRIPTION),
                    Integer.parseInt(request.getParameter(Message.ATTRIBUTE_PRODUCT_QUANTITY)),
                    request.getParameter(Message.ATTRIBUTE_PRODUCT_PHOTO_URL),
                    ServiceProvider.getInstance().getCategoryService().getCategory(request.getParameter(Message.ATTRIBUTE_PRODUCT_CATEGORY)).getId(),
                    new AttributeParserImpl().parse(request.getParameter(Message.ATTRIBUTE_PRODUCT_ATTRIBUTES))))
            {
                session.setAttribute(Message.MESSAGE, Message.CORRECT_ADDING_PRODUCT);
            } else {
                session.setAttribute(Message.MESSAGE, Message.ERROR_ADDING_PRODUCT);
            }
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        } catch (NumberFormatException e) {
            request.getSession(true).setAttribute(Message.MESSAGE, Message.WRONG_PRODUCT_DATA_INPUT);
            response.sendRedirect((String) session.getAttribute(Message.ATTRIBUTE_URL));
        } catch (ServiceException e) {
            request.getSession(true).setAttribute(Message.MESSAGE, Message.SERVICE_EXCEPTION);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        }

    }
}