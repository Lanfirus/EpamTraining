package ua.training.controller.command;

import ua.training.controller.servlet.Servlet;
import ua.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Delete implements Command{

    @Override
    public String execute(HttpServletRequest request) {
        User user = new User(Servlet.getUtilController().setUserData(request));
        return tryToDeleteUserRegistrationDataInDB(user, request);
    }

    private String tryToDeleteUserRegistrationDataInDB(User user, HttpServletRequest request) {
        if (Servlet.getUtilController().deleteUser(user.getLogin(), user.getPassword())) {
            HttpSession session = request.getSession();
            session.removeAttribute("login");
            session.removeAttribute("password");
            session.removeAttribute("role");
            session.removeAttribute("full_name");
            return "/index.jsp";
        }
        else{
            throw new RuntimeException();
        }
    }
}
