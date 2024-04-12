package org.homelessYSU.beans.factory.annotation.AOP;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class AopProxyWrapper{

    private Method methodBeProxy;
    private ChainedInterceptor befchainedInterceptor;
    private ChainedInterceptor aftchainedInterceptor;

    public AopProxyWrapper(Method methodBeProxy) {
        setSuperClass(methodBeProxy);
        this.befchainedInterceptor = new ChainedInterceptor();
        this.aftchainedInterceptor = new ChainedInterceptor();
    }

    public void setSuperClass(Method superClass) {
        this.methodBeProxy = superClass;
    }

    public Method getMethodBeProxy() {
        return methodBeProxy;
    }

    public void addProxyBef(Object proxyObj, Method proxyMethod, Parameter[] proxyParameters){
        this.befchainedInterceptor.addInterceptor(new AopMethodInvoker(proxyMethod,proxyObj,proxyParameters));
    }

    public void addProxyAft(Object proxyObj, Method proxyMethod,Parameter[] proxyParameters){
        this.aftchainedInterceptor.addInterceptor(new AopMethodInvoker(proxyMethod,proxyObj,proxyParameters));
    }

    public ChainedInterceptor getBefchainedInterceptor() {
        return befchainedInterceptor;
    }

    public ChainedInterceptor getAftchainedInterceptor() {
        return aftchainedInterceptor;
    }
}