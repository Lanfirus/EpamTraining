package ua.training.controller.command;

import ua.training.controller.exceptions.InappropriateBulkOrderException;
import ua.training.controller.utils.UtilController;
import ua.training.controller.servlet.Servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

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
                    tryToAddOrderDataToDB(setUserData(session));
                    session.removeAttribute("quantity_to_order_small_boxes");
                    session.removeAttribute("quantity_to_order_medium_boxes");
                    session.removeAttribute("quantity_to_order_big_boxes");
                    return "/order_submission_complete.jsp";
                }
                else {
                    return "/index.jsp";
                }
            }
            else {
                return "/index.jsp";
            }
        }
        catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        catch(InappropriateBulkOrderException e){
            session.removeAttribute("quantity_to_order_small_boxes");
            session.removeAttribute("quantity_to_order_medium_boxes");
            session.removeAttribute("quantity_to_order_big_boxes");
            return "/inappropriate_bulk_order.jsp";
        }
    }

    private Map<String, String> setUserData(HttpSession session) {
        Map<String, String> preparedUserData = new LinkedHashMap<>();
        preparedUserData.put("login", (String) session.getAttribute("login"));
        preparedUserData.put("quantity_to_order_small_boxes", (String) session.getAttribute("quantity_to_order_small_boxes"));
        preparedUserData.put("quantity_to_order_medium_boxes", (String) session.getAttribute("quantity_to_order_medium_boxes"));
        preparedUserData.put("quantity_to_order_box_boxes", (String) session.getAttribute("quantity_to_order_big_boxes"));
        return preparedUserData;
    }

        private void tryToAddOrderDataToDB(Map<String, String> userData) throws InappropriateBulkOrderException{
        try {
            Servlet.getUtilController().onRecievingOrderDataFromWeb(userData);
        }
        catch(SQLException e){
            throw new RuntimeException("Something bad has happened" + e.getMessage());
        }
    }
}
