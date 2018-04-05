package ua.training.servlet;

import ua.training.command.*;
import ua.training.controller.UtilController;
import ua.training.dao.SQLInteraction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
        if(page.contains("redirect")){
            response.sendRedirect(page.replace("redirect:", "/api"));
        }else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

    private void utilControllerInitialization(){
        SQLInteraction sqlInteraction = new SQLInteraction();
        utilController = new UtilController(sqlInteraction);
        try {
            System.out.println("5");
            utilController.deInitializeDB();
        }
        catch (SQLException e) {
            System.err.println("SQL hasn't been initialized properly");
            System.err.println(e.getMessage());
        }
        try{
            System.out.println("6");
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