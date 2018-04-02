package ua.training.filter;


import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private RequestDispatcher defaultRequestDispatcher;
    @Override
    public void init(FilterConfig filterConfig){
        this.defaultRequestDispatcher =   filterConfig.getServletContext().getNamedDispatcher("default");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType("text/html");
        servletResponse.setCharacterEncoding("UTF-8");
        servletRequest.setCharacterEncoding("UTF-8");
        //defaultRequestDispatcher.forward(servletRequest, servletResponse);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
    }
}
