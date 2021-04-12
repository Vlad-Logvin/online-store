package by.logvin.onlinestore.controller.command.impl;

import by.logvin.onlinestore.bean.User;
import by.logvin.onlinestore.controller.command.Command;
import by.logvin.onlinestore.service.ServiceProvider;
import by.logvin.onlinestore.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;

public class RemoveFromFavourite implements Command {
    private final static Logger logger = Logger.getLogger(RemoveFromFavourite.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirectURL = null;
        HttpSession session = request.getSession(false);
        if (session == null) {
            redirectURL = "Controller?command=go_to_authorization_page&message=session_has_timed_out";
        } else {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                redirectURL = "Controller?command=go_to_authorization_page&message=not_authorized_user";
            } else {
                try {
                    boolean isRemove = ServiceProvider.getInstance().getFavouriteService().removeProduct(user.getUserDetails().getFavourite().getId(), Integer.parseInt(request.getParameter("productID")));
                    logger.info(isRemove);
                    redirectURL = (String) session.getAttribute("url");
                } catch (ServiceException e) {
                    //log
                    // FIXME: 12.04.2021
                    redirectURL = "Controller?command=go_to_main_page&message=somethingWrong";
                }
            }
        }
        response.sendRedirect(redirectURL);
    }
}