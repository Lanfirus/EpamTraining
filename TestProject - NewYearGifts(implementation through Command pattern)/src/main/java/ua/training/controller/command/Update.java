package ua.training.controller.command;

import ua.training.controller.servlet.Servlet;
import ua.training.model.entity.User;
import ua.training.model.exception.NotUniqueLoginException;

import javax.servlet.http.HttpServletRequest;

public class Update implements Command{

    @Override
    public String execute(HttpServletRequest request) {
        User user = new User(Servlet.getUtilController().setUserData(request));
        return tryToUpdateUserRegistrationDataInDB(user, request);
    }

    private String tryToUpdateUserRegistrationDataInDB(User user, HttpServletRequest request){
        try{
            Servlet.getUtilController().onRecievingUserUpdateDataFromWeb(user);
            return "/update_successful.jsp";
        }
        catch (NotUniqueLoginException e) {
            System.err.println(e.getMessage());
            Servlet.getUtilController().setUserEnteredDataBackToForm(request, user);
            setProblemWithLoginAttribute(request);
            setViewDataAttribute(request);
            return "/registration.jsp";
        }
        catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void setProblemWithLoginAttribute(HttpServletRequest request){
        request.setAttribute("loginProblem", true);
    }

    private void setViewDataAttribute(HttpServletRequest request){
        request.setAttribute("userView", true);
    }
}
