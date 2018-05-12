package com.yll.example.jvm;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * -XX:PermSize=10M -XX:MaxPermSize=10M
 * @author：linlin.yang
 * @date：2018/3/28 16:47
 */
public class JavaMethodAreaOOM {
    static class OOMObject {

    }
    public static void main(String[] args) {
        while (true) {
            //CGLib字节码技术动态生成Class
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseFactory(false);
            enhancer.setCallback(new MethodInterceptor() {
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, objects);
                }
            });

            enhancer.create();
        }
    }
}
