package org.homelessYSU;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.homelessYSU.web.ContextLoaderListener;
import org.homelessYSU.web.servlet.DispatcherServlet;

import java.io.File;

public class LubboApplication {
    public static void run(Class clazz){
        run();
    }

    public static void run(){
        Tomcat tomcat = new Tomcat();
        int port = 8080;
        tomcat.setPort(port); // 设置Tomcat的端口号

        Context ctx = tomcat.addWebapp("", new File("web").getAbsolutePath());
//        tomcat.addServlet(ctx, "DispatcherServlet", new DispatcherServlet());
//
//
//        ctx.addServletMappingDecoded("/", "DispatcherServlet");
//        ctx.addLifecycleListener(new Tomcat.FixContextListener());
//        ctx.addApplicationListener(ContextLoaderListener.class.getName());

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
