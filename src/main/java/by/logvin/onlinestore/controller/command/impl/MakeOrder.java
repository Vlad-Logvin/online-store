package by.logvin.onlinestore.controller.command.impl;

import by.logvin.onlinestore.controller.command.Command;
import by.logvin.onlinestore.controller.message.GoToPage;
import by.logvin.onlinestore.controller.util.ExistenceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;

public class MakeOrder implements Command {
    private final Logger logger = Logger.getLogger(MakeOrder.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!ExistenceProvider.getInstance().getUserExistence().isUserExist(request, response)){
            return;
        }

        for(String s : request.getParameterValues("productID")) {
            logger.info("productID: " + s);
        }
        for(String s : request.getParameterValues("productQuantity")) {
            logger.info("productQuantity" + s);
        }

        response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
    }
}