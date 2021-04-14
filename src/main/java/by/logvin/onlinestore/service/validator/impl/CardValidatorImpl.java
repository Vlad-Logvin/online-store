package by.logvin.onlinestore.service.validator.impl;

import by.logvin.onlinestore.service.validator.CardValidator;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;

public class CardValidatorImpl implements CardValidator {

    private static final Logger logger = Logger.getLogger(CardValidatorImpl.class);

    private final static String CARD_NUMBER_REGEX = "[0-9]{16}";
    private final static String VALIDITY_PERIOD_REGEX = "((1[0-2])|([1-9]))[0-9]{2}";
    private final static String AUTHENTICATION_CODE_REGEX = "[0-9]{3}";
    private final static Integer MAX_ISSUED_CARD = 5;

    @Override
    public boolean validate(long number, int validityPeriod, int authenticationCode) {
        boolean isValidValidityPeriod = Integer.toString(validityPeriod).matches(VALIDITY_PERIOD_REGEX);
        logger.info("IsValidPeriod: " + isValidValidityPeriod);
        if (isValidValidityPeriod) {
            int month = validityPeriod / 100;
            int year = validityPeriod - month * 100;
            LocalDateTime now = LocalDateTime.now();
            System.out.println(now);
            if (now.getYear() == year) {
                if (now.getMonthValue() - month < 0) {
                    return false;
                }
            }
            if (month > 12) {
                return false;
            }
            if (year - MAX_ISSUED_CARD > now.getYear() % 100) {
                return false;
            }
        } else {
            return false;
        }
        logger.info("IsValidNumber: " + Long.toString(number).matches(CARD_NUMBER_REGEX));
        logger.info("IsValidCode: " + Integer.toString(authenticationCode).matches(AUTHENTICATION_CODE_REGEX));
        return Long.toString(number).matches(CARD_NUMBER_REGEX) && Integer.toString(authenticationCode).matches(AUTHENTICATION_CODE_REGEX);
    }
}
