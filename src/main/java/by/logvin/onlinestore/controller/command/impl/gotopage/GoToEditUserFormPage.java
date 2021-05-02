package by.logvin.onlinestore.controller.command.impl.gotopage;

import by.logvin.onlinestore.controller.command.Command;
import by.logvin.onlinestore.controller.message.GoToPage;
import by.logvin.onlinestore.controller.util.ExistenceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * The GoToEditUserFormPage class is responsible for going to edit user form page
 *
 * @author bylogvin
 * @see by.logvin.onlinestore.controller.command.Command
 */
public class GoToEditUserFormPage implements Command {

    private final static Logger logger = Logger.getLogger(GoToEditUserFormPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!ExistenceProvider.getInstance().getUserExistence().isUserExist(request, response)){
            return;
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(GoToPage.FORWARD_EDIT_USER_FORM_PAGE);
        logger.info("Forward to edit user form page");
        requestDispatcher.forward(request, response);
    }
}