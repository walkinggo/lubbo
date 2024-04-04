package org.homelessYSU;

import java.util.EventListener;
/**
 * @description:应用监听器，对已发布的事件进行处理
 * @param
 * @return:
 * @author: walkinggo
 * @time: 2024/4/4 16:14
 */
public class ApplicationListener implements EventListener {
    void onApplicationEvent(ApplicationEvent event) {
        System.out.println(event.toString());
    }
}