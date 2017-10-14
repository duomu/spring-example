package com.yll.example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author：linlin.yang
 * @date：2017/9/27 14:28
 */
@Component//注册为bean
@Aspect//声明为切面
public class Audience {
    @Pointcut("execution(* com.yll.example.Performance.perform(String)) && args(name)")
    public void performance(String name) {

    }

    @Before("performance(name)")
    public void silenceCellPhones(String name) {
        System.out.println("Silencing cell phones：" + name);
    }

    @Before("performance(name)")
    public void takeSeats(String name) {
        System.out.println("Taking seats：" + name);
    }

    @AfterReturning("performance(name)")
    public void applause(String name) {
        System.out.println("CLAP CLAP CLAP：" + name);
    }

    @AfterThrowing("performance(name)")
    public void demandRefund(String name) {
        System.out.println("Demanding a refund：" + name);
    }

//    @Around("performance()")
//    public void watchPerformance(ProceedingJoinPoint joinPoint) {
//        try{
//            System.out.println("Silencing cell phones");
//            System.out.println("Taking seats");
//            joinPoint.proceed();
//            System.out.println("CLAP CLAP CLAP");
//        }catch (Throwable throwable) {
//            System.out.println("Demanding a refund");
//        }
//    }

}
