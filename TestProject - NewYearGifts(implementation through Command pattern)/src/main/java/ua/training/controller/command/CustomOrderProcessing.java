package ua.training.controller.command;

import ua.training.controller.servlet.Servlet;
import ua.training.controller.utils.UtilController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class CustomOrderProcessing implements Command{

    private UtilController utilController;

    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final String login = (String)session.getAttribute("login");
        final String password = (String) session.getAttribute("password");
        String roleFromDB;

        utilController = Servlet.getUtilController();

        try {
            if (utilController.userIsExist(login, password)) {
                roleFromDB = utilController.getRoleByLoginPassword(login, password);
                if (roleFromDB.equals("admin") || roleFromDB.equals("user")) {
                    customOrderProcessing(request, session);
                }
                else {
                    roleFromDB = "unknown";
                }
            }
            else {
                roleFromDB = "unknown";
            }
        }
        catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        return getUserMenuPage(roleFromDB);
    }

    private void customOrderProcessing(HttpServletRequest request, HttpSession session){
        setParameterToSessionScope(session, request,"boxType");
        setParameterToSessionScope(session, request,"caramel_qty");
        setParameterToSessionScope(session, request,"chocolate_qty");
        setParameterToSessionScope(session, request,"jelly_qty");
        setParameterToSessionScope(session, request,"lollipop_qty");
        setParameterToSessionScope(session, request,"waffle_qty");
        setParameterToSessionScope(session, request,"marshmallow_qty");
        setParameterToSessionScope(session, request,"custom_order_qty");
    }

    private void mergeSessionAttributes(HttpServletRequest request, HttpSession session, String attributeName){

        String storedStringAttributeValue = (String)session.getAttribute(attributeName);
        Integer storedAttributeValue = Integer.parseInt(storedStringAttributeValue);
        Integer newAttributeValueGottenFromUser = Integer.parseInt(request.getParameter(attributeName));
        String newAttributeValueToStoreInSession = String.valueOf(storedAttributeValue + newAttributeValueGottenFromUser);
        session.setAttribute(attributeName, newAttributeValueToStoreInSession);
    }

    private String getUserMenuPage(final String role) {
        if (role.equals("user") || role.equals("admin")) {
            return "order_confirmation";
        } else {
            return "/login.jsp";
        }
    }

    private Integer getParameterValue(HttpServletRequest request, String parameter){
        Integer parameterValue;
        try {
            parameterValue = Integer.parseInt(request.getParameter(parameter));
        }
        catch (Throwable e) {
            throw new RuntimeException("You have entered wrong input data");
        }
        return parameterValue;
    }

    private void setParameterToSessionScope(HttpSession session, HttpServletRequest request, String parameter){
        session.setAttribute(parameter, getParameterValue(request, parameter));
    }
}
