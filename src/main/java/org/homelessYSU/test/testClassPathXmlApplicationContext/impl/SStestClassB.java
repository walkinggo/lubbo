package org.homelessYSU.test.testClassPathXmlApplicationContext.impl;

public class SStestClassB {
    private SStestClassA sb;

    public SStestClassA getSb() {
        return sb;
    }

    public void setSb(SStestClassA sb) {
        this.sb = sb;
    }

    @Override
    public String toString() {
        return "SStestClassB{" +
                "sb=" + sb +
                '}';
    }
}
