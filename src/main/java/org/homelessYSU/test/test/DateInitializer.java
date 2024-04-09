package org.homelessYSU.test.test;

import org.homelessYSU.beans.factory.annotation.LubboComponent;
import org.homelessYSU.web.WebBindingInitializer;
import org.homelessYSU.web.WebDataBinder;

import java.util.Date;


@LubboComponent
public class DateInitializer implements WebBindingInitializer {
    @Override
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(Date.class,"yyyy-MM-dd", false));
    }
}