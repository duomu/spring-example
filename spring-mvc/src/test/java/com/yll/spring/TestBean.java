package com.yll.spring;

/**
 * @author：linlin.yang
 * @date：2018/5/22 14:28
 */
public class TestBean {
    private String testStr = "testStr";

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    public void test() {
        System.out.println("TestBean.test().....");
    }
}
