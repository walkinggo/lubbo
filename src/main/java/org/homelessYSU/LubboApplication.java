package org.homelessYSU;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.homelessYSU.beans.factory.annotation.AOP.LubboAOPScanner;
import org.homelessYSU.beans.factory.annotation.EnableLubboApplication;
import org.homelessYSU.web.ContextLoaderListener;
import org.homelessYSU.web.servlet.DispatcherServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

public class LubboApplication {
    private static final Logger logger = LoggerFactory.getLogger(LubboApplication.class);

    public static void run(Class clazz) {
        URL location = clazz.getProtectionDomain().getCodeSource().getLocation();
        logger.info("location : " + location);
        String name = clazz.getPackage().getName();
        logger.info("ApplicationName : " + name);
        int port = 0;
        try {
            port = Class.forName(clazz.getName()).getAnnotation(EnableLubboApplication.class).port();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        run(name, port);
    }

    public static void run(String packageLocation, int port) {
        Tomcat tomcat = new Tomcat();

        tomcat.setPort(port); // 设置Tomcat的端口号
        Context ctx = tomcat.addContext("", null);
//
//        Context ctx = tomcat.addWebapp("", new File("web").getAbsolutePath());
        ctx.getServletContext().setAttribute("packageLocation", packageLocation);
//


        tomcat.addServlet(ctx, "DispatcherServlet", new DispatcherServlet());


        ctx.addServletMappingDecoded("/", "DispatcherServlet");
        ctx.addLifecycleListener(new Tomcat.FixContextListener());
        ctx.addApplicationListener(ContextLoaderListener.class.getName());

        Connector connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);
        // 启动Tomcat服务器
        try {
            tomcat.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }

        logger.info("Tomcat started on port " + port);
        logger.info("Visit http://127.0.0.1:" + port);
        tomcat.getServer().await();

    }
}
