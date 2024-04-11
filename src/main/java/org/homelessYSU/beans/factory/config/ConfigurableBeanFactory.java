package org.homelessYSU.beans.factory.config;

import org.homelessYSU.beans.factory.BeanFactory;

/**
 * @description:维护bean之间的依赖关系和支持bean处理器
 * @param
 * @return:
 * @author: walkinggo
 * @time: 2024/4/4 14:06
 */
public interface ConfigurableBeanFactory extends BeanFactory,SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    void addBeforeInitializaBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    int getBeanPostProcessorCount();

    void registerDependentBean(String beanName, String dependentBeanName);

    String[] getDependentBeans(String beanName);

    String[] getDependenciesForBean(String beanName);

}