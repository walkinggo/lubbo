package org.homelessYSU.test.testClassPathXmlApplicationContext.impl;

import org.homelessYSU.beans.factory.annotation.Autowired;
import org.homelessYSU.beans.factory.annotation.LubboComponent;

@LubboComponent
public class AServiceImpl {
    private String name;
    private int level;
    private String property1;
    private String property2;
    @Autowired
    private BaseService baseservice;

    public BaseService getRef1() {
        return baseservice;
    }

    public void setRef1(BaseService bs) {
        this.baseservice = bs;
    }

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public String getProperty2() {
        return property2;
    }

    public void setProperty2(String property2) {
        this.property2 = property2;
    }

    public AServiceImpl() {
    }

    public AServiceImpl(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public void sayHello() {
        System.out.print(this.property1 + "," + this.property2);
        baseservice.sayHello();
    }
}