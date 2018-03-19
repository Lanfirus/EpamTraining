package ua.training.servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * @author Dudchenko Andrei
 */
public class GetStartPageServlet extends HttpServlet {

    private final static String index = "/WEB-INF/index.jsp";

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher(index).forward(req, resp);
    }
}