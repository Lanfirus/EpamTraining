package Task6.ua.training.model;

import java.util.Map;

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
