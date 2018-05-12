package com.yll.example.jvm;

import java.util.List;

/**
 * @author：linlin.yang
 * @date：2018/3/30 20:29
 */
public class StaticDispatch {
    static abstract class Human {

    }

    static class Man extends Human {

    }

    static class Woman extends Human {
    }

    public void sayHello(Human human) {
        System.out.println("hello, guy");
    }

    public void sayHello(Man man) {
        System.out.println("hello, man");
    }

    public void sayHello(Woman woman) {
        System.out.println("hello, woman");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);
        sr.sayHello(woman);

        func(null);
    }

    public static void func(List<Integer> list) {
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
        }
    }
}
