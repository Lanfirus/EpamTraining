package Task5.controller;

import Task5.model.ModelEllipse;
import Task5.view.View;
import sun.plugin2.message.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    private View view;
    private ModelEllipse model;

    public void setView(View view) {
        this.view = view;
    }

    public void setModel(ModelEllipse model) {
        this.model = model;
    }

    public Controller() {
    }

    public Controller(View view, ModelEllipse model) {
        this.view = view;
        this.model = model;
    }


    /**
     * Main method of the program. Interact with the user to get all data and later to put it into model.
     * Supports 3 languages - English, Russian and Ukrainian. User is asked to choose one at the beginning
     * of the program.
     * All correct data is stored in temporary TreeMap object. It's content is in line with model's record structure.
     * See description of Notebook class for more details.
     *
     * Subsriber's groups are stored in List<String> object.
     * Full address is stored in HashMap<Integer, String>.
     * Creation date and date of last change are stored in GregorianCalendar objects which allow to flexibly get
     * required time information.
     *
     * To see the result of this program's work add the following lines to the very end of this method:
     * Map<Integer, Object> tmp = model.getNote(0);
     * for (Map.Entry<Integer, Object> x : tmp.entrySet()) {
     * System.out.println("key = " + x.getKey() + " value = " + x.getValue());
     * }
     */
    public void startProgram() {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            view.printMessage(Messages.INITIAL_MESSAGE.getTextOfMessage());
            checkAndSetValueForParameterRadiusA(reader);
            checkAndSetValueForParameterFocalDistance(reader);
            setRadiusB();
            view.printMessage(Messages.PARAMETERS_SET.getTextOfMessage());
            view.printMessage(Messages.VIEW_PARAMETER_CHOICE.getTextOfMessage());
            checkUserChoice(reader);
            view.printMessage(Messages.END_OF_PROGRAM_CHOICE.getTextOfMessage());
            checkUserChoiceToEndOrStartNewProgram(reader);
        }
        catch (IOException e) {
            view.printMessage(Messages.EXCEPTION_HANDLER.getTextOfMessage());
            e.printStackTrace();
        }
    }

    public void checkAndSetValueForParameterX(BufferedReader reader) throws IOException{
        view.printMessage(String.format(Messages.ENTER_PARAMETER_X.getTextOfMessage(), model.getRadiusA()));
        String inputValue = reader.readLine();
        if (checkInputParsedToDouble(inputValue) && checkParsedInputForCorrectness(inputValue)) {
            model.setX(Double.parseDouble(inputValue));
        }
        else {
            checkAndSetValueForParameterX(reader);
        }
    }

    public void checkAndSetValueForParameterY(BufferedReader reader) throws IOException{
        view.printMessage(String.format(Messages.ENTER_PARAMETER_Y.getTextOfMessage(), model.getRadiusB()));
        String inputValue = reader.readLine();
        if (checkInputParsedToDouble(inputValue) && checkParsedInputForCorrectness(inputValue)) {
            model.setY(Double.parseDouble(inputValue));
        }
        else {
            checkAndSetValueForParameterY(reader);
        }
    }

    public void checkAndSetValueForParameterRadiusA(BufferedReader reader) throws IOException{
        view.printMessage(String.format(Messages.ENTER_RADIUS_A.getTextOfMessage(), 1.0, Double.MAX_VALUE));
        String inputValue = reader.readLine();
        if (checkInputParsedToDouble(inputValue) && checkParsedInputForCorrectness(inputValue)) {
            model.setRadiusA(Double.parseDouble(inputValue));
        }
        else {
            checkAndSetValueForParameterRadiusA(reader);
        }
    }

    public void checkAndSetValueForParameterFocalDistance(BufferedReader reader) throws IOException{
        view.printMessage(String.format(Messages.ENTER_FOCAL_DISTANCE.getTextOfMessage(), 0.5, model.getRadiusA() - 1));
        String inputValue = reader.readLine();
        if (checkInputParsedToDouble(inputValue) && checkParsedInputForCorrectness(inputValue)) {
            if (isRadiusBCorrect(Double.parseDouble(inputValue))) {
                model.setFocalDistance(Double.parseDouble(inputValue));
            }
        }
        else {
            checkAndSetValueForParameterFocalDistance(reader);
        }
    }

    public void checkAndSetValueForParameterRadiusB(BufferedReader reader) throws IOException{
        view.printMessage(Messages.ENTER_RADIUS_B.getTextOfMessage());
        String inputValue = reader.readLine();
        if (checkInputParsedToDouble(inputValue) && checkParsedInputForCorrectness(inputValue)) {
            if (isRadiusBCorrect(model.getFocalDistance())) {
                model.setRadiusB(Double.parseDouble(inputValue));
            }
        }
        else {
            checkAndSetValueForParameterRadiusB(reader);
        }
    }


    /**
     * Checks input from the user for possibility to turn it into Integer.
     * Returns respective message if it's not a case.
     * Returns int value if it's possible.
     *
     * @param input
     */
    public boolean checkInputParsedToDouble(String input) {
        double testDouble = 0;
        try {
            testDouble = Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            view.printMessage(String.format(Messages.INCORRECT_INPUT.getTextOfMessage(), input));
            return false;
        }
    }


    public boolean checkParsedInputForCorrectness(String input) {
        double testDouble = Double.parseDouble(input);
        if (testDouble <= 0 || testDouble > Integer.MAX_VALUE) {
            return false;
        } else {
            return true;
        }
    }

    public double calculateY(double x, double radiusA, double radiusB) {
        double y = Math.sqrt(Math.pow(radiusB, 2) * (1 - (Math.pow(x, 2) / (Math.pow(radiusA, 2)))));
        return y;
    }

    public double calculateX(double y, double radiusA, double radiusB) {
        double x = Math.sqrt(Math.pow(radiusA, 2)*(1 - (Math.pow(y, 2)/(Math.pow(radiusB, 2)))));
        return x;
    }

    public boolean isRadiusBCorrect(double focalDistance) {
        double radiusB = Math.sqrt(Math.pow(model.getRadiusA(), 2) - Math.pow(focalDistance, 2));
        if (radiusB <= 0 ) {
            Messages.INCORRECT_INPUT_FOR_RADIUSB_CALCULATION.getTextOfMessage();
            return false;
        }
        else {
            return true;
        }
    }

    public void setRadiusB() {
        model.setRadiusB(calculateRadiusB());
    }

    public double calculateRadiusB() {
        double radiusB = Math.sqrt(Math.pow(model.getRadiusA(), 2) - Math.pow(model.getFocalDistance(), 2));
        return radiusB;
    }

    public void checkUserChoice(BufferedReader reader) throws IOException{
        String userInput = reader.readLine();
        if (userInput.toLowerCase().equals("y") || userInput.toLowerCase().equals("yes") ||
            userInput.toLowerCase().equals("n") || userInput.toLowerCase().equals("no")) {
            matchUserChoice(userInput, reader);
        }
        else {
            view.printMessage(String.format(Messages.INCORRECT_INPUT.getTextOfMessage(), userInput));
            checkUserChoice(reader);
        }
    }

    public void checkUserChoiceToEndOrStartNewProgram(BufferedReader reader) throws IOException{
        String userInput = reader.readLine();
        if (userInput.toLowerCase().equals("start")){
            startProgram();
        }
        else if (userInput.toLowerCase().equals("end")) {}
        else {
            view.printMessage(String.format(Messages.INCORRECT_INPUT.getTextOfMessage(), userInput));
            checkUserChoiceToEndOrStartNewProgram(reader);
        }
    }

    public void matchUserChoice(String input, BufferedReader reader) throws IOException{
        if (input.toLowerCase().equals("y") || input.toLowerCase().equals("yes")) {
            view.printMessage(Messages.SET_PARAMETER_TO_VIEW.getTextOfMessage());
            choiceSelector(reader);
        }
    }

    public void choiceSelector(BufferedReader reader) throws IOException{
        view.printMessage(Messages.VIEW_PARAMETER_CHOICE.getTextOfMessage());
        String userChoice = reader.readLine();
        switch (userChoice) {
            case "All" :
                view.printMessage(model.getAllData());
                break;

            case "X" :
                if (model.getX() != 0) {
                    view.printMessage(String.valueOf(model.getX()));
                }
                else if (model.getY() != 0) {
                        double calculatedX = calculateX(model.getY(), model.getRadiusA(), model.getRadiusB());
                        model.setX(calculatedX);
                        view.printMessage(String.valueOf(model.getX()));
                }
                else {
                    checkAndSetValueForParameterY(reader);
                    double calculatedX = calculateX(model.getY(), model.getRadiusA(), model.getRadiusB());
                    model.setX(calculatedX);
                    view.printMessage(String.valueOf(model.getX()));
                }
                break;

            case "Y" :
                if (model.getY() != 0) {
                    view.printMessage(String.valueOf(model.getY()));
                }
                else if (model.getX() != 0) {
                        double calculatedY = calculateY(model.getX(), model.getRadiusA(), model.getRadiusB());
                        model.setY(calculatedY);
                        view.printMessage(String.valueOf(model.getY()));
                }
                else {
                    checkAndSetValueForParameterX(reader);
                    double calculatedY = calculateY(model.getX(), model.getRadiusA(), model.getRadiusB());
                    model.setY(calculatedY);
                    view.printMessage(String.valueOf(model.getY()));
                }
                break;

            case "FocalDistance" :
                view.printMessage(String.valueOf(model.getFocalDistance()));
                break;

            case "RadiusA" :
                view.printMessage(String.valueOf(model.getRadiusA()));
                break;

            case "RadiusB" :
                view.printMessage(String.valueOf(model.getRadiusB()));
                break;

            default:
                view.printMessage(String.format(Messages.INCORRECT_INPUT.getTextOfMessage(), userChoice));
                choiceSelector(reader);
        }
    }
}



