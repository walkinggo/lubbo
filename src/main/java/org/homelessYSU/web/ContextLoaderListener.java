package org.homelessYSU.web;
import org.homelessYSU.beans.factory.annotation.AOP.LubboAOPScanner;
import org.homelessYSU.beans.factory.annotation.LubboComponentScanner;
import org.homelessYSU.beans.factory.config.ConfigurableListableBeanFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Map;

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
        LubboAOPScanner scanner = new LubboAOPScanner();
        Map map = scanner.loadBeanDefinitions(packageLocation);
        servletContext.setAttribute(LubboAOPScanner.AOP_ATTRIBUTE,map);

        System.out.println("  _    _   _ ____  ____   ___  \n" +
                " | |  | | | | __ )| __ ) / _ \\ \n" +
                " | |  | | | |  _ \\|  _ \\| | | |\n" +
                " | |__| |_| | |_) | |_) | |_| |\n" +
                " |_____\\___/|____/|____/ \\___/ \n" +
                "                               ");
    }


}