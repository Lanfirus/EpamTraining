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
public class RegistrationServlet extends HttpServlet {

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
        List<String> userRegistrationData = collectUserRegistrationData(request);
        String index = tryToAddUserRegistrationDataToDB(userRegistrationData, request);
        request.getRequestDispatcher(index).forward(request, response);
    }

    private List<String> collectUserRegistrationData(HttpServletRequest request){
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
        return userRegistrationData;
    }

    private String tryToAddUserRegistrationDataToDB(List<String> userRegistrationData, HttpServletRequest request){
        if (controllerWeb.onRecievingDataFromWeb(userRegistrationData)) {
            return "/registration-successful.jsp";
        }
        else {
            makePreemptiveFillingOfUserRegistrationData(request);
            return "/login_problem.jsp";
        }
    }

    private void makePreemptiveFillingOfUserRegistrationData(HttpServletRequest request){
        List<String> userRegistrationData = controllerWeb.getUserRegistrationData();
        request.setAttribute("nameUser", userRegistrationData.get(0));
        request.setAttribute("surnameUser", userRegistrationData.get(1));
        request.setAttribute("patronymicUser", userRegistrationData.get(2));
        request.setAttribute("loginUser", userRegistrationData.get(3));
        request.setAttribute("passwordUser", "");
        request.setAttribute("commentUser", userRegistrationData.get(5));
        request.setAttribute("homephonenumberUser", userRegistrationData.get(6));
        request.setAttribute("mobilehonenumberUser", userRegistrationData.get(7));
        request.setAttribute("emailUser", userRegistrationData.get(8));
    }
}