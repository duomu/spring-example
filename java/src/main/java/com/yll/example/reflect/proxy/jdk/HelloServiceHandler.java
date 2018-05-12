package com.yll.example.reflect.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理
 * @author：linlin.yang
 * @date：2018/4/12 15:25
 */
public class HelloServiceHandler implements InvocationHandler {
    /**
     * 真实主题
     */
    private Object subject;

    /**
     * 根据参数调用执行真实主题的方法
     * @param proxy 代理对象
     * @param method 被调用方法
     * @param args  方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("--------执行前--------");
        Object result = method.invoke(subject, args);
        System.out.println("--------执行后--------");
        return result;
    }

    /**
     * 获取真实主题的动态代理
     * @return
     */
    public Object getProxy(Object subject) {
        this.subject = subject;
        return Proxy.newProxyInstance(this.subject.getClass().getClassLoader(), this.subject.getClass().getInterfaces(), this);
    }
}
