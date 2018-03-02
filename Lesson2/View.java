package lesson2;

public class View {

    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Prints message to the console
     * @param message
     */
    public void printMessageToConsole(String message) {
        System.out.println(message);
    }
}
