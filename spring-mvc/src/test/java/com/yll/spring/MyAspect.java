package com.yll.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @author：linlin.yang
 * @date：2018/5/22 14:23
 */
@Aspect
public class MyAspect {
    @Pointcut("execution(* *.*(..))")
    public void test() {

    }

    @Before("test()")
    public void before() {
        System.out.println("before.........");
    }

    @After("test()")
    public void after() {
        System.out.println("after..........");
    }

    @Around("test()")
    public Object around(ProceedingJoinPoint p) {
        System.out.println("around before........");
        Object o = null;
        try {
            o = p.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        System.out.println("around after.........");
        return o;
    }
}
