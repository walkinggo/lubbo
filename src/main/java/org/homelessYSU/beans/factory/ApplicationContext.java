package org.homelessYSU.beans.factory;

import org.homelessYSU.beans.ApplicationEventPublisher;
import org.homelessYSU.beans.BeansException;
import org.homelessYSU.beans.factory.config.ConfigurableBeanFactory;
import org.homelessYSU.beans.factory.config.ConfigurableListableBeanFactory;
import org.homelessYSU.env.Environment;
import org.homelessYSU.env.EnvironmentCapable;
import org.homelessYSU.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @description:规定了上下文的规范。
 * @param
 * @return:
 * @author: walkinggo
 * @time: 2024/4/4 16:10
 */
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