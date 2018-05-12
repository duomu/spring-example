package com.yll.example.reflect.proxy.jdk;


/**
 * JDK静态代理
 * @author：linlin.yang
 * @date：2018/4/12 15:23
 */
public class HelloServiceProxyStatic implements IHelloService {
    private IHelloService helloService;

    @Override
    public void sayHello(String name) {
        helloService.sayHello(name);
    }
}
