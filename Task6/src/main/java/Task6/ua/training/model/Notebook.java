package Task6.ua.training.model;

import Task6.ua.training.controller.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Model to store data.
 * Data is stored in List of Maps.
 * Each map has the following structure (key : value):
 *
 * 0 : String subscriberSurname;
 * 1 : String subscriberName;
 * 2 : String subscriberPatronymic;
 * 3 : String generatedInitials;
 * 4 : String login;
 * 5 : String comment;
 * 6 : List<String> groups;
 * 7 : String homeTelNumber;
 * 8 : String mobileTelNumber;
 * 9 : String mobileTelNumber2;
 * 10 : String email;
 * 11 : String skype;
 * 12 : HashMap<String, String> addressSeparated;
 * 13 : String addressFull;
 * 14 : GregorianCalendar creationDate;
 * 15 : GregorianCalendar lastChangeDate;
 */
public class Notebook implements Model{

    private List<Map<Integer, Object>> notebook = new ArrayList<>();
    private Controller controller;
    private ResourceBundle modelBundle = ResourceBundle.getBundle(MODEL_RESOURCE_BUNDLE_NAME);
    private final static String MODEL_RESOURCE_BUNDLE_NAME = "model/messages";
    private final static String NOT_UNIQUE_LOGIN_EXCEPTION = "notUniqueLogin.exception";

    public String getData(String request) {
        return null;
    }

    public void setData(String data) {
    }

    public List<Map<Integer, Object>> getNotebook() {
        return notebook;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Check if login in new record have been already used by someone.
     * If no, stores provided map into general List.
     * If yes, throws exception.
     *
     * @param map
     */
    public void checkForDuplicateAndAddNoteToNotebook(Map map) throws NotUniqueLoginException{
        if (!notebook.isEmpty()) {
            for (int i = 0; i < notebook.size(); i++) {
                if (notebook.get(i).get(controller.getLoginIndex()).equals(map.get(controller.getLoginIndex()))) {
                    throw new NotUniqueLoginException(modelBundle.getString(NOT_UNIQUE_LOGIN_EXCEPTION), map);
                }
            }
            notebook.add(map);
        }
        else {
            notebook.add(map);
        }
    }

    /**
     * Provides required record if respective index is used.
     *
     * @param index
     * @return
     */
    public Map<Integer, Object> getNote(int index) {
        return notebook.get(index);
    }

    /**
     * Provides information about index of last record in general List.
     *
     * @return
     */
    public int getLastNoteIndex(){
        return notebook.size() - 1;
    }
}
