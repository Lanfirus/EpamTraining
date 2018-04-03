package ua.training.command;

import ua.training.controller.UtilController;

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

        setUtilController(request);

        try {
            if (session.getAttribute("login") != null && session.getAttribute("password") != null) {
                role = (String) session.getAttribute("role");
            }
            else if (utilController.userIsExist(login, password)) {
                role = utilController.getRoleByLoginPassword(login, password);
                request.getSession().setAttribute("login", login);
                request.getSession().setAttribute("password", password);
                request.getSession().setAttribute("role", role);
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

    private void setUtilController(HttpServletRequest request){
        final Object controllerWeb = request.getServletContext().getAttribute("controller");
        if (controllerWeb == null || !(controllerWeb instanceof UtilController)) {
            throw new IllegalStateException("Controller is not setup");
        }
        else {
            this.utilController = (UtilController)controllerWeb;
        }
    }

    private String getUserMenuPage(final String role){
        if (role.equals("admin")) {
            return "/admin_menu.jsp";
        }
        else if (role.equals("user")) {
            return "/user_menu.jsp";
        }
        else {
            return "/login.jsp";
        }
    }
}
