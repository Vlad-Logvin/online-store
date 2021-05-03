package by.logvin.onlinestore.service.validator.impl;

import by.logvin.onlinestore.service.validator.CardValidator;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class CardValidatorImplTest {

    private static final CardValidator validator = new CardValidatorImpl();

    private static Stream<Arguments> cardData() {
        return Stream.of(
                Arguments.of(1234123412341234L, 1234, 213, false),
                Arguments.of(1234123412341234L, 225, 213, true),
                Arguments.of(123412341234234L, 1234, 213, false),
                Arguments.of(1234123412341234L, 12, 213, false),
                Arguments.of(1234123412341234L, 1234, 21, false),
                Arguments.of(1234123412341234L, 1312, 213, false),
                Arguments.of(12341234123413234L, 1220, 213, false),
                Arguments.of(1234123412341234L, 1234, 2100, false)
        );
    }

    @ParameterizedTest
    @MethodSource("cardData")
    void validate(long number, int validityPeriod, int authenticationCode, boolean expected) {
        Assert.assertEquals(expected, validator.validate(number, validityPeriod, authenticationCode));
    }
}
