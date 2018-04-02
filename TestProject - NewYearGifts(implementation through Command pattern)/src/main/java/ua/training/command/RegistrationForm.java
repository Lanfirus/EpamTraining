package ua.training.command;

import javax.servlet.http.HttpServletRequest;

public class RegistrationForm implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/registration.jsp";
    }
}
