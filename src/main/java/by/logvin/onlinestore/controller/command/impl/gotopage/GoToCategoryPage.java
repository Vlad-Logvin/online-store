package by.logvin.onlinestore.controller.command.impl.gotopage;

import by.logvin.onlinestore.bean.Category;
import by.logvin.onlinestore.controller.command.Command;
import by.logvin.onlinestore.service.ServiceProvider;
import by.logvin.onlinestore.service.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;

public class GoToCategoryPage implements Command {
    private static final Logger logger = Logger.getLogger(GoToCategoryPage.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        int categoryID = 0;
        try {
            categoryID = Integer.parseInt(request.getParameter("categoryID"));
        } catch (NumberFormatException e){
            //log
            // FIXME: 12.04.2021
            response.sendRedirect((String) session.getAttribute("url"));
        }
        try {
            Category category = ServiceProvider.getInstance().getCategoryService().getCategory(categoryID);
            request.setAttribute("category", category);
            request.setAttribute("products", ServiceProvider.getInstance().getProductService().take(category));
            session.setAttribute("url", "Controller?command=go_to_category_page&categoryID=" + categoryID);
            logger.info(category);
            logger.info(ServiceProvider.getInstance().getProductService().take(category));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/category.jsp");
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            //log
            response.sendRedirect((String) session.getAttribute("url"));
        }
    }
}
