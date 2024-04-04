package org.homelessYSU.factory;

import org.homelessYSU.BeansException;
/**
 * @description:最初始化的bean工厂接口，代表了批量管理bean的能力。
 * @param
 * @return:
 * @author: walkinggo
 * @time: 2024/4/4 14:36
 */
public interface BeanFactory {
    Object getBean(String name) throws BeansException;
    boolean containsBean(String name);
    //void registerBean(String beanName, Object obj);
    boolean isSingleton(String name);
    boolean isPrototype(String name);
    Class<?> getType(String name);

}
