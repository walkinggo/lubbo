package org.homelessYSU.beans.factory.config;



import org.homelessYSU.beans.BeansException;
import org.homelessYSU.beans.factory.support.AbstractBeanFactory;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractAutowireCapableBeanFactory
        extends AbstractBeanFactory implements AutowireCapableBeanFactory{
    private final List<BeanPostProcessor> BeforeInitializationbeanPostProcessors = new ArrayList<BeanPostProcessor>();
    private final List<BeanPostProcessor> AfterInitializationbeanPostProcessors = new ArrayList<BeanPostProcessor>();

    public void addBeforeInitializaBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.BeforeInitializationbeanPostProcessors.remove(beanPostProcessor);
        this.BeforeInitializationbeanPostProcessors.add(beanPostProcessor);
    }
    public void addAfterInitializaBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.AfterInitializationbeanPostProcessors.remove(beanPostProcessor);
        this.AfterInitializationbeanPostProcessors.add(beanPostProcessor);
    }
    public int getBeanPostProcessorCount() {
        return this.BeforeInitializationbeanPostProcessors.size();
    }
    public List<BeanPostProcessor> getBeforeInitializationbeanPostProcessors() {
        return this.BeforeInitializationbeanPostProcessors;
    }
    public List<BeanPostProcessor> getAfterInitializationbeanPostProcessors() {
        return this.AfterInitializationbeanPostProcessors;
    }

    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName)
            throws BeansException {

        Object result = existingBean;
        for (BeanPostProcessor beanProcessor : getBeforeInitializationbeanPostProcessors()) {
            beanProcessor.setBeanFactory(this);
            result = beanProcessor.postProcessBeforeInitialization(result, beanName);
            if (result == null) {
                return result;
            }
        }
        return result;
    }

    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName)
            throws BeansException {

        Object result = existingBean;
        for (BeanPostProcessor beanProcessor : getAfterInitializationbeanPostProcessors()) {
            result = beanProcessor.postProcessAfterInitialization(result, beanName);
            if (result == null) {
                return result;
            }
        }
        return result;
    }
}