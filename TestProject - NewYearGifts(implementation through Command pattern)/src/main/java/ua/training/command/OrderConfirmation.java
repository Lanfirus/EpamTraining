package ua.training.command;

import ua.training.controller.UtilController;
import ua.training.servlet.Servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class OrderConfirmation implements Command{

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
                    premadeOrderProcessing(request, session);
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

    private void premadeOrderProcessing(HttpServletRequest request, HttpSession session){
        if(session.getAttribute("quantity_to_order_small_boxes") == null) {
            session.setAttribute("quantity_to_order_small_boxes",
                    (request.getParameter("quantity_to_order_small_boxes") == null) ? 0 :
                            request.getParameter("quantity_to_order_small_boxes"));
            session.setAttribute("quantity_to_order_medium_boxes",
                    (request.getParameter("quantity_to_order_medium_boxes") == null) ? 0 :
                            request.getParameter("quantity_to_order_medium_boxes"));
            session.setAttribute("quantity_to_order_big_boxes",
                    (request.getParameter("quantity_to_order_big_boxes") == null) ? 0 :
                            request.getParameter("quantity_to_order_big_boxes"));
        }
        else {
            mergeSessionAttributes(request, session, "quantity_to_order_small_boxes");
            mergeSessionAttributes(request, session, "quantity_to_order_medium_boxes");
            mergeSessionAttributes(request, session, "quantity_to_order_big_boxes");
        }
    }

    private void mergeSessionAttributes(HttpServletRequest request, HttpSession session, String attributeName){
        Integer storedAttributeValue = Integer.parseInt((String)session.getAttribute(attributeName));
        Integer newAttributeValueGottenFromUser = Integer.parseInt(request.getParameter(attributeName));
        Object newAttributeValueToStoreInSession = (storedAttributeValue + newAttributeValueGottenFromUser);
        session.setAttribute(attributeName, newAttributeValueToStoreInSession);
    }

    private String getUserMenuPage(final String role){
        if (role.equals("user") || role.equals("admin")) {
            return "/order_confirmation.jsp";
        }
        else {
            return "/login.jsp";
        }
    }
}
