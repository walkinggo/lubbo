package org.homelessYSU.beans.factory.annotation.AOP;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AopBeanInterceptor implements MethodInterceptor {
    private static LubboAOPScanner scanner = new LubboAOPScanner();
    private static Map<String, AopProxyWrapper> methodAopProxyWrapperMap = new HashMap<>();
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        postprocessBeforeService(method);
        Object result = methodProxy.invokeSuper(o, args);
        postprocessAfterService(method);
        return result;
    }
    protected void postprocessBeforeService(Method method){
        if(methodAopProxyWrapperMap.containsKey(LubboAOPScanner.getAopUrl(method.getName(),method.getDeclaringClass().getName()))){
            try {
                methodAopProxyWrapperMap.get(LubboAOPScanner.getAopUrl(method.getName(),method.getDeclaringClass().getName())).getBefchainedInterceptor().intercept();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
    protected void postprocessAfterService(Method method){
        if(methodAopProxyWrapperMap.containsKey(LubboAOPScanner.getAopUrl(method.getName(),method.getDeclaringClass().getName()))){
            try {
                methodAopProxyWrapperMap.get(LubboAOPScanner.getAopUrl(method.getName(),method.getDeclaringClass().getName())).getAftchainedInterceptor().intercept();
            }
            catch (Throwable throwable){
                throwable.printStackTrace();
            }
        }
    }
    public static void AopScan(String url) {
        methodAopProxyWrapperMap = scanner.loadBeanDefinitions(url);
    }
}
