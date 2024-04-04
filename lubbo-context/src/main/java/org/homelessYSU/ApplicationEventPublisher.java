package org.homelessYSU;
/**
 * @description:规定了应用事件发布的规范
 * @param
 * @return:
 * @author: walkinggo
 * @time: 2024/4/4 16:14
 */
public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
    void addApplicationListener(ApplicationListener listener);
}