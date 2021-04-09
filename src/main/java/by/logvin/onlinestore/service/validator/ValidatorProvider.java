package by.logvin.onlinestore.service.validator;

import by.logvin.onlinestore.service.validator.impl.AuthorizationValidatorImpl;
import by.logvin.onlinestore.service.validator.impl.CardValidatorImpl;

public class ValidatorProvider {
    private final static ValidatorProvider instance = new ValidatorProvider();
    private ValidatorProvider(){

    }
    public static ValidatorProvider getInstance() {
        return instance;
    }

    private final AuthorizationValidator authorizationValidator = new AuthorizationValidatorImpl();
    public AuthorizationValidator getAuthorizationValidator() {
        return authorizationValidator;
    }

    private final CardValidator cardValidator = new CardValidatorImpl();
    public CardValidator getCardValidator() {
        return cardValidator;
    }
}
