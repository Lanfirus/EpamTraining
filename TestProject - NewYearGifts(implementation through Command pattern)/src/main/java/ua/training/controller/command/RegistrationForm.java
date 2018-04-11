package ua.training.controller.command;

import ua.training.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class RegistrationForm implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/registration.jsp";
    }
}
