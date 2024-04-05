package org.homelessYSU.web;

import javax.servlet.http.HttpServletRequest;

public interface HandlerMapping {
    HandlerMethod getHandler(HttpServletRequest request) throws Exception;
}