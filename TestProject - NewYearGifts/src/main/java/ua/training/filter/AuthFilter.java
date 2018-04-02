package ua.training.filter;

import ua.training.controller.ControllerWeb;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter (value = "/login")
public class AuthFilter implements Filter {

    private ControllerWeb controllerWeb;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        final String login = request.getParameter("login");
        final String password = request.getParameter("password");

        final HttpSession session = request.getSession();

        setControllerWeb(request);

        try {
            if (session.getAttribute("login") != null && session.getAttribute("password") != null) {
                final String role = (String) session.getAttribute("role");
                moveToMenu(request, response, role);
            } else if (controllerWeb.userIsExist(login, password)) {
                final String role = controllerWeb.getRoleByLoginPassword(login, password);
                request.getSession().setAttribute("login", login);
                request.getSession().setAttribute("password", password);
                request.getSession().setAttribute("role", role);
                moveToMenu(request, response, role);
            } else {
                final String role = "unknown";
                moveToMenu(request, response, role);
            }
        }
        catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private void setControllerWeb(HttpServletRequest request){
        final Object controllerWeb = request.getServletContext().getAttribute("controller");
        if (controllerWeb == null || !(controllerWeb instanceof ControllerWeb)) {
            throw new IllegalStateException("Controller is not setup");
        }
        else {
            this.controllerWeb = (ControllerWeb)controllerWeb;
        }
    }

    private void moveToMenu(final HttpServletRequest request, final HttpServletResponse response,
                            final String role) throws IOException, ServletException {
        if (role.equals("admin")) {
            request.getRequestDispatcher("/admin_menu.jsp").forward(request, response);
        }
        else if (role.equals("user")) {
            request.getRequestDispatcher("/user_menu.jsp").forward(request, response);
        }
        else {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
