package Lesson3.ua.training;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller {

    private View view;
    private Model model;

    private boolean stopReading = false;
    private boolean isLanguageSet = false;
    private boolean isInputCorrect = true;

    private Locale locale;
    private ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME);

    private final static String RESOURCE_BUNDLE_NAME = "controllerMessages";

    private final static String EXCEPTION_HANDLER_MESSAGE = "exception.handler";
    private final static String LANGUAGE_CHOICE_MESSAGE = "language.choice";
    private final static String LANGUAGE_CHOICE_ERROR_MESSAGE = "language.choice.error";
    private final static String LANGUAGE_SET_MESSAGE = "language.set";
    private final static String INCORRECT_INPUT = "input.incorrect";

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public Locale getLocale() {
        return locale;
    }

    public boolean isStopReading() {
        return stopReading;
    }

    /**
     * Starts program with setting required language. Then proceeds with setting required game mode.
     * When all settings will be done reads user inputs from the console and pass them to model for processing.
     */
    public void startProgram() {

        view.printMessage(bundle.getString(LANGUAGE_CHOICE_MESSAGE));
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (!isLanguageSet) {
                setLanguage(reader.readLine());
            }
            model.setLanguage(locale);
            view.printMessage(model.getInitialRulesSetMessage());

            while (!model.isSetMode()) {
                inputCheck(reader.readLine());
                if (isInputCorrect) {
                    model.setGameMode();
                }
            }

            while (!model.isSetLowBound()) {
                inputCheck(reader.readLine());
                if (isInputCorrect) {
                    model.setLowBound();
                }
            }

            while (!model.isSetHighBound()) {
                inputCheck(reader.readLine());
                if (isInputCorrect) {
                    model.setHighBound();
                }
            }

            while (!stopReading) {
                inputCheck(reader.readLine());
                if (isInputCorrect) {
                        model.checkAndMatchNumberFromUser();
                }
            }
        }
        catch (IOException e) {
            view.printMessage(bundle.getString(EXCEPTION_HANDLER_MESSAGE));
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
        if (input != null) {
            try {
                model.setNumberFromUser(Integer.parseInt(input));
            } catch (NumberFormatException e) {
                view.printMessage(String.format(bundle.getString(INCORRECT_INPUT), input));
                isInputCorrect = false;
            }
        }
        else {
            view.printMessage(String.format(bundle.getString(INCORRECT_INPUT), input));
        }
    }

    /**
     * Pushes message to user when there is one from the program
     * @param message
     */
    public void onNewMessageToUser(String message) {
        view.printMessage(message);
    }

    /**
     * Stops program by initializing stopper for View
     */
    public void stopProgram() {
        stopReading = true;
    }

    /**
     * Sets language game will use to communicate with user.
     * @param language
     */
    public void setLanguage(String language) {
        if (language.toLowerCase().equals("english") || language.toLowerCase().equals("russian")) {
            locale = new Locale(language.substring(0, 2).toLowerCase());
            bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, locale);
            view.printMessage(bundle.getString(LANGUAGE_SET_MESSAGE));
            isLanguageSet = true;
            }
        else {
            view.printMessage(bundle.getString(LANGUAGE_CHOICE_ERROR_MESSAGE) + language);
            }
    }
}
