package org.homelessYSU;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.homelessYSU.web.ContextLoaderListener;
import org.homelessYSU.web.servlet.DispatcherServlet;

import java.net.URL;

public class LubboApplication {
    public static void run(Class clazz) {
        URL location = clazz.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(location);
        String name = clazz.getPackage().getName();
        System.out.println("name = " + name);
        run(name);
    }

    public static void run(String packageLocation) {
        Tomcat tomcat = new Tomcat();
        int port = 8080;
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

        System.out.println("Tomcat started on port " + port);
        System.out.println("Visit http://127.0.0.1:" + port);
        tomcat.getServer().await();

    }
}
