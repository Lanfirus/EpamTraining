package ua.training.servlet;

import ua.training.controller.ControllerWeb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Dudchenko Andrei
 */
public class LoginProblemServlet extends HttpServlet {

    private ControllerWeb controllerWeb;

    private final static String index = "/login_problem.jsp";

    public void init(){
        final Object controllerWeb = getServletContext().getAttribute("controller");
        if (controllerWeb == null || !(controllerWeb instanceof ControllerWeb)) {
            throw new IllegalStateException("Controller is not setup");
        }
        else {
            this.controllerWeb = (ControllerWeb)controllerWeb;
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<String> userRegistrationData = controllerWeb.getUserRegistrationData();
        req.setAttribute("nameUser", userRegistrationData.get(0));
        req.setAttribute("surnameUser", userRegistrationData.get(1));
        req.setAttribute("patronymicUser", userRegistrationData.get(2));
        req.setAttribute("loginUser", userRegistrationData.get(3));
        req.setAttribute("passwordUser", "");
        req.setAttribute("commentUser", userRegistrationData.get(5));
        req.setAttribute("homephonenumberUser", userRegistrationData.get(6));
        req.setAttribute("mobilehonenumberUser", userRegistrationData.get(7));
        req.setAttribute("emailUser", userRegistrationData.get(8));

        req.getRequestDispatcher(index).forward(req, resp);
    }
}