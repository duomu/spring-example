package com.yll.example.collection;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author：linlin.yang
 * @date：2018/4/11 14:12
 */
public class ListTest {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(1);//在链表的末尾添加一个元素
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.println("------链表-----");
        for (Object obj : list) {
            System.out.print(obj + ", ");
        }

        System.out.println();

        System.out.println("------队列-----");
        list.clear();
        list.offer(1);//入队：在链表的末尾添加一个元素
        list.offer(2);
        list.offer(3);
        System.out.println(list.poll());//出队：从链表的头部删除一个元素
        System.out.println(list.poll());
        System.out.println(list.poll());

        System.out.println("------栈-----");
        list.clear();
        list.push(1);//入栈：在链表的头部添加一个元素
        list.push(2);
        list.push(3);
        System.out.println(list.pop());//出栈：从链表的头部删除一个元素
        System.out.println(list.pop());
        System.out.println(list.pop());

    }
}
