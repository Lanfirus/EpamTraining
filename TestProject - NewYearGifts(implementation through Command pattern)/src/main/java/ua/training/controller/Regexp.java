package ua.training.controller;

import java.util.Arrays;
import java.util.List;

/**
 * @author Dudchenko Andrei
 */
final class Regexp {

    private final static String NAME = "name";
    private final static String SURNAME = "surname";
    private final static String PATRONYMIC = "patronymic";
    private final static String LOGIN = "login";
    private final static String PASSWORD = "password";
    private final static String COMMENT = "comment";
    private final static String HOME_PHONE_NUMBER = "homePhoneNumber";
    private final static String MOBILE_PHONE_NUMBER = "mobilePhoneNumber";
    private final static String EMAIL = "email";

    /**
     * Provides string array of regexps.
     *
     * @return
     */
    static List<String> returnRegexpArray(){
        List<String> temp = Arrays.asList(NAME, SURNAME, PATRONYMIC, LOGIN, PASSWORD, COMMENT, HOME_PHONE_NUMBER, MOBILE_PHONE_NUMBER, EMAIL);
        return temp;
    }
}
