package org.homelessYSU.test.test;

import org.homelessYSU.beans.factory.annotation.Autowired;
import org.homelessYSU.beans.factory.annotation.LubboController;
import org.homelessYSU.beans.factory.annotation.LubboRequestBody;
import org.homelessYSU.beans.factory.annotation.LubboRequestParam;
import org.homelessYSU.test.testClassPathXmlApplicationContext.impl.*;
import org.homelessYSU.web.annotation.GetMapping;
import org.homelessYSU.web.annotation.PostMapping;
import org.homelessYSU.web.annotation.RequestMapping;

@LubboController(ControllerUrl = "/helloworld")
public class HelloWorldBean {

    @Autowired
    AnnoInClass anno;

    @GetMapping("/test1")
    public String doTest1() {

        return "test 1, hello world!";
    }
    @GetMapping("/test2")
    public String doTest2() {
        anno.annoTest();
        return "test 2, hello world!";
    }
    @GetMapping("/test3")
    public String doTest3(@LubboRequestBody CheckInClass a, @LubboRequestBody CheckInClass b){
        return a.toString() + b.toString();
    }
    @PostMapping("/test4")
    public String doTest4(@LubboRequestBody CheckInClass a){
        return a.toString();
    }

    @PostMapping("/test5")
    public String doTest5(@LubboRequestBody SStestClassB a){
        return a.toString();
    }

    @PostMapping("/test6")
    public String doTest6(@LubboRequestParam int a,@LubboRequestParam int b){
        return a + " + " + b;
    }
}