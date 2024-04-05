package org.homelessYSU.test.testClassPathXmlApplicationContext;

import org.homelessYSU.beans.BeansException;
import org.homelessYSU.context.ClassPathXmlApplicationContext;
import org.homelessYSU.test.testClassPathXmlApplicationContext.impl.AServiceImpl;
import org.homelessYSU.test.testClassPathXmlApplicationContext.impl.BaseService;

public class Test01 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        AServiceImpl aService;
        BaseService bService;
        try {
            aService = (AServiceImpl) ctx.getBean("aservice");
            aService.sayHello();

            bService = (BaseService)ctx.getBean("baseservice");
            bService.sayHello();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
