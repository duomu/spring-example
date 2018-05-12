package com.yll.util;

import com.yll.list.ListNode;

import java.util.List;

/**
 * @author：linlin.yang
 * @date：2018/3/23 11:05
 */
public class GoodUtil {
    public static <T> void print(List<T> list) {
        if (list == null) {
            System.out.println("null");
        }

        if (list.size() == 0) {
            System.out.println("{}");
        }

        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
                System.out.print(list.get(i) + ", ");
            } else {
                System.out.println(list.get(i));
            }
        }

        System.out.println();
    }

    public static <T> String toString(List<T> list) {
        if (list == null) {
            return null;
        }

        if (list.size() == 0) {
            return "[]";
        }

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
                stringBuffer.append(list.get(i) + ", ");
            } else {
                stringBuffer.append(list.get(i));
            }
        }

        return stringBuffer.toString();
    }

    public static void main(String[] args) {

    }
}
