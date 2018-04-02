package ua.training.command;

import ua.training.servlet.Servlet;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Dudchenko Andrei
 */
public class Registration implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        List<String> userRegistrationData = collectUserRegistrationData(request);
        return tryToAddUserRegistrationDataToDB(userRegistrationData, request);
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
        if (Servlet.getControllerWeb().onRecievingDataFromWeb(userRegistrationData)) {
            return "/registration-successful.jsp";
        }
        else {
            makePreemptiveFillingOfUserRegistrationData(request);
            return "/login_problem.jsp";
        }
    }

    private void makePreemptiveFillingOfUserRegistrationData(HttpServletRequest request){
        List<String> userRegistrationData = Servlet.getControllerWeb().getUserRegistrationData();
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