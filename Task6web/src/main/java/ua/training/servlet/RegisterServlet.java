package ua.training.servlet;

import ua.training.controller.ControllerWeb;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * @author Dudchenko Andrei
 */
public class RegisterServlet extends HttpServlet {

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

        request.setCharacterEncoding("UTF-8");

        final String surname = request.getParameter("surname");
        final String name = request.getParameter("name");
        final String patronymic = request.getParameter("patronymic");
        final String login = request.getParameter("login");
        final String comment = request.getParameter("comment");
        final String homephonenumber = request.getParameter("homephonenumber");
        final String mobilephonenumber = request.getParameter("mobilephonenumber");
        final String email = request.getParameter("email");

        Map<Integer, Object> mapWithUserData = new ConcurrentHashMap<>();
        mapWithUserData.put(0, surname);
        mapWithUserData.put(1, name);
        mapWithUserData.put(2, patronymic);
        mapWithUserData.put(3, "Ivanov");
        mapWithUserData.put(4, login);
        mapWithUserData.put(5, comment);
        mapWithUserData.put(6, "Managers");
        mapWithUserData.put(7, homephonenumber);
        mapWithUserData.put(8, mobilephonenumber);
        mapWithUserData.put(9, "380501234567");
        mapWithUserData.put(10, email);
        mapWithUserData.put(11, "dsfsf");
        mapWithUserData.put(12, new HashMap<>());
        mapWithUserData.put(13, "SomethingInRegisterServlet");
        mapWithUserData.put(14, new GregorianCalendar());
        mapWithUserData.put(15, new GregorianCalendar());

        if (controllerWeb.onRecievingDataFromWeb(mapWithUserData)) {
            response.sendRedirect("/registration-successful");
        }
        else {
            response.sendRedirect("/login_problem");
        }
    }
}