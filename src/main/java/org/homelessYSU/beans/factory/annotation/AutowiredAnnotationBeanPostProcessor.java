package org.homelessYSU.beans.factory.annotation;

import org.homelessYSU.beans.factory.config.BeanPostProcessor;
import org.homelessYSU.beans.BeansException;
import org.homelessYSU.beans.factory.BeanFactory;

import java.lang.reflect.Field;

public class AutowiredAnnotationBeanPostProcessor implements BeanPostProcessor {
    private BeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Object result = bean;

        Class<?> clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if(fields!=null){
            for(Field field : fields){
                boolean isAutowired = field.isAnnotationPresent(Autowired.class);
                if(isAutowired){
                    String fieldName = field.getName();
                    Object autowiredObj = this.getBeanFactory().getBean(this.getBeanFactory().getBeanNameByBeanClass(field.getType().getName()));
                    if(autowiredObj == null)
                        autowiredObj = this.getBeanFactory().getBean(this.getBeanFactory().getBeanNameByBeanClass(field.getType().getInterfaces()[0].getName()));
                    try {
                        field.setAccessible(true);
                        field.set(bean, autowiredObj);
                        System.out.println("autowire " + fieldName + " for bean " + beanName);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        return result;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // TODO Auto-generated method stub
        return null;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }


    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }


}