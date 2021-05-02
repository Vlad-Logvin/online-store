package by.logvin.onlinestore.controller.command;

import by.logvin.onlinestore.controller.command.impl.*;
import by.logvin.onlinestore.controller.command.impl.gotopage.*;
import org.apache.log4j.Logger;

import java.util.EnumMap;
import java.util.Map;

public class CommandProvider {
    private static final Logger logger = Logger.getLogger(CommandProvider.class);

    private Map<CommandName, Command> commands = new EnumMap<>(CommandName.class);

    public CommandProvider() {
        commands.put(CommandName.ADD_CARD, new AddCard());
        commands.put(CommandName.ADD_PRODUCT, new AddProduct());
        commands.put(CommandName.ADD_TO_BASKET, new AddToBasket());
        commands.put(CommandName.ADD_TO_FAVOURITE, new AddToFavourite());
        commands.put(CommandName.CHANGE_LOCALE, new ChangeLocale());
        commands.put(CommandName.DELETE_CARD, new DeleteCard());
        commands.put(CommandName.DELETE_PRODUCT, new DeleteProduct());
        commands.put(CommandName.EDIT_CARD, new EditCard());
        commands.put(CommandName.EDIT_PRODUCT, new EditProduct());
        commands.put(CommandName.EDIT_PROFILE, new EditProfile());
        commands.put(CommandName.GET_MORE, new GetMore());
        commands.put(CommandName.MANAGE_ACCESS, new ManageAccess());
        commands.put(CommandName.MANAGE_ROLE, new ManageRole());
        commands.put(CommandName.LOGOUT, new Logout());
        commands.put(CommandName.MAKE_ORDER, new MakeOrder());
        commands.put(CommandName.REMOVE_FROM_BASKET, new RemoveFromBasket());
        commands.put(CommandName.REMOVE_FROM_FAVOURITE, new RemoveFromFavourite());
        commands.put(CommandName.SIGN_IN, new SignIn());
        commands.put(CommandName.SIGN_UP, new SignUp());

        commands.put(CommandName.GO_TO_ADD_CARD_FORM_PAGE, new GoToAddCardFormPage());
        commands.put(CommandName.GO_TO_ADD_PRODUCT_FORM_PAGE, new GoToAddProductFormPage());
        commands.put(CommandName.GO_TO_AUTHORIZATION_PAGE, new GoToAuthorizationPage());
        commands.put(CommandName.GO_TO_BASKET_PAGE, new GoToBasketPage());
        commands.put(CommandName.GO_TO_CATEGORY_PAGE, new GoToCategoryPage());
        commands.put(CommandName.GO_TO_EDIT_CARD_FORM_PAGE, new GoToEditCardFormPage());
        commands.put(CommandName.GO_TO_EDIT_PRODUCT_FORM_PAGE, new GoToEditProductFormPage());
        commands.put(CommandName.GO_TO_EDIT_USER_FORM_PAGE, new GoToEditUserFormPage());
        commands.put(CommandName.GO_TO_ERROR_PAGE, new GoToErrorPage());
        commands.put(CommandName.GO_TO_FAVOURITE_PAGE, new GoToFavouritePage());
        commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
        commands.put(CommandName.GO_TO_ORDER_PAGE, new GoToOrderPage());
        commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
        commands.put(CommandName.GO_TO_SHOW_PRODUCT_PAGE, new GoToShowProductPage());
        commands.put(CommandName.GO_TO_SHOW_USER_PAGE, new GoToShowUserPage());
        commands.put(CommandName.GO_TO_USER_PAGE, new GoToUserPage());
    }

    public Command takeCommand(String name) {
        CommandName commandName = null;
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.info("Not correct command");
            commandName = CommandName.GO_TO_ERROR_PAGE;
        }
        return commands.get(commandName);
    }
}
