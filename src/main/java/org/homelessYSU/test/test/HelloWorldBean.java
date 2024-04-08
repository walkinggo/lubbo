package org.homelessYSU.test.test;

import org.homelessYSU.beans.factory.annotation.Autowired;
import org.homelessYSU.beans.factory.annotation.LubboController;
import org.homelessYSU.test.testClassPathXmlApplicationContext.impl.AnnoInClass;
import org.homelessYSU.test.testClassPathXmlApplicationContext.impl.BaseService;
import org.homelessYSU.web.RequestMapping;

@LubboController(ControllerUrl = "/helloworld")
public class HelloWorldBean {

    @Autowired
    AnnoInClass anno;

    @RequestMapping("/test1")
    public String doTest1() {

        return "test 1, hello world!";
    }
    @RequestMapping("/test2")
    public String doTest2() {
        anno.annoTest();
        return "test 2, hello world!";
    }
}