package com.yll.example.reflect.proxy.cglib;

import com.yll.example.reflect.proxy.jdk.HelloService;

/**
 * @author：linlin.yang
 * @date：2018/4/12 15:58
 */
public class CglibTest {
    public static void main(String[] args) {
        //代理工厂
        HelloServiceCglib helloServiceCglib = new HelloServiceCglib();
        //真实主题
        HelloService helloService = new HelloService();
        //创建真实主题的动态代理（不必通过接口创建）
        HelloService proxy = (HelloService) helloServiceCglib.getProxy(helloService);
        proxy.sayHello("Lilei");
    }
}
