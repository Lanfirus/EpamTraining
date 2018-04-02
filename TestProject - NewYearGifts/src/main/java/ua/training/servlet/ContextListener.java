package ua.training.servlet;

import ua.training.controller.ControllerWeb;
import ua.training.dao.SQLInteraction;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;
import java.util.Locale;

/**
 * @author Dudchenko Andrei
 */
@WebListener
public class ContextListener implements ServletContextListener{

    private ControllerWeb controllerWeb;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
            SQLInteraction sqlInteraction = new SQLInteraction();

            controllerWeb = new ControllerWeb(sqlInteraction);
            try {
                controllerWeb.deInitializeDB();
                controllerWeb.initializeDB();
            }
            catch(SQLException e){
                System.err.println("SQL hasn't been initialized properly");
                System.err.println(e.getMessage());
            }
            controllerWeb.bundleInitialization(new Locale("en"));

            final ServletContext servletContext = servletContextEvent.getServletContext();
            servletContext.setAttribute("controller", controllerWeb);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
