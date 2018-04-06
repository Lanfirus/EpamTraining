package ua.training.command;

import javax.servlet.http.HttpServletRequest;

public class NavigateToCustomOrderPage implements Command{

    @Override
    public String execute(HttpServletRequest request) {
        return "/custom_order.jsp";
    }
}
