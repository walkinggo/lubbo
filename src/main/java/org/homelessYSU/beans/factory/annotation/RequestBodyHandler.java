package org.homelessYSU.beans.factory.annotation;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.connector.CoyoteReader;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class RequestBodyHandler {
    public static List<Object> getServletJson(HttpServletRequest request) {
        return null;
    }
}
