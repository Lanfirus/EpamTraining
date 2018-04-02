package ua.training.servlet;

import ua.training.command.*;
import ua.training.controller.ControllerWeb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(value = "/app/*")
public class Servlet extends HttpServlet {
    private Map<String, Command> commands = new ConcurrentHashMap<>();
    private static ControllerWeb controllerWeb;

    public void init(){
        {
            commands.put("logout", new LogOut());
            commands.put("login_form", new Login());
            commands.put("registration_form", new RegistrationForm());
            commands.put("registration", new Registration());
            commands.put("exception", new MyException());
        }

        final Object controllerWeb = getServletContext().getAttribute("controller");
        if (controllerWeb == null || !(controllerWeb instanceof ControllerWeb)) {
            throw new IllegalStateException("Controller is not setup");
        }
        else {
            this.controllerWeb = (ControllerWeb)controllerWeb;
        }
    }

    public static ControllerWeb getControllerWeb() {
        return controllerWeb;
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
}