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
    private final static String PHONE_NUMBER_HOME = "phoneNumberHome";
    private final static String PHONE_NUMBER_MOBILE = "phoneNumberMobile";
    private final static String EMAIL = "email";

    /**
     * Provides string array of regexps.
     *
     * @return
     */
    static List<String> returnRegexpArray(){
        List<String> temp = Arrays.asList(NAME, SURNAME, PATRONYMIC, LOGIN, PASSWORD, COMMENT, PHONE_NUMBER_HOME, PHONE_NUMBER_MOBILE, EMAIL);
        return temp;
    }
}
