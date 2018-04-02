package ua.training.controller;

import ua.training.dao.NotUniqueLoginException;
import ua.training.dao.SQLInteraction;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

/**
 * @author Dudchenko Andrei
 */
public class ControllerWeb {

    private List<String> userRegistrationData = new CopyOnWriteArrayList<>();
    private SQLInteraction sqlInteraction;


    private ResourceBundle regexpBundle;
    private final static String REGEXP_BUNDLE_NAME = "regexp";


    public ControllerWeb(SQLInteraction sqlInteraction) {
        this.sqlInteraction = sqlInteraction;
    }

    public List<String> getUserRegistrationData() {
        return userRegistrationData;
    }

    public void setUserRegistrationData(List<String> userRegistrationData) {
        this.userRegistrationData = userRegistrationData;
    }

    public void initializeDB() throws SQLException{
        sqlInteraction.initializeDB();
    }

    public void deInitializeDB() throws SQLException{
        sqlInteraction.deInitializeDB();
    }


    /**
     * Applies regexp to userRegistrationData input to check its validity.
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
    }

    /**
     * Sends checked data to userRegistrationData to be put into DB.
     * @param userRegistrationData
     */
    public boolean sendReadyDataToDB(List<String> userRegistrationData) {
        try {
            sqlInteraction.insertRecord(userRegistrationData);
            return true;
        }
        catch (NotUniqueLoginException e) {
            System.out.println(e.getMessage());
            setUserRegistrationData(userRegistrationData);
            return false;
        }
    }

    /**
     * Sends preliminary checked data from web to 2nd check and if it's correct
     * sends data to try to be stored into DB.
     * @param userRegistrationData
     * @return
     */
    public boolean onRecievingDataFromWeb(List<String> userRegistrationData){
        if (checkDataFromWebForCorrectness(userRegistrationData)) {
            return sendReadyDataToDB(userRegistrationData);
        }
        else {
            throw new RuntimeException("Something happened during userRegistrationData registration data check");
        }
    }

    /**
     * Matches data from web with regexps. For some types of data that is not String,
     * performs other checks.
     * @param userRegistrationData
     * @return
     */
    public boolean checkDataFromWebForCorrectness(List<String> userRegistrationData) {
        boolean check = true;
        bundleInitialization(new Locale("en"));
        List<String> regexpArray = Regexp.returnRegexpArray();
        for (int userField = 0; userField < userRegistrationData.size(); userField++) {
                check &= matchInputWithRegexp(userRegistrationData.get(userField), regexpBundle.getString(regexpArray.get(userField)));
        }
        return check;
    }

    public boolean userIsExist(String login, String password) throws SQLException{
        return sqlInteraction.executeIsUserExistQuery(login, password);
    }

    public String getRoleByLoginPassword(String login, String password) throws SQLException{
        String temporaryRoleValue = sqlInteraction.executeGetRoleQuery(login, password);
        if (temporaryRoleValue == null){
            throw new NullPointerException("This user doesn't have any role");
        }
        return temporaryRoleValue;
    }
}
