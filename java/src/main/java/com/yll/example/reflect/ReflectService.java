package com.yll.example.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 通过反射创建对象，并调用对象的方法
 * @author：linlin.yang
 * @date：2018/4/12 15:03
 */
public class ReflectService {
    public void sayHello(String name) {
        System.out.println("hello " + name);
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        //获取ReflectService类
        Class clazz = Class.forName(ReflectService.class.getName());//ClassNotFoundException
        //实例化ReflectService对象
        ReflectService reflectService = (ReflectService) clazz.newInstance();
        //获取方法
        Method method = clazz.getMethod("sayHello", String.class);//NoSuchMethodException

        //调用方法
        method.invoke(reflectService, "Lilei");//InvocationTargetException
    }
}
