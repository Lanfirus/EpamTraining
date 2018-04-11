package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

public class LoginForm implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String pass = request.getParameter("password");
        System.out.println(name + " " + pass);
        if( name == null || name.equals("") || pass == null || pass.equals("")  ){
            return "/login.jsp";
        }
        return "/login.jsp";
    }
}
