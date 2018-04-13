package ua.training.controller.servlet;

import ua.training.controller.command.*;
import ua.training.controller.utils.UtilController;
import ua.training.model.utils.SQLInteraction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet(value = "/app/*")
public class Servlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();
    private static UtilController utilController;

    public void init(){
        {
            commands.put("logout", new LogOut());
            commands.put("login_form", new LoginForm());
            commands.put("login", new Login());
            commands.put("registration_form", new RegistrationForm());
            commands.put("registration", new Registration());
            commands.put("exception", new MyException());
            commands.put("premade_order", new PremadeOrderProcessing());
            commands.put("return_to_main", new ReturnToMain());
            commands.put("products", new NavigateToProductPage());
            commands.put("order_confirmation", new OrderConfirmation());
            commands.put("custom_order", new NavigateToCustomOrderPage());
            commands.put("sugar_filter", new SugarFilter());
            commands.put("custom_order_processing", new CustomOrderProcessing());
            commands.put("language", new LanguageChange());
            commands.put("change", new ChangeForm());
            commands.put("update", new Update());
            commands.put("delete", new Delete());
        }
        utilControllerInitialization();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        path = path.replaceAll(".*/app/" , "");
        Command command = commands.getOrDefault(path ,
                r->"/index.jsp");
        String page = command.execute(request);

        request.getRequestDispatcher(page).forward(request, response);
    }

    private void utilControllerInitialization(){
        SQLInteraction sqlInteraction = SQLInteraction.getInstance();
        System.out.println("got instance");
        utilController = new UtilController(sqlInteraction);
        try {
            utilController.deInitializeDB();
        }
        catch (SQLException e) {
            System.err.println("SQL hasn't been initialized properly");
            System.err.println(e.getMessage());
        }
        try{
            utilController.initializeDB();
        }
        catch (SQLException e) {
            System.err.println("SQL hasn't been initialized properly");
            System.err.println(e.getMessage());
        }
        utilController.bundleInitialization(new Locale("en"));
    }

    public static UtilController getUtilController() {
        return utilController;
    }
}