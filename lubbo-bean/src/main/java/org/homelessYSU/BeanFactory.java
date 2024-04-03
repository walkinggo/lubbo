package org.homelessYSU;

public interface BeanFactory {
    Object getBean(String beanName) throws BeansException;
    boolean containsBean(String name);
    boolean isSingleton(String name);
    boolean isPrototype(String name);
    Class<?> getType(String name);

//    void registerBean(String beanName, Object obj);

}