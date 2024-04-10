package org.homelessYSU.beans.factory.annotation;

import org.homelessYSU.util.StringUtils;
import org.homelessYSU.web.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Parameter;

public class LubboRequestReader {
    public static void readRequestParamObj(Object[] params, HandlerMethod handlerMethod, HttpServletRequest request) {
        Parameter[] parameters = handlerMethod.getMethod().getParameters();
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(LubboRequestParam.class)) {
                String paramName = parameters[i].getDeclaredAnnotation(LubboRequestParam.class).paramName();
                if (StringUtils.isEmpty(paramName))
                    paramName = parameters[i].getName();

                params[i] = convertStringToType(parameters[i], request.getParameter(paramName));
            }
        }
    }

    public static Object convertStringToType(Parameter parameter, String value) {
        Class<?> type = parameter.getType();

        if (type == int.class || type == Integer.class) {
            return value == null ? null : Integer.valueOf(value);
        } else if (type == long.class || type == Long.class) {
            return value == null ? null : Long.valueOf(value);
        } else if (type == double.class || type == Double.class) {
            return value == null ? null : Double.valueOf(value);
        } else if (type == float.class || type == Float.class) {
            return value == null ? null : Float.valueOf(value);
        } else if (type == byte.class || type == Byte.class) {
            return value == null ? null : Byte.valueOf(value);
        } else if (type == short.class || type == Short.class) {
            return value == null ? null : Short.valueOf(value);
        } else if (type == boolean.class || type == Boolean.class) {
            return value == null ? null : Boolean.valueOf(value);
        } else if (type == char.class || type == Character.class) {
            if (value == null) {
                return null;
            } else if (value.length() == 1) {
                return value.charAt(0);
            } else {
                throw new IllegalArgumentException("Cannot convert String to char: \"" + value + "\"");
            }
        } else {
            throw new IllegalArgumentException("Type " + type + " is not supported");
        }
    }
}
