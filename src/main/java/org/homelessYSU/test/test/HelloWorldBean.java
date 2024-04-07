package org.homelessYSU.test.test;

import org.homelessYSU.beans.factory.annotation.Autowired;
import org.homelessYSU.test.testClassPathXmlApplicationContext.impl.BaseService;
import org.homelessYSU.web.RequestMapping;

public class HelloWorldBean {

    @Autowired
    BaseService baseservice;

    @RequestMapping("/test1")
    public String doTest1() {
//        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        AService aService;
//        BaseService bService;
//        try {
//            aService = (AService)ctx.getBean("aservice");
//            aService.sayHello();
//
//            bService = (BaseService)ctx.getBean("baseservice");
//            bService.sayHello();
//        } catch (BeansException e) {
//            e.printStackTrace();
//        }
        return "test 1, hello world!";
    }
    @RequestMapping("/test2")
    public String doTest2() {
        return "test 2, hello world!";
    }
    @RequestMapping("/test3")
    public String doTest3() {
        return 5 + " "  + baseservice.getBbs().getAs().getProperty1();
    }
}