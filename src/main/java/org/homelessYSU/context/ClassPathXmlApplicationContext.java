package org.homelessYSU.context;

import org.homelessYSU.beans.*;
import org.homelessYSU.beans.factory.AbstractApplicationContext;
import org.homelessYSU.beans.factory.annotation.AOP.LubboAOPScanner;
import org.homelessYSU.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.homelessYSU.beans.factory.annotation.LubboComponentScanner;
import org.homelessYSU.beans.factory.config.BeanFactoryPostProcessor;
import org.homelessYSU.beans.factory.config.ConfigurableListableBeanFactory;
import org.homelessYSU.beans.factory.support.DefaultListableBeanFactory;
import org.homelessYSU.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @param
 * @description:通过XML文件获得的xml上下文信息，并实例化beans.
 * @return: 通过XML文件所获得的xml上下文信息
 * @author: walkinggo
 * @time: 2024/4/2 15:06
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {
    DefaultListableBeanFactory beanFactory;

    private final List<BeanFactoryPostProcessor> beanFactoryPostProcessors =
            new ArrayList<BeanFactoryPostProcessor>();


    public ClassPathXmlApplicationContext(String fileName, String packageLocation) {
        this(fileName, true, packageLocation);
    }

    public void loadComponentBean(String packageLocation) {
        LubboComponentScanner scanner = new LubboComponentScanner(beanFactory);
        scanner.loadBeanDefinitions(packageLocation);
    }


    public ClassPathXmlApplicationContext(String fileName, boolean isRefresh, String packageLocation) {
        DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
        if (fileName != null) {
            Resource res = new ClassPathXmlResource(fileName);
            XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(bf);
            reader.loadBeanDefinitions(res);
        }


        this.beanFactory = bf;
        this.loadComponentBean(packageLocation);

        if (isRefresh) {
            try {
                refresh();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }
    }

    //    @Override
    protected void registerListeners() {
        ApplicationListener listener = new ApplicationListener();
        this.getApplicationEventPublisher().addApplicationListener(listener);

    }

    @Override
    protected void initApplicationEventPublisher() {
        ApplicationEventPublisher aep = new SimpleApplicationEventPublisher();
        this.setApplicationEventPublisher(aep);
    }

    @Override
    protected void postProcessBeanFactory(ConfigurableListableBeanFactory bf) {
    }

    //    @Override
    // 在这里注册了autowired处理器，这样就可以自动填充@autowired标注的东西了。
    protected void registerBeanPostProcessors(ConfigurableListableBeanFactory bf) {
        this.beanFactory.addBeforeInitializaBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());
    }

    //    @Override
    protected void onRefresh() {
        this.beanFactory.refresh();
    }

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException {
        return this.beanFactory;
    }

    @Override
    public void addApplicationListener(ApplicationListener listener) {
        this.getApplicationEventPublisher().addApplicationListener(listener);

    }

    @Override
    protected void finishRefresh() {
        publishEvent(new ContextRefreshEvent("Context Refreshed..."));

    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        this.getApplicationEventPublisher().publishEvent(event);

    }


    @Override
    public String getBeanNameByBeanClass(String beanName) {
        return null;
    }
}