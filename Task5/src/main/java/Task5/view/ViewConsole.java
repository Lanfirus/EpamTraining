package Task5.view;


import Task5.controller.Controller;

public class ViewConsole extends View{

    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Prints message on console
     *
     * @param message
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Print error messages on console
     *
     * @param message
     */
    public void printError(String message) {
        System.err.println(message);
    }
}
