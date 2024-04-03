package org.homelessYSU.factory.config;

import org.homelessYSU.BeansException;
import org.homelessYSU.factory.BeanFactory;

public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(BeanFactory beanFactory) throws BeansException;
}
