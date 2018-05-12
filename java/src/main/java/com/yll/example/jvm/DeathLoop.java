package com.yll.example.jvm;

/**
 * @author：linlin.yang
 * @date：2018/3/29 16:46
 */
public class DeathLoop {
    public static void main(String[] args) {
        boolean flag = true;
        while (flag) {
            System.out.println("this is death loop");
        }
    }
}
