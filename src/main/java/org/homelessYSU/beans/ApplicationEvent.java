package org.homelessYSU.beans;

import java.util.EventObject;
/**
 * @description:简单的应用事件的例子
 * @param
 * @return:
 * @author: walkinggo
 * @time: 2024/4/4 16:13
 */
public class ApplicationEvent extends EventObject {

    private static final long serialVersionUID = 1L;
    protected String msg = null;

    public ApplicationEvent(Object arg0) {
        super(arg0);
        this.msg = arg0.toString();
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}