package org.homelessYSU.factory;

import org.homelessYSU.BeansException;

import java.util.Map;
/**
 * @description:规定了对beanDefintion处理的规范
 * @param
 * @return:
 * @author: walkinggo
 * @time: 2024/4/4 16:11
 */
public interface ListableBeanFactory extends BeanFactory {

    boolean containsBeanDefinition(String beanName);

    int getBeanDefinitionCount();

    String[] getBeanDefinitionNames();

    String[] getBeanNamesForType(Class<?> type);

    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

}