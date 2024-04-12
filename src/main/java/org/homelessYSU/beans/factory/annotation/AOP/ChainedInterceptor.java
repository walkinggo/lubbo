package org.homelessYSU.beans.factory.annotation.AOP;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ChainedInterceptor implements MethodInterceptor {
    private List<MethodInterceptor> interceptors = new ArrayList<>();
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result = null;
        for (MethodInterceptor interceptor : interceptors) {
            result = interceptor.intercept(o,method,objects,methodProxy);
        }
        return null;
    }
    public void addInterceptor(MethodInterceptor interceptor) {
        interceptors.add(interceptor);
    }
}
