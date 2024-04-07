package org.homelessYSU.web;

public interface ViewResolver {
    View resolveViewName(String viewName) throws Exception;

}