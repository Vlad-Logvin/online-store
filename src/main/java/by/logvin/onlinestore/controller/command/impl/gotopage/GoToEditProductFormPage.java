package by.logvin.onlinestore.controller.command.impl.gotopage;

import by.logvin.onlinestore.bean.User;
import by.logvin.onlinestore.controller.command.Command;
import by.logvin.onlinestore.service.ServiceProvider;
import by.logvin.onlinestore.service.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class GoToEditProductFormPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("Controller?command=go_to_authorization_page&message=session_has_timed_out");
        } else {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect("Controller?command=go_to_authorization_page&message=not_authorized_user");
            } else if (!"admin".equals(((User) session.getAttribute("user")).getUserDetails().getRole())) {
                response.sendRedirect("Controller?command=go_to_main_page");
            } else {
                try {
                    String productID = request.getParameter("productID");
                    request.setAttribute("action", "editProduct");
                    request.setAttribute("product", ServiceProvider.getInstance().getProductService().takeByProductID(Integer.valueOf(productID)));
                    request.setAttribute("categories", ServiceProvider.getInstance().getCategoryService().getCategories());
                    request.setAttribute("attributes", ServiceProvider.getInstance().getAttributeService().getParsedAttributes(Integer.valueOf(productID)));
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productForm.jsp");
                    requestDispatcher.forward(request, response);
                    session.setAttribute("url", "Controller?command=go_to_edit_product_form_page&productID=" + productID);
                } catch (ServiceException e) {
                    // FIXME: 11.04.2021
                    //log
                    response.sendRedirect("Controller?command=go_to_main_page&message=somethingWrong");
                }
            }
        }
    }
}