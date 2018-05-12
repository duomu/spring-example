package com.yll.example.generics;

/**
 * @author：linlin.yang
 * @date：2018/4/10 20:51
 */
public class Person<T> {
    T data;

    public Person(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public <T> void print() {
        System.out.println(this.data);
    }
}
