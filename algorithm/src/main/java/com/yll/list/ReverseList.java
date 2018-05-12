package com.yll.list;

/**
 * @author：linlin.yang
 * @date：2018/3/8 16:51
 */
public class ReverseList {

    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = null;
        while (head != null) {
            ListNode s = head;
            head = head.next;
            s.next = newHead;
            newHead = s;
        }

        return newHead;
    }

    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5};
        ListNode head = ListNode.create(array);
        System.out.println("----------初始链表--------");
        ListNode.print(head);
        System.out.println("----------反转后的链表--------");

        ListNode.print(reverse(head));

    }
}
