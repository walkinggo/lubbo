package org.homelessYSU.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.homelessYSU.beans.factory.annotation.LubboRequestReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


public class RequestMappingHandlerAdapter implements HandlerAdapter {
    WebApplicationContext wac = null;

    public RequestMappingHandlerAdapter(WebApplicationContext wac) {
        this.wac = wac;
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
        String json = LubboJsonReader.readJson(request);
        Object[] parms = new Object[handlerMethod.getMethod().getParameters().length];
        LubboJsonReader.getRequestBodyObjs(parms, json, handlerMethod);
        LubboRequestReader.readRequestParamObj(parms, handlerMethod, request);
        Method invocableMethod = handlerMethod.getMethod();
        response.getWriter().append(new ObjectMapper().writeValueAsString(invocableMethod.invoke(handlerMethod.getBean(), parms)));
    }

}