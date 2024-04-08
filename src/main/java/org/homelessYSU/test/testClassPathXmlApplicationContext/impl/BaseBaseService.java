package org.homelessYSU.test.testClassPathXmlApplicationContext.impl;

import org.homelessYSU.beans.factory.annotation.Autowired;
import org.homelessYSU.beans.factory.annotation.LubboComponent;

@LubboComponent
public class BaseBaseService {
    @Autowired
    private AServiceImpl aservice;

    public AServiceImpl getAs() {
        return aservice;
    }
    public void setAs(AServiceImpl as) {
        this.aservice = as;
    }
    public BaseBaseService() {
    }
    public void sayHello() {
        System.out.println("Base Base Service says hello");

    }
}