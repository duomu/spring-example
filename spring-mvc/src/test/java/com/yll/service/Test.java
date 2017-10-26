package com.yll.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author：linlin.yang
 * @date：2017/10/26 10:31
 */
public class Test {
    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<Integer>();
//        for (Integer i : list) {
//            System.out.println(i);
//        }

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(1);
        stack.push(3);
        System.out.println(stack.search(1));
    }
}
