package org.homelessYSU.web;


import org.homelessYSU.beans.*;
import org.homelessYSU.beans.factory.AbstractApplicationContext;
import org.homelessYSU.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.homelessYSU.beans.factory.annotation.LubboController;
import org.homelessYSU.beans.factory.config.BeanFactoryPostProcessor;
import org.homelessYSU.beans.factory.config.ConfigurableListableBeanFactory;
import org.homelessYSU.beans.factory.support.DefaultListableBeanFactory;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AnnotationConfigWebApplicationContext
        extends AbstractApplicationContext implements WebApplicationContext {
    private WebApplicationContext parentApplicationContext;
    private ServletContext servletContext;
    DefaultListableBeanFactory beanFactory;
    private final List<BeanFactoryPostProcessor> beanFactoryPostProcessors =
            new ArrayList<BeanFactoryPostProcessor>();

    public AnnotationConfigWebApplicationContext(String packageLocation) {
        this(packageLocation, null);
    }

    public AnnotationConfigWebApplicationContext(String packageLocation, WebApplicationContext parentApplicationContext) {
        this.parentApplicationContext = parentApplicationContext;
        this.servletContext = this.parentApplicationContext.getServletContext();
//        URL xmlPath = null;
//        try {
//            xmlPath = this.getServletContext().getResource(packageLocation);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//        List<String> packageNames = XmlScanComponentHelper.getNodeValue(xmlPath);

        List<String> controllerNames = scanPackages(new ArrayList<>(Arrays.asList(packageLocation)));
//        List<String> controllerNames = new ArrayList<>(Arrays.asList(packageLocation));
        DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
        this.beanFactory = bf;
        this.beanFactory.setParent(this.parentApplicationContext.getBeanFactory());
        loadBeanDefinitions(controllerNames);

        if (true) {
            try {
                refresh();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }

    }

    public void loadBeanDefinitions(List<String> controllerNames) {
        for (String controller : controllerNames) {
            String beanID = controller;
            String beanClassName = controller;

            BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);

            this.beanFactory.registerBeanDefinition(beanID, beanDefinition);
        }
    }


    private List<String> scanPackages(List<String> packages) {
        List<String> tempControllerNames = new ArrayList<>();
        for (String packageName : packages) {
            tempControllerNames.addAll(scanPackage(packageName));
        }
        return tempControllerNames;
    }

    private List<String> scanPackage(String packageName){
        List<String> tempControllerNames = new ArrayList<>();
        URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));
        File dir = null;
        try {
            dir = new File(URLDecoder.decode(url.getFile(),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                tempControllerNames.addAll(scanPackage(packageName + "." + file.getName()));
            } else {
                String controllerName = packageName + "." + file.getName().replace(".class", "");
                try {
                    if(Class.forName(controllerName).isAnnotationPresent(LubboController.class))
                        tempControllerNames.add(controllerName);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return tempControllerNames;
    }


    public void setParent(WebApplicationContext parentApplicationContext) {
        this.parentApplicationContext = parentApplicationContext;
        this.beanFactory.setParent(this.parentApplicationContext.getBeanFactory());
    }

    @Override
    public ServletContext getServletContext() {
        return this.servletContext;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        this.getApplicationEventPublisher().publishEvent(event);
    }

    @Override
    public void addApplicationListener(ApplicationListener listener) {
        this.getApplicationEventPublisher().addApplicationListener(listener);
    }

    @Override
    public void registerListeners() {
        ApplicationListener listener = new ApplicationListener();
        this.getApplicationEventPublisher().addApplicationListener(listener);
    }

    @Override
    public void initApplicationEventPublisher() {
        ApplicationEventPublisher aep = new SimpleApplicationEventPublisher();
        this.setApplicationEventPublisher(aep);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory bf) {
    }

    @Override
    public void registerBeanPostProcessors(ConfigurableListableBeanFactory bf) {
        this.beanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());
    }

    @Override
    public void onRefresh() {
        this.beanFactory.refresh();
    }

    @Override
    public void finishRefresh() {
        // TODO Auto-generated method stub

    }

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException {
        return this.beanFactory;
    }

    @Override
    public String getBeanNameByBeanClass(String beanName) {
        return this.parentApplicationContext.getBeanNameByBeanClass(beanName);
    }
}