package lesson2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    private View view;
    private Model model;
    private boolean stopReading = false;
    private final static String EXCEPTION_HANDLER_MESSAGE = "Something is wrong. Exception happens";
    private boolean isInputCorrect = true;

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
     * reading user inputs from the console. Then checks user input for
     * initial correctness and sends correct inputs to model for further processing
     */
    public void startProgram() {
        model.initiateProgram();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (!stopReading) {
                inputCheck(reader.readLine());
                if (isInputCorrect) {
                    model.CheckAndMatchNumberFromUser();
                }
            }
        }
        catch (IOException e) {
            System.out.println(EXCEPTION_HANDLER_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Checks input from the user for initial correctness. Returns respective message if it's
     * not a case.
     * @param input
     */
    public void inputCheck(String input) {
        isInputCorrect = true;
        if (input == null || input.contains(" ")) {
            view.printMessageToConsole(String.format(model.getIncorrectInput(), input));
        }
        else {
            try {
                model.setNumberFromUser(Integer.parseInt(input));
            } catch (NumberFormatException e) {
                view.printMessageToConsole(String.format(model.getIncorrectInput(), input));
                isInputCorrect = false;
            }
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
