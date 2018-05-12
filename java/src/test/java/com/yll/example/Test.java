package com.yll.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author：linlin.yang
 * @date：2018/4/4 10:17
 */
public class Test {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<Integer>();
        set.add(1);
        set.add(2);
        set.add(3);

        List<Integer> list = new ArrayList<Integer>(set);

        for (int i=0;i<list.size();i++) {
            System.out.println(list.get(i));
        }
    }
}
