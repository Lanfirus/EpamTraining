package Lesson3.ua.training;

public class View {

    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Prints message to the console
     * @param message
     */
    public void printMessage(String message) {
        System.out.println(message);
    }
}
