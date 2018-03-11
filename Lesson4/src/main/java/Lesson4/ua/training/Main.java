package Lesson4.ua.training;

import Lesson4.ua.training.controller.Controller;
import Lesson4.ua.training.model.Notebook;
import Lesson4.ua.training.view.ViewConsole;

public class Main {

    public static void main(String[] args) {
        ViewConsole view = new ViewConsole();
        Notebook model = new Notebook();
        Controller controller = new Controller(view, model);
        view.setController(controller);
        model.setController(controller);

        controller.startProgram();
    }
}
