package ua.training.controller.exceptions;

/**
 * @author Dudchenko Andrei
 */
public class NotUniqueLoginException extends Exception {

    public NotUniqueLoginException(String message) {
        super(message);
    }
}
