package org.homelessYSU.test.test;

import org.homelessYSU.beans.factory.annotation.AOP.BeforeRun;
import org.homelessYSU.beans.factory.annotation.AOP.LubboAop;

@LubboAop
public class AopTest {

    @BeforeRun(method = "doTest4",className = "org.homelessYSU.test.test.HelloWorldBean")
    public void aopT1(){
        System.out.println("in aop");
    }
}
