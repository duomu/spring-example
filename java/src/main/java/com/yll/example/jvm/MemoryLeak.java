package com.yll.example.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 内存泄露demo
 * -Xmx=10m -Xms=10m -XX:+HeapDumpOnOutOfMemoryError -XX:PrintGCDetails
 * @author：linlin.yang
 * @date：2018/5/10 18:19
 */
public class MemoryLeak {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Integer val = new Integer(i);
            list.add(val);
        }

        list = null;

        Integer i = new Integer(1);
        Integer j = i;
    }
}
