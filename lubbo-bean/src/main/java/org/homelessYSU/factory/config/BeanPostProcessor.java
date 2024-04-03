package org.homelessYSU.factory.config;

import org.homelessYSU.BeansException;
import org.homelessYSU.factory.BeanFactory;

public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

    void setBeanFactory(BeanFactory beanFactory);

}
