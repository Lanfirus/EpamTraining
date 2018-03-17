package Task6.ua.training;

import Task6.ua.training.controller.Controller;
import Task6.ua.training.model.DBEmu;
import Task6.ua.training.model.NotUniqueLoginException;
import Task6.ua.training.model.Notebook;
import Task6.ua.training.view.View;
import Task6.ua.training.view.ViewConsole;

import java.util.TreeMap;

public class Main {

    static void initializeDB(DBEmu emu, Notebook model, Controller controller) {
        emu.initializeRecord1();
        emu.initializeRecord2();
        try {
            model.checkForDuplicateAndAddNoteToNotebook(emu.getRecordEmulation1());
            model.checkForDuplicateAndAddNoteToNotebook(emu.getRecordEmulation2());
        }
        catch (NotUniqueLoginException e) {}
    }

    public static void main(String[] args) {
        ViewConsole view = new ViewConsole();
        Notebook model = new Notebook();
        Controller controller = new Controller(view, model);
        view.setController(controller);
        model.setController(controller);
        DBEmu emu = new DBEmu();

        initializeDB(emu, model, controller);
        controller.startProgram();
    }
}
