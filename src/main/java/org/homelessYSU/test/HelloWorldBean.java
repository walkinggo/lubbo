package org.homelessYSU.test;

import org.homelessYSU.beans.BeansException;
import org.homelessYSU.context.ClassPathXmlApplicationContext;
import org.homelessYSU.testClassPathXmlApplicationContext.AService;
import org.homelessYSU.testClassPathXmlApplicationContext.impl.BaseService;
import org.homelessYSU.web.RequestMapping;

public class HelloWorldBean {
    @RequestMapping("/test1")
    public String doTest1() {
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
        return "test 1, hello world!";
    }
    @RequestMapping("/test2")
    public String doTest2() {
        return "test 2, hello world!";
    }
}