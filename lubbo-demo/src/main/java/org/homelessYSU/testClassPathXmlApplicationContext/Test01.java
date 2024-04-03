package org.homelessYSU.testClassPathXmlApplicationContext;

import org.homelessYSU.BeansException;
import org.homelessYSU.ClassPathXmlApplicationContext;
import org.homelessYSU.testClassPathXmlApplicationContext.impl.BaseService;

public class Test01 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        AService aService;
        BaseService bService;
        try {
            aService = (AService)ctx.getBean("aservice");
            aService.sayHello();

            bService = (BaseService)ctx.getBean("baseservice");
            bService.sayHello();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
