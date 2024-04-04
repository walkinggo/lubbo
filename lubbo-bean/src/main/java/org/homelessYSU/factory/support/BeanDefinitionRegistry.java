package org.homelessYSU.factory.support;

import org.homelessYSU.BeanDefinition;
/**
 * @description:规定了beanDefinition注册的接口规范
 * @param
 * @return:
 * @author: walkinggo
 * @time: 2024/4/4 15:56
 */
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String name, BeanDefinition bd);
    void removeBeanDefinition(String name);
    BeanDefinition getBeanDefinition(String name);
    boolean containsBeanDefinition(String name);
}

