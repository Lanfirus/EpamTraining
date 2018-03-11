package Lesson4.ua.training.controller;

import java.util.ResourceBundle;
import java.util.stream.Stream;

/**
 * Stores information about allowed and correct user groups to choose from.
 */
abstract class Groups {

    final static String EMPLOYEES = "Employees";
    final static String MANAGEMENT = "Management";
    final static String CLIENTS = "Clients";
    final static String SUBCONTRACTORS = "Subcontractors";
    final static String OUTSORCE = "Outsource";

    /**
     * Provides stream of names of the groups adapted to user language.
     *
     * @return
     */
    static Stream<String> returnGroupsStream(){
        ResourceBundle bundle = Controller.getGroupsBundle();
        Stream<String> temp = Stream.of(bundle.getString(EMPLOYEES), bundle.getString(MANAGEMENT),
                bundle.getString(CLIENTS), bundle.getString(SUBCONTRACTORS), bundle.getString(OUTSORCE));
        return temp;
    }
}
