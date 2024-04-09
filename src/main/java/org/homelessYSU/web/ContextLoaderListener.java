package org.homelessYSU.web;
import org.homelessYSU.beans.factory.annotation.LubboComponentScanner;
import org.homelessYSU.beans.factory.config.ConfigurableListableBeanFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

public class ContextLoaderListener implements ServletContextListener {
    public static final String CONFIG_LOCATION_PARAM = "contextConfigLocation";
    private WebApplicationContext context;

    public ContextLoaderListener() {
    }

    public ContextLoaderListener(WebApplicationContext context) {
        this.context = context;
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        initWebApplicationContext(event.getServletContext());
    }

    private void initWebApplicationContext(ServletContext servletContext) {
        String sContextLocation = servletContext.getInitParameter(CONFIG_LOCATION_PARAM);
        String packageLocation = (String) servletContext.getAttribute("packageLocation");
        WebApplicationContext wac = new XmlWebApplicationContext(sContextLocation,packageLocation);
        wac.setServletContext(servletContext);
        this.context = wac;
        servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, this.context);
        System.out.println(" _______   __    __  .______   .______     ______   \n" +
                "|       \\ |  |  |  | |   _  \\  |   _  \\   /  __  \\  \n" +
                "|  .--.  ||  |  |  | |  |_)  | |  |_)  | |  |  |  | \n" +
                "|  |  |  ||  |  |  | |   _  <  |   _  <  |  |  |  | \n" +
                "|  '--'  ||  `--'  | |  |_)  | |  |_)  | |  `--'  | \n" +
                "|_______/  \\______/  |______/  |______/   \\______/  \n" +
                "                                                    ");
    }


}