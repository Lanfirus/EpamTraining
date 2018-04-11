package ua.training.controller.command;

import ua.training.controller.exceptions.NotUniqueLoginException;
import ua.training.model.User;
import ua.training.controller.servlet.Servlet;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Dudchenko Andrei
 */
public class Registration implements Command {
    
    @Override
    public String execute(HttpServletRequest request) {
        User user = new User(setUserData(request));
        return tryToAddUserRegistrationDataToDB(user, request);
    }

    private Map<String, String> setUserData(HttpServletRequest request){
        Map<String, String> preparedUserData = new LinkedHashMap<>();
        preparedUserData.put("name", request.getParameter("name"));
        preparedUserData.put("surname", request.getParameter("surname"));
        preparedUserData.put("patronymic", request.getParameter("patronymic"));
        preparedUserData.put("login", request.getParameter("login"));
        preparedUserData.put("password", request.getParameter("password"));
        preparedUserData.put("comment", request.getParameter("comment"));
        preparedUserData.put("homePhoneNumber", request.getParameter("homephonenumber"));
        preparedUserData.put("mobilePhoneNumber", request.getParameter("mobilephonenumber"));
        preparedUserData.put("email", request.getParameter("email"));
        return preparedUserData;
    }

    private String tryToAddUserRegistrationDataToDB(User user, HttpServletRequest request){
        try{
            Servlet.getUtilController().onRecievingUserRegistrationDataFromWeb(user);
            return "/registration-successful.jsp";
        }
        catch (NotUniqueLoginException e) {
            System.out.println(e.getMessage());
            makePreemptiveFillingOfUserRegistrationData(request, user);
            return "/login_problem.jsp";
        }
    }

    private void makePreemptiveFillingOfUserRegistrationData(HttpServletRequest request, User user){
        request.setAttribute("nameUser", user.getName());
        request.setAttribute("surnameUser", user.getSurame());
        request.setAttribute("patronymicUser", user.getPatronymic() == null ? "" : user.getPatronymic());
        request.setAttribute("loginUser", user.getLogin());
        request.setAttribute("passwordUser", "");
        request.setAttribute("commentUser", user.getComment() == null ? "" : user.getComment());
        request.setAttribute("homephonenumberUser", user.getHomePhoneNumber() == null ? "" : user.getHomePhoneNumber());
        request.setAttribute("mobilehonenumberUser", user.getMobilePhoneNumber());
        request.setAttribute("emailUser", user.getEmail());
    }
}