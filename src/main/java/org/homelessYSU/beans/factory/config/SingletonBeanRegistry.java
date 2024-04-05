package org.homelessYSU.beans.factory.config;

/**
 * @description:该接口抽取了beanFactory在单例情况下的注册、获取、是否包含和获得所有名字的能力。
 * @param
 * @return:
 * @author: walkinggo
 * @time: 2024/4/4 14:35
 */
public interface SingletonBeanRegistry {
    void registerSingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName);

    boolean containsSingleton(String beanName);

    String[] getSingletonNames();

}
