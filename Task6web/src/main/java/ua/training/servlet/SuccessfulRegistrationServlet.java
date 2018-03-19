package ua.training.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Dudchenko Andrei
 */
public class SuccessfulRegistrationServlet extends HttpServlet{

    private final static String index = "/WEB-INF/registration-successful.jsp";

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher(index).forward(req, resp);
    }
}
