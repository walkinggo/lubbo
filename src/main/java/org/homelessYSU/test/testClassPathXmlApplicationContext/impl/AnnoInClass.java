package org.homelessYSU.test.testClassPathXmlApplicationContext.impl;

import org.homelessYSU.beans.factory.annotation.LubboComponent;

@LubboComponent
public class AnnoInClass {
    public String annoTest(){
        System.out.println("in annoTest.it works");
        return "anno";
    }
}
