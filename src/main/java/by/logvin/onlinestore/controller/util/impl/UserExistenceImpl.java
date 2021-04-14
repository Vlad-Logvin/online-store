package by.logvin.onlinestore.controller.util.impl;

import by.logvin.onlinestore.bean.User;
import by.logvin.onlinestore.controller.message.GoToPage;
import by.logvin.onlinestore.controller.message.Message;
import by.logvin.onlinestore.controller.util.ExistenceProvider;
import by.logvin.onlinestore.controller.util.UserExistence;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;

public class UserExistenceImpl implements UserExistence {

    private final static Logger logger = Logger.getLogger(UserExistenceImpl.class);

    @Override
    public boolean isUserExist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!ExistenceProvider.getInstance().getSessionExistence().isSessionExist(request, response)) {
            return false;
        }

        HttpSession session = request.getSession(false);

        if (session!=null && session.getAttribute(Message.ATTRIBUTE_USER) == null) {
            session.setAttribute(Message.MESSAGE, Message.NOT_AUTHORIZED_USER);
            response.sendRedirect(GoToPage.REDIRECT_AUTHORIZATION_PAGE);
            return false;
        }

        return true;
    }

    @Override
    public boolean isAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(!isUserExist(request, response)){
            return false;
        }

        if(!Message.ATTRIBUTE_ADMIN.equals(((User)request.getSession(false).getAttribute(Message.ATTRIBUTE_USER)).getUserDetails().getRole())){
            request.getSession(true).setAttribute(Message.MESSAGE, Message.NOT_ENOUGH_PERMISSION);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
            return false;
        }

        return true;
    }
}
