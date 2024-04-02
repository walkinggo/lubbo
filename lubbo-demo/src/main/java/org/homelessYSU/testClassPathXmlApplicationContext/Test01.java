package org.homelessYSU.testClassPathXmlApplicationContext;

import org.homelessYSU.BeansException;
import org.homelessYSU.ClassPathXmlApplicationContext;

public class Test01 {
    public static void main(String[] args) throws BeansException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        AService aservice = (AService) ctx.getBean("aservice");
        aservice.sayHello();
    }
}
