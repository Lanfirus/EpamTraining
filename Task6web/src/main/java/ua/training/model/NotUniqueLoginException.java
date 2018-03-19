package ua.training.model;

import java.util.Map;

/**
 * @author Dudchenko Andrei
 */
public class NotUniqueLoginException extends Exception {

    private Map<Integer, Object> map;

    public Map<Integer, Object> getMap() {
        return map;
    }

    public NotUniqueLoginException() {}

    public NotUniqueLoginException(String message, Map<Integer, Object> map) {
        super(message);
        this.map = map;
    }
}
