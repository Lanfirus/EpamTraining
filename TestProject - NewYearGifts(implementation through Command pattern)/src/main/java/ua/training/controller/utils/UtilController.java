package ua.training.controller.utils;



import ua.training.constants.Constants;
import ua.training.controller.exceptions.InappropriateBulkOrderException;
import ua.training.model.dao.impl.UserDAO;
import ua.training.model.entity.User;

import ua.training.model.exception.NotUniqueLoginException;
import ua.training.model.utils.SQLInteraction;


import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author Dudchenko Andrei
 */
public class UtilController {

    private User user;
    private SQLInteraction sqlInteraction;


    private ResourceBundle regexpBundle;
    private final static String REGEXP_BUNDLE_NAME = "regexp";


    public UtilController(SQLInteraction sqlInteraction) {
        this.sqlInteraction = sqlInteraction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return Pattern.matches(regexp, input);
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
     * Sends checked data to user to be put into DB.
     * @param userData
     */
    public boolean sendReadyRegistrationDataToDB(Map<String, String> userData) throws NotUniqueLoginException {
        UserDAO dao = new UserDAO();
        dao.create(userData);
        return true;
    }

    public boolean sendReadyUpdateDataToDB(Map<String, String> userData) throws NotUniqueLoginException {
        UserDAO dao = new UserDAO();
        dao.update(new User(userData));
        return true;
    }

    /**
     * Sends preliminary checked data from web to 2nd check and if it's correct
     * sends data to try to be stored into DB.
     * @param orderData
     * @return
     */
    public void onRecievingOrderDataFromWeb(Map<String, String> orderData) throws SQLException, InappropriateBulkOrderException {
        if(checkOrderDataForCorrectness(orderData)) {
            sqlInteraction.insertOrderRecord(orderData);
        }
        else{
            throw new RuntimeException("Something happened during order confirmation data check");
        }
    }

    public boolean onRecievingUserRegistrationDataFromWeb(User user) throws NotUniqueLoginException{
        Map<String, String> userData = user.getUserData();
        if (checkDataFromWebForCorrectness(userData)) {
            return sendReadyRegistrationDataToDB(userData);
        }
        else {
            throw new RuntimeException("Something happened during userRegistrationData registration data check");
        }
    }

    public boolean onRecievingUserUpdateDataFromWeb(User user) throws NotUniqueLoginException{
        Map<String, String> userData = user.getUserData();
        if (checkDataFromWebForCorrectness(userData)) {
            return sendReadyUpdateDataToDB(userData);
        }
        else {
            throw new RuntimeException("Something happened during userRegistrationData registration data check");
        }
    }

    /**
     * Matches data from web with regexps. For some types of data that is not String,
     * performs other checks.
     * @param userData
     * @return
     */
    public boolean checkDataFromWebForCorrectness(Map<String, String> userData) {
        boolean check = true;
        bundleInitialization(new Locale("en"));
        for (Map.Entry<String, String> field : userData.entrySet()){
            check &= matchInputWithRegexp(field.getValue(), regexpBundle.getString(field.getKey()));
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

    public String getFullNameByLoginPassword(String login, String password) throws SQLException{
        String temporaryFullNameValue = sqlInteraction.executeGetFullNameQuery(login, password);
        if (temporaryFullNameValue == null){
            throw new NullPointerException("This user doesn't have name or surname");
        }
        return temporaryFullNameValue;
    }

    private boolean checkOrderDataForCorrectness(Map<String, String> orderData) throws InappropriateBulkOrderException{
        String regexpNormal = "^[0-9]{1,4}$";
        String regexpBulkOrder = "^[0-9]{5,}$";
        boolean check = true;
        for (Map.Entry<String, String> orderField : orderData.entrySet()) {
            if (!orderField.getKey().equals("login") && !matchInputWithRegexp(orderField.getValue(), regexpNormal)) {
                System.out.println(orderField.getValue());
                if(!matchInputWithRegexp(orderField.getValue(), regexpBulkOrder)) {
                    System.out.println(orderField.getValue());
                    check &= false;
                }
                else {
                    throw new InappropriateBulkOrderException();
                }
            }
        }
        return check;
    }

    public void setUserEnteredDataBackToForm(HttpServletRequest request, User user){
        request.setAttribute(Constants.NAME, user.getName());
        request.setAttribute(Constants.SURNAME, user.getSurame());
        request.setAttribute(Constants.PATRONYMIC, user.getPatronymic() == null ? "" : user.getPatronymic());
        request.setAttribute(Constants.LOGIN, user.getLogin());
//        request.setAttribute("password", "");
        request.setAttribute(Constants.COMMENT, user.getComment() == null ? "" : user.getComment());
        request.setAttribute(Constants.HOME_PHONE_NUMBER, user.getHomePhoneNumber() == null ? "" : user.getHomePhoneNumber());
        request.setAttribute(Constants.MOBILE_PHONE_NUMBER, user.getMobilePhoneNumber());
        request.setAttribute(Constants.EMAIL, user.getEmail());
    }

    public Map<String, String> setUserData(HttpServletRequest request){
        Map<String, String> preparedUserData = new LinkedHashMap<>();
        preparedUserData.put(Constants.NAME, request.getParameter("name"));
        preparedUserData.put(Constants.SURNAME, request.getParameter("surname"));
        preparedUserData.put(Constants.PATRONYMIC, request.getParameter("patronymic"));
        preparedUserData.put(Constants.LOGIN, request.getParameter("login"));
        preparedUserData.put(Constants.PASSWORD, request.getParameter("password"));
        preparedUserData.put(Constants.COMMENT, request.getParameter("comment"));
        preparedUserData.put(Constants.HOME_PHONE_NUMBER, request.getParameter("homephonenumber"));
        preparedUserData.put(Constants.MOBILE_PHONE_NUMBER, request.getParameter("mobilephonenumber"));
        preparedUserData.put(Constants.EMAIL, request.getParameter("email"));
        return preparedUserData;
    }

    public User getUserFromDBByLogin(String login){
        UserDAO dao = new UserDAO();
        User user = new User(dao.findByLogin(login));
        return user;
    }

    public boolean deleteUser(String login, String password){
        UserDAO dao = new UserDAO();
        dao.delete(login, password);
        return true;
    }

}
