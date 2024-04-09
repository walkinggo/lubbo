package org.homelessYSU.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.homelessYSU.beans.BeansException;
import org.homelessYSU.beans.factory.annotation.RequestBodyHandler;
import org.homelessYSU.test.test.DateInitializer;
import org.homelessYSU.test.testClassPathXmlApplicationContext.impl.CheckInClass;


import java.io.BufferedReader;
import java.io.DataInput;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class RequestMappingHandlerAdapter implements HandlerAdapter {
    WebApplicationContext wac = null;
    private WebBindingInitializer webBindingInitializer = null;

    public RequestMappingHandlerAdapter(WebApplicationContext wac) {
        this.wac = wac;
        //            this.webBindingInitializer = (WebBindingInitializer) this.wac.getBean(DateInitializer.class.getName());
        this.webBindingInitializer = new DateInitializer();
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        return handleInternal(request, response, (HandlerMethod) handler);
    }

    private ModelAndView handleInternal(HttpServletRequest request, HttpServletResponse response,
                                        HandlerMethod handler) {
        ModelAndView mv = null;

        try {
            invokeHandlerMethod(request, response, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mv;

    }

    protected void invokeHandlerMethod(HttpServletRequest request,
                                       HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {


        WebDataBinderFactory binderFactory = new WebDataBinderFactory();

        Parameter[] methodParameters = handlerMethod.getMethod().getParameters();
        Object[] methodParamObjs = new Object[methodParameters.length];
        StringBuilder jsonStringBuilder = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonStringBuilder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String json = jsonStringBuilder.toString();
        Object[] parObjs;
        parObjs = new Object[methodParameters.length];
        for (int i = 0; i < parObjs.length; i++) {
            parObjs[i] = methodParameters[i].getType().newInstance();
        }
        if(json.charAt(0) == '{'){
            parObjs[0] = new ObjectMapper().readValue(json, parObjs[0].getClass());
        }
        else{
            JsonNode jsonNode = new ObjectMapper().readTree(json);
            int i = 0;
            for (JsonNode node : jsonNode) {
                parObjs[i] = new ObjectMapper().treeToValue(node,parObjs[i].getClass());
                i ++;
            }
        }
        int i = 0;
        for (Parameter methodParameter : methodParameters) {
            methodParamObjs[i] = parObjs[i];
            i++;
        }

        Method invocableMethod = handlerMethod.getMethod();
        Object returnobj = invocableMethod.invoke(handlerMethod.getBean(), methodParamObjs);

        response.getWriter().append(returnobj.toString());

    }



}