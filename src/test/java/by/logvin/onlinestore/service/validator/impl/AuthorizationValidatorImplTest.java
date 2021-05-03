package by.logvin.onlinestore.service.validator.impl;


import by.logvin.onlinestore.bean.RegistrationInfo;
import by.logvin.onlinestore.service.validator.AuthorizationValidator;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class AuthorizationValidatorImplTest {

    private final static AuthorizationValidator validator = new AuthorizationValidatorImpl();

    private static Stream<Arguments> userEmailAndPassword() {
        return Stream.of(
                Arguments.of("vladlogvin97@gmail.com", "password", true),
                Arguments.of("vlad", "pass", false),
                Arguments.of("v@f.c", "password", true),
                Arguments.of("vlad@gmail.com", "pass", false),
                Arguments.of("vlad@gmail.com", "!ofwefoefk", false),
                Arguments.of("vlad@gmail.com", "mrefj+", false),
                Arguments.of("1@gmail.com", "password", true),
                Arguments.of("vlad@gmail.com", "lefw-oekw", false),
                Arguments.of("vlad@gmail.com", "lfwe_ew", true)
        );
    }

    @ParameterizedTest
    @MethodSource("userEmailAndPassword")
    void validate(String email, String password, boolean expected) {
        Assert.assertEquals(expected, validator.validate(email, password));
    }

    private static Stream<Arguments> userRegistrationInfo() {
        return Stream.of(
                Arguments.of(new RegistrationInfo("vlad@gmail.com", "password",
                        "Vlad", "Logvin", "2001-12-28"), true),
                Arguments.of(new RegistrationInfo("vlad@gmail.com", "password",
                        "Vlad2", "Logvin", "2001-12-28"), false),
                Arguments.of(new RegistrationInfo("vlad@gmail.com", "password",
                        "Vlad", "Logvin", null), true),
                Arguments.of(new RegistrationInfo("vlad@gmail.com", "password",
                        "Vlad", "Logvin2", "2001-12-28"), false),
                Arguments.of(new RegistrationInfo("vlad@gmail.", "password",
                        "Vlad", "Logvin", "2001-12-28"), false),
                Arguments.of(new RegistrationInfo("vlad@gmail.com", "password",
                        "Vlad", "Logvin", "20-12-28"), true),
                Arguments.of(new RegistrationInfo("vlad@gmail.com", "pass",
                        "Vlad", "Logvin", "2001-12-28"), false),
                Arguments.of(new RegistrationInfo("@gmail.com", "password",
                        "Vlad", "Logvin", "2001-12-28"), false),
                Arguments.of(new RegistrationInfo("vlad@gmail.com", "0123456789012345678901234567890",
                        "Vlad", "Logvin", "2001-12-28"), false)
        );
    }

    @ParameterizedTest
    @MethodSource("userRegistrationInfo")
    void validate(RegistrationInfo info, boolean expected) {
        Assert.assertEquals(expected, validator.validate(info));
    }
}
