package by.logvin.onlinestore.controller.command.impl;

import by.logvin.onlinestore.bean.User;
import by.logvin.onlinestore.controller.command.Command;
import by.logvin.onlinestore.service.ServiceProvider;
import by.logvin.onlinestore.service.UserService;
import by.logvin.onlinestore.service.exception.ServiceException;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.apache.log4j.Logger;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignIn implements Command {
    private final static Logger logger = Logger.getLogger(SignIn.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        logger.info("email + " + email);
        logger.info("password + " + password);

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        User user = null;
        String redirectURL = null;
        HttpSession session = null;
        try {
            user = userService.signIn(email, password);
            if (user == null) {
                redirectURL = "Controller?command=go_to_authorization_page&message=wrong";
            } else {
                session = request.getSession();
                session.setAttribute("user", user);
                redirectURL = "Controller?command=go_to_main_page";
            }
        } catch (ServiceException e) {
            logger.info("ServiceException", e);
            redirectURL = "Controller?command=go_to_authorization_page&message=wrong";
        }


        response.sendRedirect(redirectURL);
    }
}
