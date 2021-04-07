package by.logvin.onlinestore.controller.command.impl;

import by.logvin.onlinestore.bean.RegistrationInfo;
import by.logvin.onlinestore.controller.command.Command;

import by.logvin.onlinestore.dao.UserDAO;
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

public class SignUp implements Command {
    private final static Logger logger = Logger.getLogger(SignUp.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirectURL = null;

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dateOfBirth = request.getParameter("dateOfBirth");

        logger.info("email: " + email);
        logger.info("password: " + password);
        logger.info("firstName: " + firstName);
        logger.info("lastName: " + lastName);
        logger.info("dateOfBirth: " + dateOfBirth);

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();

        try {
            boolean isSignUp = userService.signUp(new RegistrationInfo(
                    email,
                    password,
                    firstName,
                    lastName,
                    dateOfBirth
            ));
            logger.info("isSignUp: " + isSignUp);
            if (isSignUp) {
                redirectURL = "Controller?command=go_to_authorization_page&message=correct";
            } else {
                redirectURL = "Controller?command=go_to_registration_page&message=wrongRegistration";
            }
        } catch (ServiceException e) {
            logger.info("ServiceException: ", e);
            redirectURL = "Controller?command=go_to_registration_page&message=wrongRegistration";
        }

        response.sendRedirect(redirectURL);
    }
}
