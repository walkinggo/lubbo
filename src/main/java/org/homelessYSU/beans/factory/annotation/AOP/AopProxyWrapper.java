package org.homelessYSU.beans.factory.annotation.AOP;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class AopProxyWrapper extends Enhancer {

    private Class<?> Superclass;
    private ChainedInterceptor chainedInterceptor;

    public AopProxyWrapper(Class<?> superclass) {
        setSuperClass(superclass);
        this.chainedInterceptor = new ChainedInterceptor();
        super.setCallback(chainedInterceptor);
    }

    public void setSuperClass(Class<?> superClass) {
        super.setSuperclass(superClass);
        this.Superclass = superClass;
    }

    public Class<?> getSuperclass() {
        return Superclass;
    }

    public void addProxyBef(Object proxyObj, Method proxyMethod,Object[] proxyParameters,Method methodP){
        getChainedInterceptor().addInterceptor(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy proxy) throws Throwable {
                if(method.getName().equals(methodP)){
                    proxyMethod.invoke(proxyObj,proxyParameters);
                }
                return proxy.invokeSuper(o,objects);
            }
        });
    }

    public void addProxyAft(Object proxyObj, Method proxyMethod,Object[] proxyParameters,Method methodP){
        getChainedInterceptor().addInterceptor(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy proxy) throws Throwable {

                Object aSuper = proxy.invokeSuper(o, objects);
                if(method.getName().equals(methodP)){
                    proxyMethod.invoke(proxyObj,proxyParameters);
                }
                return aSuper;
            }
        });
    }


    public Object create() {
        super.setCallback(this.chainedInterceptor);
        return super.create();
    }


    public ChainedInterceptor getChainedInterceptor() {
        return this.chainedInterceptor;
    }
}