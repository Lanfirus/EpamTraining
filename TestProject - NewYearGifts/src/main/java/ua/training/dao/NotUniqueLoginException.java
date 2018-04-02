package ua.training.dao;

import java.util.List;

/**
 * @author Dudchenko Andrei
 */
public class NotUniqueLoginException extends Exception {

    private List<String> userRegistrationData;

    public List<String> getUserRegistrationData() {
        return userRegistrationData;
    }

    public NotUniqueLoginException() {}

    public NotUniqueLoginException(String message, List<String> userRegistrationData) {
        super(message);
        this.userRegistrationData = userRegistrationData;
    }
}
