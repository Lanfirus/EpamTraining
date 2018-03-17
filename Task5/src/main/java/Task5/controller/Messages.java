package Task5.controller;

public enum Messages {

    INITIAL_MESSAGE ("Welcome. Please, enter initial values for Ellipse. It's radiusA and focal distance."),
    INCORRECT_INPUT ("Your input: %s is incorrect"),
    ENTER_PARAMETER_X("Please, enter value for X. It should be value from 0 to %.2f."),
    ENTER_PARAMETER_Y("Please, enter value for Y. It should be value from 0 to %.2f."),
    ENTER_FOCAL_DISTANCE ("Please, enter value for Focal Distance. It should be value from %f to %f."),
    ENTER_RADIUS_A ("Please, enter value for Radius A. It should be value from %.2f to %e."),
    INCORRECT_INPUT_FOR_RADIUSB_CALCULATION ("Your input is incorrect. Focal distance [%f] can't be bigger than RadiusA [%f]"),
    PARAMETERS_SET ("All parameters have been successfully set."),
    VIEW_PARAMETER_CHOICE ("Do you want to see the value of some parameter? [Y]es or [N]o"),
    SET_PARAMETER_TO_VIEW ("Enter parameter you want to see. Allowed parameters are [X],[Y],[Focal Distance],[RadiusA],[RadiusB] and [All]"),
    END_OF_PROGRAM_CHOICE ("Do you want to finish program? [End] program or [Start] new one."),
    EXCEPTION_HANDLER ("Something happened and exception have been thrown");

    private final String message;
    Messages (String message) {
        this.message = message;
    }

    String getTextOfMessage() {
        return message;
    }
}
