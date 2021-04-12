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

public class GoToAddCardFormPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("Controller?command=go_to_authorization_page&message=session_has_timed_out");
        } else {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect("Controller?command=go_to_authorization_page&message=not_authorized_user");
            } else {

                request.setAttribute("action", "addCard");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cardForm.jsp");
                requestDispatcher.forward(request, response);
            }
        }
    }
}