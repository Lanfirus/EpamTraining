package ua.training.controller.command;

import ua.training.controller.servlet.Servlet;
import ua.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeForm implements Command{

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        User user = Servlet.getUtilController().getUserFromDBByLogin(login);
        Servlet.getUtilController().setUserEnteredDataBackToForm(request, user);
        setViewDataAttribute(request);
        return "/registration.jsp";
    }

    private void setViewDataAttribute(HttpServletRequest request){
        request.setAttribute("userView", true);
    }
}
