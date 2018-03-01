package lesson2;

public class Controller {

    private View view;
    private Model model;

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
     * Starts program by pushing Model to initialize Guessed number and push View to start
     * reading user inputs from the console
     */
    public void startProgram() {
        model.initiateProgram();
        view.readingFromConsole();
    }

    /**
     * Pushes message to user when there is one from the program
     * @param message
     */
    public void onNewMessageToUser(String message) {
        view.printMessageToConsole(message);
    }

    /**
     * Pushes input from the user to the Model
     * @param readString
     */
    public void onNewReadString(String readString) {
        model.guessTry(readString);
    }

    /**
     * Stops program by initializing stopper for View
     */
    public void stopProgram() {
        view.setStopReading(true);
    }

}
