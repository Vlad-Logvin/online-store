package by.logvin.onlinestore.service.validator.impl;

import by.logvin.onlinestore.service.validator.CardValidator;

public class CardValidatorImpl implements CardValidator {

    @Override
    public boolean validate(long number, int validityPeriod, int authenticationCode) {
        return false;
    }
}
