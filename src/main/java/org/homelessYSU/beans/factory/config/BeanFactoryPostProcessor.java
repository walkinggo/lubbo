package org.homelessYSU.beans.factory.config;

import org.homelessYSU.beans.BeansException;
import org.homelessYSU.beans.factory.BeanFactory;

/**
 * @description:设立beanFactory处理的规范，一般在BeanFactory建立之后进行处理。
 * @param
 * @return:
 * @author: walkinggo
 * @time: 2024/4/4 16:02
 */
public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(BeanFactory beanFactory) throws BeansException;
}
