package by.logvin.onlinestore.service.validator;

import by.logvin.onlinestore.service.validator.impl.AuthorizationValidatorImpl;
import by.logvin.onlinestore.service.validator.impl.CardValidatorImpl;

/**
 * The class ValidatorProvider contains validator instances
 */
public class ValidatorProvider {
    /**
     * {@link ValidatorProvider} instance
     */
    private final static ValidatorProvider instance = new ValidatorProvider();
    private ValidatorProvider(){

    }

    /**
     *
     * @return {@link ValidatorProvider} instance
     */
    public static ValidatorProvider getInstance() {
        return instance;
    }

    /**
     * {@link AuthorizationValidator} instance
     */
    private final AuthorizationValidator authorizationValidator = new AuthorizationValidatorImpl();
    public AuthorizationValidator getAuthorizationValidator() {
        return authorizationValidator;
    }

    /**
     * {@link CardValidator} instance
     */
    private final CardValidator cardValidator = new CardValidatorImpl();
    public CardValidator getCardValidator() {
        return cardValidator;
    }
}
