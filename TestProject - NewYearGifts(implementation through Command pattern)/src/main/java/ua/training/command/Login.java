package ua.training.command;

import ua.training.controller.UtilController;
import ua.training.servlet.Servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class Login implements Command{

    private UtilController utilController;


    @Override
    public String execute(HttpServletRequest request) {

        final String login = request.getParameter("login");
        final String password = request.getParameter("password");
        String role;

        final HttpSession session = request.getSession();

        utilController = Servlet.getUtilController();


        try {
            if (session.getAttribute("login") != null && session.getAttribute("password") != null) {
                role = (String) session.getAttribute("role");
            }
            else if (utilController.userIsExist(login, password)) {
                role = utilController.getRoleByLoginPassword(login, password);
                request.getSession().setAttribute("login", login);
                request.getSession().setAttribute("password", password);
                request.getSession().setAttribute("role", role);
                String fullName = utilController.getFullNameByLoginPassword(login, password);
                request.getSession().setAttribute("full_name", fullName);
            }
            else {
                role = "unknown";
            }
        }
        catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        return getUserMenuPage(role);
    }

    private String getUserMenuPage(final String role){
        if (role.equals("admin")) {
            return "/products.jsp";
        }
        else if (role.equals("user")) {
            return "/products.jsp";
        }
        else {
            return "/login.jsp";
        }
    }
}
