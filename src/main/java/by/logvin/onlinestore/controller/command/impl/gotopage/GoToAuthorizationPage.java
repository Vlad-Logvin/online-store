package by.logvin.onlinestore.controller.command.impl.gotopage;

import by.logvin.onlinestore.controller.command.Command;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToAuthorizationPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute("url", "/Controller?command=go_to_authorization_page");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/authorization.jsp");
        requestDispatcher.forward(request, response);
    }
}
