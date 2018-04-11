package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

public class ReturnToMain implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return "/index.jsp";
    }
}
