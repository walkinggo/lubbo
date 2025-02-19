package org.homelessYSU.beans.factory.annotation.AOP;

import org.homelessYSU.beans.BeansException;
import org.homelessYSU.beans.factory.BeanFactory;
import org.homelessYSU.beans.factory.config.BeanPostProcessor;


public class AopBeanPostProcessor implements BeanPostProcessor {
    private BeanFactory beanFactory;
    private static AopBeanCreator creator = new AopBeanCreator();
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return creator.createAopBean(bean);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
    public static void AopScan(String url) {
        creator.AopScan(url);
    }

}
