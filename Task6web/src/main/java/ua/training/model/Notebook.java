package ua.training.model;

import ua.training.controller.ControllerWeb;

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
 *
 * @author Dudchenko Andrei
 */
public class Notebook implements Model{

    private List<Map<Integer, Object>> notebook = new ArrayList<>();
    private ControllerWeb controller;

    public String getData(String request) {
        return null;
    }

    public void setData(String data) {
    }

    public List<Map<Integer, Object>> getNotebook() {
        return notebook;
    }

    public void setController(ControllerWeb controller) {
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
                    throw new NotUniqueLoginException("Not unique login!", map);
                }
            }
        }
            notebook.add(map);
    }
}
