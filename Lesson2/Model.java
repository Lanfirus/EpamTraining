package lesson2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Model {

    private Controller controller;
    private int numberToBeGuessed;
    private int numberFromUser;
    private int numberOfSteps = 1;
    private int lowBound = 0;
    private int highBound = MAX_VALUE_OF_NUMBER;
    private List<Integer> allUserNumbers = new ArrayList<>();

    private final static int MAX_VALUE_OF_NUMBER = 100;
    private final static String INCORRECT_INPUT = "You have used incorrect input: %s";
    private final static String INITIAL_RULES_MESSAGE = String.format("Welcome! Try to guess my number. It's a number between 0 and %d", MAX_VALUE_OF_NUMBER);
    private final static String CONGRATULATION = "Congratulations! Your guess is absolutely right. Guessed number was: ";
    private final static String PREVIOUS_ATTEMPTS_MESSAGE = "Your previous attempts are as follows: ";
    private final static String NUMBER_OF_STEPS_MESSAGE = "You have used the following number of steps: ";
    private final static String LESS = "Guessed number is lesser than your";
    private final static String MORE = "Guessed number is bigger than your";
    private final static String NEW_BOUNDARIES_MESSAGES = "Guessed number is somewhere between ";
    private final static String TRY_AGAIN = "Please, try again";


    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setNumberFromUser(int numberFromUser) {
        this.numberFromUser = numberFromUser;
    }

    public String getIncorrectInput() {
        return INCORRECT_INPUT;
    }

    /**
     * Initializes Model by setting random number as a number to be guessed by user.
     */
    public void initiateProgram() {
        numberToBeGuessed = rand();
        controller.onNewMessageToUser(INITIAL_RULES_MESSAGE);
    }

    /**
     * Generates pseudorandom number between 0 and MAX_VALUE_OF_NUMBER values
     *
     * @return int
     */
    private int rand () {
        return ThreadLocalRandom.current().nextInt(MAX_VALUE_OF_NUMBER + 1);
    }

    /**
     * Generates pseudorandom number between min and max values
     *
     * @return int
     */
    private int rand(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * Matches input from the user and Guessed number. Returns respective messages about
     * result of the matching.
     * @param number
     */
    private void matchNumberFromUser(int number) {
        if (number == numberToBeGuessed) {
            controller.onNewMessageToUser(CONGRATULATION + numberToBeGuessed);
            controller.onNewMessageToUser(PREVIOUS_ATTEMPTS_MESSAGE + printAttempts());
            controller.onNewMessageToUser(NUMBER_OF_STEPS_MESSAGE + numberOfSteps);
            controller.stopProgram();
        }
        else
            if (numberToBeGuessed < number) {
                controller.onNewMessageToUser(LESS);
                controller.onNewMessageToUser(NUMBER_OF_STEPS_MESSAGE + numberOfSteps);
                numberOfSteps++;
                allUserNumbers.add(number);
                controller.onNewMessageToUser(PREVIOUS_ATTEMPTS_MESSAGE + printAttempts());
                highBound = number - 1;
                controller.onNewMessageToUser(NEW_BOUNDARIES_MESSAGES + lowBound + " and " + highBound);
                controller.onNewMessageToUser(TRY_AGAIN);
            }
            else
                if (numberToBeGuessed > number) {
                    controller.onNewMessageToUser(MORE);
                    controller.onNewMessageToUser(NUMBER_OF_STEPS_MESSAGE + numberOfSteps);
                    numberOfSteps++;
                    allUserNumbers.add(number);
                    controller.onNewMessageToUser(PREVIOUS_ATTEMPTS_MESSAGE + printAttempts());
                    lowBound = number + 1;
                    controller.onNewMessageToUser(NEW_BOUNDARIES_MESSAGES + lowBound + " and " + highBound);
                    controller.onNewMessageToUser(TRY_AGAIN);
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
    public void CheckAndMatchNumberFromUser() {
        if (numberFromUser < lowBound || numberFromUser > highBound) {
            controller.onNewMessageToUser(String.format(INCORRECT_INPUT, numberFromUser));
        } else {
            matchNumberFromUser(numberFromUser);
        }
    }

}
