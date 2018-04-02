package ua.training.command;

import javax.servlet.http.HttpServletRequest;

public class MyException implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        throw new RuntimeException("Generated exception");
    }
}
