package org.homelessYSU.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;


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

        Parameter[] methodParameters = handlerMethod.getMethod().getParameters();
        String json = LubboJsonReader.readJson(request);
        Object[] parms = LubboJsonReader.getParObjs(json, methodParameters);
        Method invocableMethod = handlerMethod.getMethod();
        Object returnobj = invocableMethod.invoke(handlerMethod.getBean(), parms);
        response.getWriter().append(returnobj.toString());

    }

}