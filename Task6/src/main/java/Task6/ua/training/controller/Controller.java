package Task6.ua.training.controller;

import Task6.ua.training.model.NotUniqueLoginException;
import Task6.ua.training.model.Notebook;
import Task6.ua.training.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Controller {

    private View view;
    private Notebook model;

    private ResourceBundle bundle = ResourceBundle.getBundle(CONTROLLER_BUNDLE_NAME);
    private final static String CONTROLLER_BUNDLE_NAME = "controller/messages";
    private ResourceBundle regexpBundle;
    private final static String REGEXP_BUNDLE_NAME = "regexp";
    private static ResourceBundle groupsBundle;
    private final static String GROUPS_BUNDLE_NAME = "groups";

    private boolean inputCorrect;
    private boolean stopReading = false;

    private Map<Integer, Object> temporaryRecord = new TreeMap<>();
    private List<String> inputMessageArray;
    private List<String> regexpArray;
    private List<String> temporaryGroupList = new ArrayList<>();

    private int counter = 0;


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

    public Controller(View view, Notebook model) {
        this.view = view;
        this.model = model;
    }

    public boolean isInputCorrect() {
        return inputCorrect;
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

    /**
     * Main method of the program. Interact with the user to get all data and later to put it into model.
     * Supports 3 languages - English, Russian and Ukrainian. User is asked to choose one at the beginning
     * of the program.
     * All correct data is stored in temporary TreeMap object. It's content is in line with model's record structure.
     * See description of Notebook class for more details.
     *
     * Subsriber's groups are stored in List<String> object.
     * Full address is stored in HashMap<Integer, String>.
     * Creation date and date of last change are stored in GregorianCalendar objects which allow to flexibly get
     * required time information.
     *
     * To see the result of this program's work add the following lines to the very end of this method:
     * Map<Integer, Object> tmp = model.getNote(0);
     * for (Map.Entry<Integer, Object> x : tmp.entrySet()) {
     * System.out.println("key = " + x.getKey() + " value = " + x.getValue());
     * }
     */
    public void startProgram() {

        view.printMessage(bundle.getString(Messages.LANGUAGE_CHOICE_MESSAGE));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (!setLanguage(reader)) {}

            regexpArray = Regexp.returnRegexpStream().collect(Collectors.toList());
            inputMessageArray = Messages.returnInputMessagesStream().collect(Collectors.toList());
            String inputData;
            while (!stopReading) {
                view.printMessage(String.format(bundle.getString(inputMessageArray.get(counter)), returnGroupsNames()));
                if (counter == modifiedNameIndex) {
                    temporaryRecord.put(counter, createSurnameAndNameRecord());
                    counter++;
                }
                else if (counter == groupsIndex) {
                    matchGroupNames(reader.readLine());
                }
                else if (counter == fullAddressInitialIndex) {
                    String tempFullAddress = createFullAddressString();
                    temporaryRecord.put(addressIndexIndex, createAddressMap());
                    temporaryRecord.put(finalFullAddressIndex, tempFullAddress);
                    temporaryRecord.put(creationDateIndex, new GregorianCalendar());
                    temporaryRecord.put(lastChangeDateIndex, new GregorianCalendar());
                    temporaryRecord.remove(16);
                    stopReading = true;
                }
                else if (matchInputWithRegexp(inputData = reader.readLine(), regexpBundle.getString(regexpArray.get(counter)))) {
                    temporaryRecord.put(counter, inputData);
                    counter++;
                }
            }
        }
        catch (IOException e) {
            view.printMessage(bundle.getString(Messages.EXCEPTION_HANDLER_MESSAGE));
            e.printStackTrace();
        }
        sendReadyDataToModel(temporaryRecord);
        listAllRecordsFromNotebook();
    }



    /**
     * Sets language program will use to communicate with user.
     * Define locale for general messages, regexp and user groups.
     * Supports English, Russian and Ukrainian languages.
     * @param reader
     */
    public boolean setLanguage(BufferedReader reader) throws IOException {
        int languageChoice = inputParsedToInteger(reader.readLine());
        if (inputCorrect) {
            if (languageChoice == 1) {
                bundleInitialization(new Locale("en"));
                view.printMessage(bundle.getString(Messages.LANGUAGE_SET_MESSAGE));
                return true;
            }
            else if (languageChoice == 2) {
                bundleInitialization(new Locale("ru"));
                view.printMessage(bundle.getString(Messages.LANGUAGE_SET_MESSAGE));
                return true;
            }
            else if (languageChoice == 3) {
                bundleInitialization(new Locale("ua"));
                view.printMessage(bundle.getString(Messages.LANGUAGE_SET_MESSAGE));
                return true;
            }
            else {
                view.printMessage(String.format(bundle.getString(Messages.LANGUAGE_INCORRECT_INPUT), languageChoice));
                return false;
            }
        }
        return false;
    }

    /**
     * Checks input from the user for possibility to turn it into Integer.
     * Returns respective message if it's not a case.
     * Returns int value if it's possible.
     *
     * @param input
     */
    public int inputParsedToInteger(String input) {
        inputCorrect = true;
        int tempInt = 0;
        if (input != null) {
            try {
                tempInt = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                view.printMessage(String.format(bundle.getString(Messages.LANGUAGE_INCORRECT_INPUT), input));
                inputCorrect = false;
            }
        }
        else {
            view.printMessage(String.format(bundle.getString(Messages.LANGUAGE_INCORRECT_INPUT), input));
        }
        return tempInt;
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
            view.printMessage(String.format(bundle.getString(Messages.INPUT_ERROR_MESSAGE), input));
            return false;
        }
    }

    /**
     * Initialize Resource Bundles with respective locale.
     *
     * @param locale
     */
    public void bundleInitialization(Locale locale) {
        bundle = ResourceBundle.getBundle(CONTROLLER_BUNDLE_NAME, locale);
        regexpBundle = ResourceBundle.getBundle(REGEXP_BUNDLE_NAME, locale);
        groupsBundle = ResourceBundle.getBundle(GROUPS_BUNDLE_NAME, locale);
    }

    /**
     * Transform Group names to String format to show user acceptable groups to choose from.
     *
     * @return
     */
    public String returnGroupsNames(){
        StringBuilder temp = new StringBuilder();
        temp.append(groupsBundle.getString(Groups.EMPLOYEES) + ", ");
        temp.append(groupsBundle.getString(Groups.MANAGEMENT) + ", ");
        temp.append(groupsBundle.getString(Groups.CLIENTS) + ", ");
        temp.append(groupsBundle.getString(Groups.SUBCONTRACTORS) + ", ");
        temp.append(groupsBundle.getString(Groups.OUTSORCE));
        return temp.toString();
    }

    /**
     * Process user inputs to check, control and use correct user groups.
     * Prevents adding duplicated groups into main record.
     * Checks validity of input group with allowed ones.
     *
     * @param input
     */
    public void matchGroupNames(String input) {
        String result = Groups.returnGroupsStream().filter(x -> x.equals(input)).collect(Collectors.joining());
        if (!result.equals("")) {
            if (!temporaryGroupList.contains(result)){
                temporaryGroupList.add(result);
            }
            else {
                view.printMessage(bundle.getString(Messages.GROUP_NAME_ERROR));
            }
        }
        else if (input.equals("0")) {
            temporaryRecord.put(counter, temporaryGroupList);
            counter++;
        }
        else {
            view.printMessage(String.format(bundle.getString(Messages.INPUT_ERROR_MESSAGE), input));
        }
    }

    /**
     * Creates modified personal information record - Surname + "" + first letter from name + "."
     *
     * @return
     */
    public String createSurnameAndNameRecord() {
        StringBuilder temp = new StringBuilder();
        temp.append(temporaryRecord.get(surnameIndex) + " ");
        temp.append(temporaryRecord.get(nameIndex).toString().charAt(0) + ".");
        return temp.toString();
    }

    /**
     * Creates String with full address from user input data.
     *
     * @return
     */
    public String createFullAddressString() {
        StringBuilder temp = new StringBuilder();
        for (int index = addressIndexIndex; index <= addressApartmentIndex; index++) {
            temp.append(temporaryRecord.get(index) + "; ");
        }
        return temp.toString().trim();
    }

    /**
     * Creates HashMap to store address data.
     *
     * @return
     */
    public Map<String, String> createAddressMap() {
        Map<String, String> temp = new HashMap<>();
        for (int index = addressIndexIndex; index <= addressApartmentIndex; index++) {
            int beginIndex = inputMessageArray.get(index).lastIndexOf(".");
            temp.put(inputMessageArray.get(index).substring(beginIndex + 1), temporaryRecord.get(index).toString());
        }
        return temp;
    }

    /**
     * Sends checked data to model to be put into DB.
     * @param data
     */
    public void sendReadyDataToModel(Map<Integer, Object> data) {
        try {
            model.checkForDuplicateAndAddNoteToNotebook(data);
        }
        catch (NotUniqueLoginException e) {
            view.printMessage(e.getMessage());
            rewriteLoginDataFromUser(e.getMap());
        }
    }

    /**
     * Asks user to put another login if previous have been already used by someone and stored into DB.
     * Then returns corrected data to model to put into DB.
     *
     * @param map
     */
    public void rewriteLoginDataFromUser(Map<Integer, Object> map){
        view.printMessage(bundle.getString(Messages.INPUT_LOGIN));
        regexpArray = Regexp.returnRegexpStream().collect(Collectors.toList());
        regexpBundle = ResourceBundle.getBundle(REGEXP_BUNDLE_NAME);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            stopReading = false;
            String inputData;
            while(!stopReading) {
                if (matchInputWithRegexp(inputData = reader.readLine(), regexpBundle.getString(regexpArray.get(loginIndex)))) {
                    map.put(loginIndex, inputData);
                    stopReading = true;

                }
            }
            sendReadyDataToModel(map);
        }
        catch (IOException e) {
            view.printMessage(bundle.getString(Messages.EXCEPTION_HANDLER_MESSAGE));
            e.printStackTrace();
        }
    }

    /**
     * Display on console all records in notebook
     */
    public void listAllRecordsFromNotebook() {
        for (Map<Integer, Object> x : model.getNotebook()) {
            view.printMessage(x.toString());
        }
    }
}
