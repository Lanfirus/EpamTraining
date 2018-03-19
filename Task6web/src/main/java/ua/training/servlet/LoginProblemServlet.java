package ua.training.servlet;

import ua.training.controller.ControllerWeb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author Dudchenko Andrei
 */
public class LoginProblemServlet extends HttpServlet {

    private ControllerWeb controllerWeb;

    private final static String index = "/WEB-INF/login_problem.jsp";

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

        Map<Integer, Object> map = controllerWeb.getMapToResendToUser();
        req.setAttribute("surnameUser", map.get(0));
        req.setAttribute("nameUser", map.get(1));
        req.setAttribute("patronymicUser", map.get(2));
        req.setAttribute("loginUser", map.get(4));
        req.setAttribute("commentUser", map.get(5));
        req.setAttribute("homephonenumberUser", map.get(7));
        req.setAttribute("mobilehonenumberUser", map.get(8));
        req.setAttribute("emailUser", map.get(10));
        req.setAttribute("test", "тест");

        req.getRequestDispatcher(index).forward(req, resp);
    }
}