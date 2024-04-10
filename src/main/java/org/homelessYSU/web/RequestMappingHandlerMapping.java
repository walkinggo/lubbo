package org.homelessYSU.web;

import org.homelessYSU.beans.BeansException;
import org.homelessYSU.beans.factory.annotation.LubboController;
import org.homelessYSU.web.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class RequestMappingHandlerMapping implements HandlerMapping {
    WebApplicationContext wac;
//    private final MappingRegistry mappingRegistry = new MappingRegistry();
    private final Map<String,MappingRegistry> registryMap = new HashMap<>();

    public RequestMappingHandlerMapping(WebApplicationContext wac) {
        this.wac = wac;
        registryMap.put("GET",new MappingRegistry());
        registryMap.put("POST",new MappingRegistry());
        registryMap.put("PUT",new MappingRegistry());
        registryMap.put("DELETE",new MappingRegistry());
        initMapping();
    }

    protected void initMapping() {
        Class<?> clz = null;
        Object obj = null;
        String[] controllerNames = this.wac.getBeanDefinitionNames();
        for (String controllerName : controllerNames) {
            try {
                clz = Class.forName(controllerName);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            try {
                obj = this.wac.getBean(controllerName);
            } catch (BeansException e) {
                e.printStackTrace();
            }
            Method[] methods = clz.getDeclaredMethods();
            if(methods!=null){
                for(Method method : methods){
//                    boolean isRequestMapping = method.isAnnotationPresent(RequestMapping.class);
//                    if (isRequestMapping){
//                        String methodName = method.getName();
//                        String urlmapping = clz.getAnnotation(LubboController.class).ControllerUrl() + method.getAnnotation(RequestMapping.class).value();
//                        this.mappingRegistry.getUrlMappingNames().add(urlmapping);
//                        this.mappingRegistry.getMappingObjs().put(urlmapping, obj);
//                        this.mappingRegistry.getMappingMethods().put(urlmapping, method);
//                        this.mappingRegistry.getMappingMethodNames().put(urlmapping, methodName);
//                        this.mappingRegistry.getMappingClasses().put(urlmapping, clz);
//                    }
                    if(method.isAnnotationPresent(GetMapping.class)){
                        MappingRegistry registry = this.registryMap.get("GET");
                        String methodName = method.getName();
                        String urlmapping = clz.getAnnotation(LubboController.class).ControllerUrl() + method.getAnnotation(GetMapping.class).value();
                        registry.getUrlMappingNames().add(urlmapping);
                        registry.getMappingObjs().put(urlmapping, obj);
                        registry.getMappingMethods().put(urlmapping, method);
                        registry.getMappingMethodNames().put(urlmapping, methodName);
                        registry.getMappingClasses().put(urlmapping, clz);
                    }else if(method.isAnnotationPresent(PostMapping.class)){
                        MappingRegistry registry = this.registryMap.get("POST");
                        String methodName = method.getName();
                        String urlmapping = clz.getAnnotation(LubboController.class).ControllerUrl() + method.getAnnotation(PostMapping.class).value();
                        registry.getUrlMappingNames().add(urlmapping);
                        registry.getMappingObjs().put(urlmapping, obj);
                        registry.getMappingMethods().put(urlmapping, method);
                        registry.getMappingMethodNames().put(urlmapping, methodName);
                        registry.getMappingClasses().put(urlmapping, clz);
                    }else if(method.isAnnotationPresent(DeleteMapping.class)){
                        MappingRegistry registry = this.registryMap.get("DELETE");
                        String methodName = method.getName();
                        String urlmapping = clz.getAnnotation(LubboController.class).ControllerUrl() + method.getAnnotation(DeleteMapping.class).value();
                        registry.getUrlMappingNames().add(urlmapping);
                        registry.getMappingObjs().put(urlmapping, obj);
                        registry.getMappingMethods().put(urlmapping, method);
                        registry.getMappingMethodNames().put(urlmapping, methodName);
                        registry.getMappingClasses().put(urlmapping, clz);
                    }else if(method.isAnnotationPresent(PutMapping.class)){
                        MappingRegistry registry = this.registryMap.get("PUT");
                        String methodName = method.getName();
                        String urlmapping = clz.getAnnotation(LubboController.class).ControllerUrl() + method.getAnnotation(PutMapping.class).value();
                        registry.getUrlMappingNames().add(urlmapping);
                        registry.getMappingObjs().put(urlmapping, obj);
                        registry.getMappingMethods().put(urlmapping, method);
                        registry.getMappingMethodNames().put(urlmapping, methodName);
                        registry.getMappingClasses().put(urlmapping, clz);
                    }
                }
            }
        }

    }


    @Override
    public HandlerMethod getHandler(HttpServletRequest request) throws Exception {
        String sPath = request.getServletPath();

        if (!this.registryMap.get(request.getMethod()).getUrlMappingNames().contains(sPath)) {
            return null;
        }

        Method method = this.registryMap.get(request.getMethod()).getMappingMethods().get(sPath);
        Object obj = this.registryMap.get(request.getMethod()).getMappingObjs().get(sPath);
        Class<?> clz = this.registryMap.get(request.getMethod()).getMappingClasses().get(sPath);
        String methodName = this.registryMap.get(request.getMethod()).getMappingMethodNames().get(sPath);

        HandlerMethod handlerMethod = new HandlerMethod(method, obj, clz, methodName);

        return handlerMethod;
    }

}