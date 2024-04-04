package org.homelessYSU;

import org.homelessYSU.factory.AbstractApplicationContext;
import org.homelessYSU.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.homelessYSU.factory.config.BeanFactoryPostProcessor;
import org.homelessYSU.factory.config.ConfigurableListableBeanFactory;
import org.homelessYSU.factory.support.DefaultListableBeanFactory;
import org.homelessYSU.factory.xml.XmlBeanDefinitionReader;

import java.util.ArrayList;
import java.util.List;

/**
 * @param
 * @description:通过XML文件获得的xml上下文信息，并实例化beans.
 * @return: 通过XML文件所获得的xml上下文信息
 * @author: walkinggo
 * @time: 2024/4/2 15:06
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {
    DefaultListableBeanFactory beanFactory;
    private final List<BeanFactoryPostProcessor> beanFactoryPostProcessors =
            new ArrayList<BeanFactoryPostProcessor>();

    public ClassPathXmlApplicationContext(String fileName) {
        this(fileName, true);
    }

    public ClassPathXmlApplicationContext(String fileName, boolean isRefresh) {
        Resource res = new ClassPathXmlResource(fileName);
        DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(bf);
        reader.loadBeanDefinitions(res);

        this.beanFactory = bf;

        if (isRefresh) {
            try {
                refresh();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }
    }

    //    @Override
    protected void registerListeners() {
        ApplicationListener listener = new ApplicationListener();
        this.getApplicationEventPublisher().addApplicationListener(listener);

    }

    @Override
    protected void initApplicationEventPublisher() {
        ApplicationEventPublisher aep = new SimpleApplicationEventPublisher();
        this.setApplicationEventPublisher(aep);
    }

    @Override
    protected void postProcessBeanFactory(ConfigurableListableBeanFactory bf) {
    }

    //    @Override
    // 在这里注册了autowired处理器，这样就可以自动填充@autowired标注的东西了。
    protected void registerBeanPostProcessors(ConfigurableListableBeanFactory bf) {
        this.beanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());
    }

    //    @Override
    protected void onRefresh() {
        this.beanFactory.refresh();
    }

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException {
        return this.beanFactory;
    }

    @Override
    public void addApplicationListener(ApplicationListener listener) {
        this.getApplicationEventPublisher().addApplicationListener(listener);

    }

    @Override
    protected void finishRefresh() {
        publishEvent(new ContextRefreshEvent("Context Refreshed..."));

    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        this.getApplicationEventPublisher().publishEvent(event);

    }


}