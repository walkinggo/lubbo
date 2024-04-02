package org.homelessYSU.testClassPathXmlApplicationContext.impl;

import org.homelessYSU.testClassPathXmlApplicationContext.AService;

public class AServiceImpl implements AService {
    @Override
    public void sayHello() {
        System.out.println("a service 1 say hello");
    }
}
