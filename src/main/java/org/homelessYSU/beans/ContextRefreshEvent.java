package org.homelessYSU.beans;

/**
 * @description:上下文事件
 * @param
 * @return:
 * @author: walkinggo
 * @time: 2024/4/4 16:15
 */
public class ContextRefreshEvent extends ApplicationEvent{

    private static final long serialVersionUID = 1L;

    public ContextRefreshEvent(Object arg0) {
        super(arg0);
    }

    public String toString() {
        return this.msg;
    }

}