package org.homelessYSU.test.testClassPathXmlApplicationContext.impl;

import org.homelessYSU.beans.factory.annotation.Autowired;
import org.homelessYSU.beans.factory.annotation.LubboComponent;

@LubboComponent
public class BaseService {
    @Autowired
    private BaseBaseService bbs;


    public BaseBaseService getBbs() {
        return bbs;
    }
    public void setBbs(BaseBaseService bbs) {
        this.bbs = bbs;
    }
    public BaseService() {
    }
    public void sayHello() {
        System.out.print("Base Service says hello");
        bbs.sayHello();
    }
}