package com.yll.list;

/**
 * @author：linlin.yang
 * @date：2018/3/22 13:53
 */
public class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public static ListNode create(Integer[] a) {
        if (a == null || a.length == 0) {
            return null;
        }

        ListNode head = null;
        ListNode tail = null;
        for (int i = 0; i < a.length; i++) {
            if (head == null) {
                head = new ListNode(a[i]);
                tail = head;
            } else {
                ListNode newNode = new ListNode(a[i]);
                tail.next = newNode;
                tail = newNode;
            }
        }

        return head;
    }

    public static void print(ListNode head) {
        while (head != null) {
            if (head.next != null) {
                System.out.print(head.val + ", ");
            } else {
                System.out.print(head.val);
            }
            head = head.next;
        }
        System.out.println();
    }

    public static Integer[] toArray(ListNode head) {
        if (head == null) {
            return null;
        }

        int size = 0;
        ListNode p = head;
        while (head != null) {
            size++;
            p = p.next;
        }

        Integer[] array = new Integer[size];
        p = head;
        int i = 0;
        while (p != null) {
            array[i] = p.val;
            p = p.next;
            i++;
        }

        return array;
    }

    public static String toString(ListNode head) {
        if (head == null) {
            return "";
        }

        StringBuffer stringBuffer = new StringBuffer();
        ListNode p = head;
        while (p != null) {
            if (p.next != null) {
                stringBuffer.append(p.val + ", ");
            } else {
                stringBuffer.append(p.val);
            }
        }

        return stringBuffer.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof ListNode) {
            ListNode head = (ListNode) obj;

            if (head != null) {
                ListNode p = this;
                ListNode q = head;
                while (p != null && q != null) {
                    if (p.val != q.val) {
                        return false;
                    }

                    p = p.next;
                    q = q.next;
                }

                if (p == null && q == null) {
                    return true;
                }
            }

        }

        return false;
    }

//    @Override
//    public int hashCode() {
//        int result = 17;
//    }
}
