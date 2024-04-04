package org.homelessYSU;

import java.util.ArrayList;
import java.util.List;
/**
 * @description:简单的事件发布器
 * @param
 * @return:
 * @author: walkinggo
 * @time: 2024/4/4 16:15
 */
public class SimpleApplicationEventPublisher implements ApplicationEventPublisher{
    List<ApplicationListener> listeners = new ArrayList<>();

    @Override
    public void publishEvent(ApplicationEvent event) {
        for (ApplicationListener listener : listeners) {
            listener.onApplicationEvent(event);
        }
    }

    @Override
    public void addApplicationListener(ApplicationListener listener) {
        this.listeners.add(listener);
    }


}