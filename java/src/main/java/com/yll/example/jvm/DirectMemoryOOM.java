package com.yll.example.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author：linlin.yang
 * @date：2018/3/28 17:43
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;
    public static void main(String[] args) throws IllegalAccessException {
        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
