package com.yll.list;

import java.util.ArrayList;
import java.util.List;

/**
 * 删除重复的结点
 * @author：linlin.yang
 * @date：2018/3/22 13:54
 */
public class DeleteDuplicateNode {
    public static void main(String[] args) {
        Integer[] a = {1, 2, 3, 4, 5, 6, 7};
//        Integer[] a = {1, 1, 1, 1, 1, 1, 1};
//        Integer[] a = {1, 1, 1, 2, 3, 4, 5};
//        Integer[] a = {1, 2, 3, 3, 4, 4, 5};
//        Integer[] a = {1, 2, 3, 1, 1, 1, 1};
        ListNode head = ListNode.create(a);
        System.out.println("------初始链表------");
        ListNode.print(head);
        ListNode newHead = deleteDuplicate(head);
        System.out.println("--------删除后----");
        ListNode.print(newHead);
    }

    public static void delete(ListNode head) {
        ListNode p = head;
        while (p != null) {
            ListNode q = head;
            while (q.next != p && q.next != null) {
                if (q.next.val == p.val) {
                    q.next = q.next.next;
                } else {
                    q = q.next;
                }
            }

            p = p.next;
        }

    }

    public static ListNode deleteDuplicate0(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode subHead = deleteDuplicate(head.next);
        System.out.println("head：" + head.val);
        System.out.println("subHead：" + (subHead == null ? null : subHead.val));
        if (subHead == null || subHead.val != head.val) {
            head.next = subHead;
        } else {
            head = subHead.next;
        }

        ListNode.print(head);

        return head;
    }

    public static ListNode deleteDuplicate(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        if (head.val == head.next.val) {
            ListNode p = head.next.next;
            while (p != null && p.val == head.val) {
                p = p.next;
            }

            return deleteDuplicate(p);
        }else{
            head.next = deleteDuplicate(head.next);
            return head;
        }
    }

    public static void testDeleteDuplicate() {

    }
}
