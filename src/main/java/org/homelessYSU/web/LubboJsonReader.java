package org.homelessYSU.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.homelessYSU.beans.factory.annotation.LubboRequestBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.lang.reflect.Parameter;

public class LubboJsonReader {
    private static ObjectMapper om = new ObjectMapper();

    public static String readJson(HttpServletRequest request) {
        StringBuilder jsonStringBuilder = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonStringBuilder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonStringBuilder.toString();
    }

    public static Object[] getRequestBodyObjs(Object[] parObjs,String json, HandlerMethod handlerMethod) throws JsonProcessingException, IllegalAccessException, InstantiationException {
        Parameter[] methodParameters = handlerMethod.getMethod().getParameters();
        if (parObjs.length == 1) {
            if (methodParameters[0].isAnnotationPresent(LubboRequestBody.class))
                parObjs[0] = om.readValue(json, methodParameters[0].getType());
        } else {
            JsonNode jsonNode = om.readTree(json);
            int i = 0;
            for (int j = 0; j < parObjs.length; j++) {
                if (methodParameters[j].isAnnotationPresent(LubboRequestBody.class))
                    parObjs[j] = om.treeToValue(jsonNode.get(i++), methodParameters[j].getType());
            }
        }
        return parObjs;
    }
}
