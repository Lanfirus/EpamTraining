package ua.training.controller;

import ua.training.model.DBEmu;
import ua.training.model.NotUniqueLoginException;
import ua.training.model.Notebook;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Dudchenko Andrei
 */
public class ControllerWeb {

    private Notebook model;

    private ResourceBundle regexpBundle;
    private final static String REGEXP_BUNDLE_NAME = "regexp";
    private static ResourceBundle groupsBundle;
    private final static String GROUPS_BUNDLE_NAME = "groups";

    private List<String> regexpArray;
    private Map<Integer, Object> mapToResendToUser = new ConcurrentHashMap<>();

    private int surnameIndex;
    private int nameIndex;
    private int addressIndexIndex;
    private int addressApartmentIndex;
    private int finalFullAddressIndex;
    private int modifiedNameIndex;
    private int groupsIndex;
    private int fullAddressInitialIndex;
    private int creationDateIndex;
    private int lastChangeDateIndex;
    private int loginIndex;

    {
        surnameIndex = 0;
        nameIndex = 1;
        modifiedNameIndex = 3;
        loginIndex = 4;
        groupsIndex = 6;
        addressIndexIndex = 12;
        finalFullAddressIndex = 13;
        creationDateIndex = 14;
        lastChangeDateIndex = 15;
        addressApartmentIndex = 16;
        fullAddressInitialIndex = 17;
    }

    public ControllerWeb(Notebook model) {
        this.model = model;
    }

    public ResourceBundle getRegexpBundle() {
        return regexpBundle;
    }

    public static ResourceBundle getGroupsBundle() {
        return groupsBundle;
    }

    public int getLoginIndex() {
        return loginIndex;
    }

    public List<String> getRegexpArray() {
        return regexpArray;
    }

    public Map<Integer, Object> getMapToResendToUser() {
        return mapToResendToUser;
    }

    public void setMapToResendToUser(Map<Integer, Object> mapToResendToUser) {
        this.mapToResendToUser = mapToResendToUser;
    }

    /**
     * Emulates working with DB with some data already written in.
     * To do so write several records into model before actual program activities.
     * Does check for unique logins of users.
     *
     * @param emu
     * @param model
     */
    public void initializeDB(DBEmu emu, Notebook model) {
        emu.initializeRecord1();
        emu.initializeRecord2();
        try {
            model.checkForDuplicateAndAddNoteToNotebook(emu.getRecordEmulation1());
            model.checkForDuplicateAndAddNoteToNotebook(emu.getRecordEmulation2());
        }
        catch (NotUniqueLoginException e) {}
    }

    /**
     * Applies regexp to user input to check its validity.
     *
     * @param input
     * @param regexp
     * @return
     */
    public boolean matchInputWithRegexp(String input, String regexp) {
        if (Pattern.matches(regexp, input)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Initialize Resource Bundles with respective locale.
     *
     * @param locale
     */
    public void bundleInitialization(Locale locale) {
        regexpBundle = ResourceBundle.getBundle(REGEXP_BUNDLE_NAME, locale);
        groupsBundle = ResourceBundle.getBundle(GROUPS_BUNDLE_NAME, locale);
    }

    /**
     * Sends checked data to model to be put into DB.
     * @param data
     */
    public boolean sendReadyDataToModel(Map<Integer, Object> data) {
        try {
            model.checkForDuplicateAndAddNoteToNotebook(data);
            return true;
        }
        catch (NotUniqueLoginException e) {
            setMapToResendToUser(e.getMap());
            return false;
        }
    }

    /**
     * Sends preliminary checked data from web to 2nd check and if it's correct
     * sends data to try to be stored into DB.
     * @param map
     * @return
     */
    public boolean onRecievingDataFromWeb(Map<Integer, Object> map) {
        if (checkDataFromWebForCorrectness(map)) {
            return sendReadyDataToModel(map);
        }
        else {
            return false;
        }
    }

    /**
     * Matches data from web with regexps. For some types of data that is not String,
     * performs other checks.
     * @param mapToCheck
     * @return
     */
    public boolean checkDataFromWebForCorrectness(Map<Integer, Object> mapToCheck) {
        boolean check = true;
        bundleInitialization(new Locale("en"));
        regexpArray = Regexp.returnRegexpStream().collect(Collectors.toList());
        for (Map.Entry<Integer, Object> x : mapToCheck.entrySet()) {
            if (x.getKey().equals(12)) {
                check &= (x.getValue() instanceof Map);
            }
            else if (x.getKey() == 13) {
                check &= true;
            }
            else if (x.getKey() == 14 || x.getKey() == 15) {
                check &= (x.getValue() instanceof  GregorianCalendar);
            }
            else {
                check &= matchInputWithRegexp((String)x.getValue(), getRegexpBundle().getString(regexpArray.get(x.getKey())));
            }
        }
        return check;
    }
}
