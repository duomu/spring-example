package com.yll.example.generics;

/**
 * @author：linlin.yang
 * @date：2018/4/10 20:59
 */
public class Cat<T> implements MyObject<T> {
    private T data;

    public Cat(T data) {
        this.data = data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T get() {
        return this.data;
    }

    public void print() {
        System.out.println(this.data);
    }
}
