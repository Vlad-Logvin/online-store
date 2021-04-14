package by.logvin.onlinestore.controller.command.impl.gotopage;

import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.controller.command.Command;

import by.logvin.onlinestore.controller.message.GoToPage;
import by.logvin.onlinestore.controller.message.Message;
import by.logvin.onlinestore.service.ServiceProvider;
import by.logvin.onlinestore.service.exception.ServiceException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.util.List;

public class GoToMainPage implements Command {
    private static final Logger logger = Logger.getLogger(GoToMainPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute(Message.ATTRIBUTE_URL, GoToPage.REDIRECT_MAIN_PAGE);

        String sessionMessage = (String) session.getAttribute(Message.MESSAGE);
        if (sessionMessage != null) {
            request.setAttribute(Message.MESSAGE, sessionMessage);
            session.removeAttribute(Message.MESSAGE);
        }

        List<Product> products = null;
        try {
            products = ServiceProvider.getInstance().getProductService().take(9);
        } catch (ServiceException e) {
            response.sendRedirect(GoToPage.REDIRECT_ERROR_PAGE);
            return;
        }

        request.setAttribute(Message.ATTRIBUTE_PRODUCTS, products);

        RequestDispatcher dispatcher = request.getRequestDispatcher(GoToPage.FORWARD_MAIN_PAGE);
        dispatcher.forward(request, response);
    }
}
