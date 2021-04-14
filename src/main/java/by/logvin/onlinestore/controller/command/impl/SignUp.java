package by.logvin.onlinestore.controller.command.impl;

import by.logvin.onlinestore.bean.RegistrationInfo;
import by.logvin.onlinestore.controller.command.Command;

import by.logvin.onlinestore.controller.message.GoToPage;
import by.logvin.onlinestore.controller.message.Message;
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
import javax.print.attribute.standard.MediaSize;
import java.io.IOException;

public class SignUp implements Command {
    private final static Logger logger = Logger.getLogger(SignUp.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dateOfBirth = request.getParameter("dateOfBirth");

        HttpSession session = request.getSession(true);
        try {
            if(ServiceProvider.getInstance().getUserService().signUp(new RegistrationInfo(
                    request.getParameter("email"),
                    request.getParameter("password"),
                    request.getParameter("firstName"),
                    request.getParameter("lastName"),
                    request.getParameter("dateOfBirth")
            ))){
                session.setAttribute(Message.MESSAGE, Message.CORRECT_SIGN_UP);
            }else {
                session.setAttribute(Message.MESSAGE, Message.ERROR_SIGN_UP);
            }
            response.sendRedirect(GoToPage.REDIRECT_AUTHORIZATION_PAGE);
        } catch (ServiceException e) {
            request.getSession(true).setAttribute(Message.MESSAGE, Message.SERVICE_EXCEPTION);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        }
    }
}
