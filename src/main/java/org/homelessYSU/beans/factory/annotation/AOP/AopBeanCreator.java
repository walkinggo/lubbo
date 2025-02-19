package org.homelessYSU.beans.factory.annotation.AOP;

import net.sf.cglib.proxy.Enhancer;

public class AopBeanCreator {
    public static AopBeanInterceptor interceptor = new AopBeanInterceptor();
    public Object createAopBean(Object bean) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(bean.getClass());
        enhancer.setCallback(interceptor);
        Object proxy = enhancer.create();
        return proxy;
    }
    public static void AopScan(String url) {
        interceptor.AopScan(url);
    }
}
