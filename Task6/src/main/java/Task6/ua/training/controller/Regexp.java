package Task6.ua.training.controller;

import java.util.stream.Stream;

final class Regexp {

    private final static String SURNAME = "surname";
    private final static String NAME = "name";
    private final static String PATRONYMIC = "patronymic";
    private final static String MODIFIED_NAME = "modifiedName";
    private final static String LOGIN = "login";
    private final static String COMMENT = "comment";
    private final static String GROUPS = "groups";
    private final static String PHONE_NUMBER_HOME = "phoneNumberHome";
    private final static String PHONE_NUMBER_MOBILE = "phoneNumberMobile";
    private final static String PHONE_NUMBER_MOBILE_2 = "phoneNumberMobile2";
    private final static String EMAIL = "email";
    private final static String SKYPE = "skype";
    private final static String ADDRESS_INDEX = "address.index";
    private final static String ADDRESS_CITY = "address.city";
    private final static String ADDRESS_STREET = "address.street";
    private final static String ADDRESS_BUILDING = "address.buildingNumber";
    private final static String ADDRESS_APARTMENT = "address.apartmentNumber";

    /**
     * Provides stream of regexps.
     *
     * @return
     */
    static Stream<String> returnRegexpStream(){
        Stream<String> temp = Stream.of(SURNAME, NAME, PATRONYMIC, MODIFIED_NAME, LOGIN, COMMENT, GROUPS,
                PHONE_NUMBER_HOME, PHONE_NUMBER_MOBILE, PHONE_NUMBER_MOBILE_2, EMAIL, SKYPE, ADDRESS_INDEX,
                ADDRESS_CITY, ADDRESS_STREET, ADDRESS_BUILDING, ADDRESS_APARTMENT);
        return temp;
    }
}
