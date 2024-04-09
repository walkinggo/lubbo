package org.homelessYSU.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    public static Object[] getParObjs(String json, Parameter[] methodParameters) throws JsonProcessingException, IllegalAccessException, InstantiationException {
        Object[] parObjs = new Object[methodParameters.length];
        for (int i = 0; i < parObjs.length; i++) {
            parObjs[i] = methodParameters[i].getType().newInstance();
        }
        if (parObjs.length == 1) {
            parObjs[0] = om.readValue(json, parObjs[0].getClass());
        } else {
            JsonNode jsonNode = om.readTree(json);
            int i = 0;
            for (JsonNode node : jsonNode) {
                parObjs[i] = om.treeToValue(node, parObjs[i].getClass());
                i++;
            }
        }
        return parObjs;
    }

}
