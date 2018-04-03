package ua.training.dao;

/**
 * @author Dudchenko Andrei
 */
public class NotUniqueLoginException extends Exception {

    public NotUniqueLoginException(String message) {
        super(message);
    }
}
