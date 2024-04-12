package org.homelessYSU.beans.factory.support;



import org.homelessYSU.beans.BeanDefinition;
import org.homelessYSU.beans.BeansException;
import org.homelessYSU.beans.factory.config.AbstractAutowireCapableBeanFactory;
import org.homelessYSU.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory
        implements ConfigurableListableBeanFactory {
    ConfigurableListableBeanFactory parentBeanFactory;

    @Override
    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return (String[])this.beanDefinitionNames.toArray(new String[this.beanDefinitionNames.size()]);
    }

    @Override
    public String[] getBeanNamesForType(Class<?> type) {
        List<String> result = new ArrayList<>();

        for (String beanName : this.beanDefinitionNames) {
            boolean matchFound = false;
            BeanDefinition mbd = this.getBeanDefinition(beanName);
            Class<?> classToMatch = mbd.getClass();
            if (type.isAssignableFrom(classToMatch)) {
                matchFound = true;
            }
            else {
                matchFound = false;
            }

            if (matchFound) {
                result.add(beanName);
            }
        }
        return (String[]) result.toArray();

    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        String[] beanNames = getBeanNamesForType(type);
        Map<String, T> result = new LinkedHashMap<>(beanNames.length);
        for (String beanName : beanNames) {
            Object beanInstance = getBean(beanName);
            result.put(beanName, (T) beanInstance);
        }
        return result;
    }

    public void setParent(ConfigurableListableBeanFactory beanFactory) {
        this.parentBeanFactory = beanFactory;
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        Object result = super.getBean(beanName);
        if(result == null && this.parentBeanFactory == null)
            throw new BeansException("bean:" + beanName + "不存在");
        if (result == null) {
            result = this.parentBeanFactory.getBean(beanName);
        }

        return result;
    }

    @Override
    public String getBeanNameByBeanClass(String beanName) {
        String beanNameByBeanClass = super.getBeanNameByBeanClass(beanName);
        if(beanNameByBeanClass == null || beanNameByBeanClass.isEmpty())
            beanNameByBeanClass = this.parentBeanFactory.getBeanNameByBeanClass(beanName);
        return beanNameByBeanClass;
    }
}