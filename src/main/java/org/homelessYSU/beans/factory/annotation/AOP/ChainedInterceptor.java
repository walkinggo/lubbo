package org.homelessYSU.beans.factory.annotation.AOP;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ChainedInterceptor {
    private List<AopMethodInvoker> interceptors = new ArrayList<>();

    public Object intercept() throws Throwable {
        Object result = null;
        for (AopMethodInvoker interceptor : interceptors) {
            result = interceptor.Invoker();
        }
        return null;
    }
    public void addInterceptor(AopMethodInvoker interceptor) {
        interceptors.add(interceptor);
    }
}
