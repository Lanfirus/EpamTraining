package Task6.ua.training.controller;

import java.util.stream.Stream;

/**
 * Stores all messages used by the program.
 */
final class Messages {

    final static String LANGUAGE_CHOICE_MESSAGE = "language.set";
    final static String LANGUAGE_INCORRECT_INPUT = "language.set.incorrect.input";
    final static String LANGUAGE_SET_MESSAGE = "language.set.confirmation";

    final static String EXCEPTION_HANDLER_MESSAGE = "exception.handler.message";

    final static String INPUT_ERROR_MESSAGE = "input.error";
    final static String INPUT_SURNAME = "input.surname";
    final static String INPUT_NAME = "input.name";
    final static String INPUT_PATRONYMIC = "input.patronymic";
    final static String INPUT_MODIFIED_NAME = "input.modifiedName";
    final static String INPUT_LOGIN = "input.login";
    final static String INPUT_COMMENT = "input.comment";
    final static String INPUT_GROUPS = "input.groups";
    final static String INPUT_PHONE_NUMBER_HOME = "input.phoneNumber.home";
    final static String INPUT_PHONE_NUMBER_MOBILE = "input.phoneNumber.mobile";
    final static String INPUT_PHONE_NUMBER_MOBILE_2 = "input.phoneNumber.mobile2";
    final static String INPUT_EMAIL = "input.email";
    final static String INPUT_SKYPE = "input.skype";
    final static String INPUT_ADDRESS_INDEX = "input.address.index";
    final static String INPUT_ADDRESS_CITY = "input.address.city";
    final static String INPUT_ADDRESS_STREET = "input.address.street";
    final static String INPUT_ADDRESS_BUILDING = "input.address.building";
    final static String INPUT_ADDRESS_APARTMENT = "input.address.apartment";
    final static String INPUT_ADDRESS_FULL = "input.address.full";

    final static String GROUP_NAME_ERROR = "group.error";

    /**
     * Provides stream of all messages related to used input interactions.
     *
     * @return
     */
    static Stream<String> returnInputMessagesStream() {
        Stream<String> temp = Stream.of(INPUT_SURNAME, INPUT_NAME, INPUT_PATRONYMIC, INPUT_MODIFIED_NAME,
                INPUT_LOGIN, INPUT_COMMENT, INPUT_GROUPS, INPUT_PHONE_NUMBER_HOME, INPUT_PHONE_NUMBER_MOBILE,
                INPUT_PHONE_NUMBER_MOBILE_2, INPUT_EMAIL, INPUT_SKYPE, INPUT_ADDRESS_INDEX, INPUT_ADDRESS_CITY,
                INPUT_ADDRESS_STREET, INPUT_ADDRESS_BUILDING, INPUT_ADDRESS_APARTMENT, INPUT_ADDRESS_FULL);
        return temp;
    }
}
