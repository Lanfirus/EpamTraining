package lesson2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    private View view;
    private Model model;
    private boolean stopReading = false;
    private final static String EXCEPTION_HANDLER_MESSAGE = "Something is wrong. Exception happens";

    public void setView(View view) {
        this.view = view;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    /**
     * Starts program by pushing Model to initialize Guessed number and starts
     * reading user inputs from the console
     */
    public void startProgram() {
        model.initiateProgram();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (!stopReading) {
                model.guessTry(reader.readLine());
            }
        }
        catch (IOException e) {
            System.out.println(EXCEPTION_HANDLER_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Pushes message to user when there is one from the program
     * @param message
     */
    public void onNewMessageToUser(String message) {
        view.printMessageToConsole(message);
    }

    /**
     * Stops program by initializing stopper for View
     */
    public void stopProgram() {
        stopReading = true;
    }

}
