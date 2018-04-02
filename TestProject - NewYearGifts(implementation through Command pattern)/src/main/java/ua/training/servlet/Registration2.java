package ua.training.servlet;

import ua.training.controller.ControllerWeb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Dudchenko Andrei
 */
public class Registration2 extends HttpServlet {

    private ControllerWeb controllerWeb;

    public void init(){
        final Object controllerWeb = getServletContext().getAttribute("controller");
        if (controllerWeb == null || !(controllerWeb instanceof ControllerWeb)) {
            throw new IllegalStateException("Controller is not setup");
        }
        else {
            this.controllerWeb = (ControllerWeb)controllerWeb;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String> userRegistrationData = new CopyOnWriteArrayList<>();
        userRegistrationData.add(request.getParameter("name"));
        userRegistrationData.add(request.getParameter("surname"));
        userRegistrationData.add(request.getParameter("patronymic"));
        userRegistrationData.add(request.getParameter("login"));
        userRegistrationData.add(request.getParameter("password"));
        userRegistrationData.add(request.getParameter("comment"));
        userRegistrationData.add(request.getParameter("homephonenumber"));
        userRegistrationData.add(request.getParameter("mobilephonenumber"));
        userRegistrationData.add(request.getParameter("email"));

        if (controllerWeb.onRecievingDataFromWeb(userRegistrationData)) {
            //response.sendRedirect("/registration-successful");
            request.getRequestDispatcher("/registration-successful").forward(request, response);
        }
        else {
            //response.sendRedirect("/login_problem");
            request.getRequestDispatcher("/login_problem").forward(request, response);
        }
    }
}