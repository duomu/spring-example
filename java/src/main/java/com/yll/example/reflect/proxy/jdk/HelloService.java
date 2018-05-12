package com.yll.example.reflect.proxy.jdk;

/**
 * @author：linlin.yang
 * @date：2018/4/12 15:22
 */
public class HelloService implements IHelloService {
    @Override
    public void sayHello(String name) {
        System.out.println("hello " + name);
    }
}
