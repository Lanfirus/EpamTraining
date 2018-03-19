package ua.training.servlet;

import ua.training.controller.ControllerWeb;
import ua.training.model.DBEmu;
import ua.training.model.Notebook;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Locale;

/**
 * @author Dudchenko Andrei
 */
@WebListener
public class ContextListener implements ServletContextListener{

    private ControllerWeb controllerWeb;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
            Notebook model = new Notebook();
            DBEmu emu = new DBEmu();

            controllerWeb = new ControllerWeb(model);
            model.setController(controllerWeb);
            controllerWeb.initializeDB(emu, model);
            controllerWeb.bundleInitialization(new Locale("en"));

            final ServletContext servletContext = servletContextEvent.getServletContext();
            servletContext.setAttribute("controller", controllerWeb);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
