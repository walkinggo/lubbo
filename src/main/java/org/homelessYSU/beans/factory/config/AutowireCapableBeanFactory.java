package org.homelessYSU.beans.factory.config;

import org.homelessYSU.beans.BeansException;
import org.homelessYSU.beans.factory.BeanFactory;

/**
 * @description:自动处理接口。实现了这个接口的beanFactory具备了在bean实例化之后进行后置处理的操作。
 * @param
 * @return:
 * @author: walkinggo
 * @time: 2024/4/4 14:03
 */
public interface AutowireCapableBeanFactory  extends BeanFactory {
    int AUTOWIRE_NO = 0;
    int AUTOWIRE_BY_NAME = 1;
    int AUTOWIRE_BY_TYPE = 2;
    // 在bean初始化之前进行处理
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName)
            throws BeansException;
    // 在bean初始化之后进行处理
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName)
            throws BeansException;

}