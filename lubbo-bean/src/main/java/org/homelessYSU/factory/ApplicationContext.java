package org.homelessYSU.factory;

import org.homelessYSU.ApplicationEventPublisher;
import org.homelessYSU.BeansException;
import org.homelessYSU.env.Environment;
import org.homelessYSU.env.EnvironmentCapable;
import org.homelessYSU.factory.ListableBeanFactory;
import org.homelessYSU.factory.config.BeanFactoryPostProcessor;
import org.homelessYSU.factory.config.ConfigurableBeanFactory;
import org.homelessYSU.factory.config.ConfigurableListableBeanFactory;

public interface ApplicationContext
        extends EnvironmentCapable, ListableBeanFactory, ConfigurableBeanFactory, ApplicationEventPublisher{
    String getApplicationName();
    long getStartupDate();
    ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException;
    void setEnvironment(Environment environment);
    Environment getEnvironment();
    void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor);
    void refresh() throws BeansException, IllegalStateException;
    void close();
    boolean isActive();

}