package ua.training.command;

import javax.servlet.http.HttpServletRequest;

public class NavigateToProductPage implements Command{

    @Override
    public String execute(HttpServletRequest request) {
        return "/products.jsp";
    }
}
