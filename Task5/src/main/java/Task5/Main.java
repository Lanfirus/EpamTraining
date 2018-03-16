package Task5;

import Task5.controller.Controller;
import Task5.model.ModelEllipse;
import Task5.view.ViewConsole;

public class Main {

    public static void main(String[] args) {
        ViewConsole view = new ViewConsole();
        ModelEllipse model = new ModelEllipse();
        Controller controller = new Controller(view, model);
        view.setController(controller);
        model.setController(controller);

        controller.startProgram();
    }
}
