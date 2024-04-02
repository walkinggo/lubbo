package org.homelessYSU;

/**
 * @param
 * @description:通过XML文件获得的xml上下文信息，并实例化beans.
 * @return: 通过XML文件所获得的xml上下文信息
 * @author: walkinggo
 * @time: 2024/4/2 15:06
 */
public class ClassPathXmlApplicationContext implements BeanFactory {

    BeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String fileName) {
        Resource resource = new ClassPathXmlResource(fileName);
        SimpleBeanFactory beanFactory = new SimpleBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
        this.beanFactory = beanFactory;
    }

    public void publishEvent(ApplicationEvent event) {
    }

    public boolean isSingleton(String name) {
        return false;
    }

    public boolean isPrototype(String name) {
        return false;
    }

    public Class getType(String name) {
        return null;
    }


    @Override
    public Object getBean(String beanName) throws BeansException {
        return this.beanFactory.getBean(beanName);
    }



    @Override
    public boolean containsBean(String name) {
        return this.beanFactory.containsBean(name);
    }

}
