package Lesson3.ua.training;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class Model {

    private Controller controller;
    private int numberToBeGuessed;
    private int numberFromUser;
    private int numberOfSteps = 1;
    private int lowBound = 0;
    private int highBound = MAX_VALUE_OF_NUMBER + 1;

    private List<Integer> allUserNumbers = new ArrayList<>();

    private ResourceBundle modelBundle;
    private Locale modelLocale;

    private boolean setMode = false;
    private boolean setLowBound = false;
    private boolean setHighBound = false;

    private final static int MAX_VALUE_OF_NUMBER = 99;

    private final static String RESOURCE_BUNDLE_NAME = "modelMessages";

    private final static String INCORRECT_INPUT = "input.incorrect";
    private final static String INCORRECT_INPUT_OVERFLOW = "input.incorrect.overflow";
    private final static String CONGRATULATION = "output.congratulation";
    private final static String PREVIOUS_ATTEMPTS_MESSAGE = "output.previousAttempts";
    private final static String NUMBER_OF_STEPS_MESSAGE = "output.numberOfSteps";
    private final static String LESS = "output.less";
    private final static String MORE = "output.more";
    private final static String NEW_BOUNDARIES_MESSAGES = "output.boundaries";
    private final static String TRY_AGAIN = "output.tryAgain";
    private final static String INITIAL_RULES_MESSAGE = "initial.rules";
    private final static String INITIAL_RULES_SET_MESSAGE = "initial.rules.set";
    private final static String INITIAL_CUSTOM_RULES_MESSAGE = "initial.rules.custom";
    private final static String INITIAL_CUSTOM_RULES_MESSAGE_2 = "initial.rules.custom.2";
    private final static String INITIAL_RULES_ERROR = "initial.rules.error";
    private final static String INITIAL_CUSTOM_RULES_ERROR_1 = "initial.rules.custom.error.1";
    private final static String INITIAL_CUSTOM_RULES_ERROR_2 = "initial.rules.custom.error.2";

    public boolean isSetMode() {
        return setMode;
    }

    public boolean isSetLowBound() {
        return setLowBound;
    }

    public boolean isSetHighBound() {
        return setHighBound;
    }

    public String getInitialRulesSetMessage() {
        return modelBundle.getString(INITIAL_RULES_SET_MESSAGE);
    }

    public int getNumberFromUser() {
        return numberFromUser;
    }

    public Locale getModelLocale() {
        return modelLocale;
    }

    public int getNumberToBeGuessed() {
        return numberToBeGuessed;
    }

    public int getHighBound() {
        return highBound;
    }

    public int getLowBound() {
        return lowBound;
    }

    /**
     * Sets the same language as set in Controller. It could be either English or Russian.
     * @param modelLocale
     */
    public void setLanguage(Locale modelLocale) {
        this.modelLocale = modelLocale;
        modelBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, modelLocale);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setNumberFromUser(int numberFromUser) {
        this.numberFromUser = numberFromUser;
    }

    /**
     * Sets Game mode.
     * Mode could be either default one - guessed number will be between 0 and 100 exclusively - or
     * custom one where user can set his own boundaries for game
     */
    public void setGameMode() {
        if (numberFromUser == 1) {
            numberToBeGuessed = rand();
            setMode = true;
            setLowBound = true;
            setHighBound = true;
            controller.onNewMessageToUser(String.format(modelBundle.getString(INITIAL_RULES_MESSAGE), lowBound, MAX_VALUE_OF_NUMBER + 1));
        }
        else
            if (numberFromUser == 2) {
                setMode = true;
                controller.onNewMessageToUser(modelBundle.getString(INITIAL_CUSTOM_RULES_MESSAGE));
            }
            else {
                controller.onNewMessageToUser((modelBundle.getString(INITIAL_RULES_ERROR) + numberFromUser));
            }
    }

    /**
     * Sets value for low bound if custom mode have been selected
     */
    public void setLowBound() {
            lowBound = numberFromUser;
            setLowBound = true;
            controller.onNewMessageToUser(String.format(modelBundle.getString(INITIAL_CUSTOM_RULES_MESSAGE_2), lowBound + 2));
    }

    /**
     * Sets value for high bound if custom mode have been selected.
     * There are several checks for input correctness.
     * In case of incorrect input user will get respective message.
     */
    public void setHighBound() {
            if (numberFromUser < lowBound) {
                controller.onNewMessageToUser(String.format(modelBundle.getString(INITIAL_CUSTOM_RULES_ERROR_1), lowBound));
            }
            else
                if (lowBound == Integer.MIN_VALUE && numberFromUser == Integer.MAX_VALUE) {
                    controller.onNewMessageToUser(String.format(modelBundle.getString(INCORRECT_INPUT_OVERFLOW),
                                                    numberFromUser, lowBound, lowBound + 2, Integer.MAX_VALUE - 1));
                }
            else
                if (Math.abs(numberFromUser - lowBound) < 2) {
                    controller.onNewMessageToUser(String.format(modelBundle.getString(INITIAL_CUSTOM_RULES_ERROR_2), numberFromUser));
                }
                else {
                    highBound = numberFromUser;
                    setHighBound = true;
                    controller.onNewMessageToUser(String.format(modelBundle.getString(INITIAL_RULES_MESSAGE), lowBound, highBound));
                    numberToBeGuessed = rand(lowBound, highBound);
                    }
    }



    /**
     * Generates pseudorandom number between 0 and MAX_VALUE_OF_NUMBER values exclusively.
     *
     * @return int
     */
    private int rand () {
        return ThreadLocalRandom.current().nextInt(MAX_VALUE_OF_NUMBER) +1;
    }

    /**
     * Generates pseudorandom number between min and max values exclusively.
     *
     * @return int
     */
    private int rand(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min +1, max);
    }

    /**
     * Matches input from the user and Guessed number. Returns respective messages about
     * result of the matching and some statistic.
     * @param number
     */
    public void matchNumberFromUser(int number) {
        if (number == numberToBeGuessed) {
            controller.stopProgram();
            controller.onNewMessageToUser(modelBundle.getString(CONGRATULATION) + numberToBeGuessed);
            controller.onNewMessageToUser(modelBundle.getString(PREVIOUS_ATTEMPTS_MESSAGE) + printAttempts());
            controller.onNewMessageToUser(modelBundle.getString(NUMBER_OF_STEPS_MESSAGE) + numberOfSteps);
        }
        else
            if (numberToBeGuessed < number) {
                highBound = number;
                controller.onNewMessageToUser(modelBundle.getString(LESS));
                allUserNumbers.add(number);
                controller.onNewMessageToUser(makeMessageToUser());
                numberOfSteps++;
            }
            else
                if (numberToBeGuessed > number) {
                    lowBound = number;
                    controller.onNewMessageToUser(modelBundle.getString(MORE));
                    allUserNumbers.add(number);
                    controller.onNewMessageToUser(makeMessageToUser());
                    numberOfSteps++;
                }
    }

    /**
     * Allows to print List content in readable format
     * @return
     */
    private String printAttempts(){
        StringBuilder attempts = new StringBuilder();
        allUserNumbers.stream().forEach(x -> attempts.append(x + " "));
        return attempts.toString();
    }

    /**
     * Checks user input for being inside current boundaries and then match it
     * with guessed number using another method.
     */
    public void checkAndMatchNumberFromUser() {

        if (numberFromUser <= lowBound || numberFromUser >= highBound) {
            controller.onNewMessageToUser(String.format(modelBundle.getString(INCORRECT_INPUT), numberFromUser, lowBound + 1, highBound - 1));
        } else {
            matchNumberFromUser(numberFromUser);
        }
    }

    /**
     * Builds statistical message to be send to user
     * @return
     */
    public String makeMessageToUser() {
        StringBuilder temp = new StringBuilder();
        temp.append((modelBundle.getString(NUMBER_OF_STEPS_MESSAGE) + numberOfSteps) + "\n");
        temp.append((modelBundle.getString(PREVIOUS_ATTEMPTS_MESSAGE) + printAttempts()) + "\n");
        temp.append(String.format(modelBundle.getString(NEW_BOUNDARIES_MESSAGES), lowBound, highBound) + "\n");
        temp.append(modelBundle.getString(TRY_AGAIN));

        return temp.toString();
    }

}
