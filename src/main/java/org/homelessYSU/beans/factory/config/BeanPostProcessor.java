package org.homelessYSU.beans.factory.config;

import org.homelessYSU.beans.BeansException;
import org.homelessYSU.beans.factory.BeanFactory;
/**
 * @description:bean的后置处理
 * @param
 * @return:
 * @author: walkinggo
 * @time: 2024/4/4 14:10
 */
public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

    void setBeanFactory(BeanFactory beanFactory);

}
