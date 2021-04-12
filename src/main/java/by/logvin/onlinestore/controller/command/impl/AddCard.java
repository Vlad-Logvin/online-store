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

public class AddCard implements Command {
    private final static Logger logger = Logger.getLogger(AddCard.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("Controller?command=go_to_authorization_page&message=session_has_timed_out");
            return;
        }
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("Controller?command=go_to_authorization_page&message=not_authorized_user");
            return;
        }
        try {
            boolean isAdd = ServiceProvider.getInstance().getCardService().addCard(
                    Long.parseLong(request.getParameter("cardNumber")),
                    Integer.parseInt(request.getParameter("cardValidityPeriod")),
                    Integer.parseInt(request.getParameter("cardAuthenticationCode")),
                    request.getParameter("cardCardholder"),
                    user.getId()
            );
            if (isAdd) {
                session.setAttribute("user", ServiceProvider.getInstance().getUserService().signIn(user.getEmail(), user.getPassword()));
                response.sendRedirect(session.getAttribute("url") + "&message=correctAddingCard");
            } else {
                response.sendRedirect(session.getAttribute("url") + "&message=wrongAdding");
            }
        } catch (ServiceException e) {
            response.sendRedirect(session.getAttribute("url") + "&message=somethingWrong");
        } catch (NumberFormatException e) {
            response.sendRedirect("Controller?command=go_to_add_card_form_page&message=wrongInput");
        }

    }
}