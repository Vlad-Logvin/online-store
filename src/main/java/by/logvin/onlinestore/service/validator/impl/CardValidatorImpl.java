package by.logvin.onlinestore.service.validator.impl;

import by.logvin.onlinestore.service.validator.AuthorizationValidator;
import by.logvin.onlinestore.service.validator.CardValidator;

import java.time.LocalDateTime;

/**
 * The class CardValidatorImpl validate card data
 *
 * @author bylogvin
 * @see CardValidator
 */
public class CardValidatorImpl implements CardValidator {

    private final static String CARD_NUMBER_REGEX = "[0-9]{16}";
    private final static String VALIDITY_PERIOD_REGEX = "((1[0-2])|([1-9]))[0-9]{2}";
    private final static String AUTHENTICATION_CODE_REGEX = "[0-9]{3}";
    private final static Integer MAX_ISSUED_CARD = 5;
    private static final int NUMBER_SEPARATOR = 100;
    private static final int MONTH_NUMBER = 100;

    @Override
    public boolean validate(long number, int validityPeriod, int authenticationCode) {
        boolean isValidValidityPeriod = Integer.toString(validityPeriod).matches(VALIDITY_PERIOD_REGEX);
        if (isValidValidityPeriod) {
            int month = validityPeriod / NUMBER_SEPARATOR;
            int year = validityPeriod - month * NUMBER_SEPARATOR;
            LocalDateTime now = LocalDateTime.now();
            System.out.println(now);
            if (now.getYear() == year) {
                if (now.getMonthValue() < month) {
                    return false;
                }
            }
            if (month > MONTH_NUMBER) {
                return false;
            }
            if (year - MAX_ISSUED_CARD > now.getYear() % NUMBER_SEPARATOR) {
                return false;
            }
        } else {
            return false;
        }
        return Long.toString(number).matches(CARD_NUMBER_REGEX) && Integer.toString(authenticationCode).matches(AUTHENTICATION_CODE_REGEX);
    }
}
