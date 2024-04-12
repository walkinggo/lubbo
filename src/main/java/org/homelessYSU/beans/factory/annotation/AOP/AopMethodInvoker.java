package org.homelessYSU.beans.factory.annotation.AOP;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class AopMethodInvoker {
    private Method method;
    private Object object;
    private Parameter[] parameters;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Parameter[] getParameters() {
        return parameters;
    }

    public void setParameters(Parameter[] parameters) {
        this.parameters = parameters;
    }

    public Object Invoker(){
        try {
            return method.invoke(object,parameters);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AopMethodInvoker(Method method, Object object, Parameter[] parameters) {
        this.method = method;
        this.object = object;
        this.parameters = parameters;
    }
}
