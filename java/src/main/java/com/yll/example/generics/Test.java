package com.yll.example.generics;

/**
 * @author：linlin.yang
 * @date：2018/4/10 20:53
 */
public class Test {
    public static void main(String[] args) {
        Person<String> person1 = new Person<String>("xxx");
        Person<Integer> person2 = new Person<Integer>(1);
        person1.print();
        person2.print();

        Cat<String> cat1 = new Cat<String>("tom");
        Cat<String> cat2 = new Cat<String>("hoo");
        cat1.print();
        cat2.print();
    }
}
