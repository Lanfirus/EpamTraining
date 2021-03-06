package ua.training.controller.command;

import ua.training.controller.servlet.Servlet;
import ua.training.model.entity.User;
import ua.training.model.exception.NotUniqueLoginException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Dudchenko Andrei
 */
public class Registration implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        User user = new User(Servlet.getUtilController().setUserData(request));
        return tryToAddUserRegistrationDataToDB(user, request);
    }

    private String tryToAddUserRegistrationDataToDB(User user, HttpServletRequest request){
        try{
            Servlet.getUtilController().onRecievingUserRegistrationDataFromWeb(user);
            return "/registration-successful.jsp";
        }
        catch (NotUniqueLoginException e) {
            System.err.println(e.getMessage());
            Servlet.getUtilController().setUserEnteredDataBackToForm(request, user);
            setProblemWithLoginAttribute(request);
            return "/registration.jsp";
        }
        catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void setProblemWithLoginAttribute(HttpServletRequest request){
        request.setAttribute("loginProblem", true);
    }
}