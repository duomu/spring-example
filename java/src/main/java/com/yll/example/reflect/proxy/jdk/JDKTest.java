package com.yll.example.reflect.proxy.jdk;

/**
 * @author：linlin.yang
 * @date：2018/4/12 15:35
 */
public class JDKTest {
    public static void main(String[] args) {
        //代理工厂
        HelloServiceHandler helloHandler = new HelloServiceHandler();
        //真实主题
        HelloService subject = new HelloService();
        //创建真实主题的动态代理（只能通过接口创建真实主题的动态代理）
        IHelloService helloServiceProxy = (IHelloService) helloHandler.getProxy(subject);
        helloServiceProxy.sayHello("HanMeimei");
    }
}
