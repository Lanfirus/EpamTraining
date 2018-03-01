package lesson2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class View {

    private Controller controller;
    private boolean stopReading = false;
    private final static String EXCEPTION_HANDLER_MESSAGE = "Something is wrong. Exception happens";


    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setStopReading(boolean stopReading) {
        this.stopReading = stopReading;
    }

    /**
     * Reads user input from the console. Send it to controller to further
     * pass it to Model for processing.
     */
    public void readingFromConsole(){

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (!stopReading) {
                controller.onNewReadString(reader.readLine());
            }
        }
        catch (IOException e) {
            System.out.println(EXCEPTION_HANDLER_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Prints message to the console
     * @param message
     */
    public void printMessageToConsole(String message) {
        System.out.println(message);
    }
}
