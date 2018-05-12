package com.yll.example.reflect.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;

/**
 * @author：linlin.yang
 * @date：2018/4/12 15:50
 */
public class HelloServiceCglib implements MethodInterceptor {
    private Object subject;

    /**
     * 根据参数调用执行真实主题的方法
     * @param subject
     * @param method
     * @param args
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object subject, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("--------执行前--------");
        Object result = methodProxy.invokeSuper(subject, args);
        System.out.println("--------执行后--------");
        return result;
    }

    /**
     * 创建真实主题的代理对象
     * @param subject
     * @return
     */
    public Object getProxy(Object subject) {
        this.subject = subject;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.subject.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }
}
