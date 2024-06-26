package org.homelessYSU.web;

import org.homelessYSU.context.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;


public class XmlWebApplicationContext
        extends ClassPathXmlApplicationContext implements WebApplicationContext{
    private ServletContext servletContext;

    public XmlWebApplicationContext(String fileName,String packageLocation) {
        super(fileName,packageLocation);
    }

    @Override
    public ServletContext getServletContext() {
        return this.servletContext;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}