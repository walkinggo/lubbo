package org.homelessYSU.factory.annotation;

import org.homelessYSU.BeanPostProcessor;
import org.homelessYSU.BeansException;
import org.homelessYSU.factory.BeanFactory;

import java.lang.reflect.Field;

public class AutowiredAnnotationBeanPostProcessor implements BeanPostProcessor {

    private BeanFactory beanFactory;

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Object result = bean;
        Class<?> aClass = bean.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            boolean annotationPresent = field.isAnnotationPresent(Autowired.class);
            if(annotationPresent){
                String fieldName = field.getName();
                Object autowiredObj = this.getBeanFactory().getBean(fieldName);
                try {
                    field.setAccessible(true);
                    field.set(bean,autowiredObj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}
