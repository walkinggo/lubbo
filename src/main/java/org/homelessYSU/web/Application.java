package org.homelessYSU.web;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.homelessYSU.web.servlet.DispatcherServlet;

import java.io.File;

public class Application {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080); // 设置Tomcat的端口号

        // 创建一个context
//        Context ctx = tomcat.addContext("", new File(".").getAbsolutePath());

        Context ctx = tomcat.addWebapp("", new File("web").getAbsolutePath());
        // 添加你的Servlet到Tomcat服务器实例
        tomcat.addServlet(ctx, "MyMvcServlet", new DispatcherServlet());

        // 为你的Servlet添加映射
        ctx.addServletMappingDecoded("/", "MyMvcServlet");
        ctx.addLifecycleListener(new Tomcat.FixContextListener());
        ctx.addApplicationListener(ContextLoaderListener.class.getName());

//        ctx.addParameter("contextConfigLocation","applicationContext.xml");

        Connector connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);
        // 启动Tomcat服务器
        tomcat.start();

        // 等待直到接收到关闭命令
        tomcat.getServer().await();

//        tomcat.start();
//        tomcat.getServer().await();

    }
}
