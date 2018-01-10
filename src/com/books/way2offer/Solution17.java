package com.books.way2offer;

import com.books.datastructure.linkedlist.ListNode;

/**
 * Created by gordon on 1/10/18.
 */
public class Solution17 {

    /*合并两个排序的链表*/

    public static void main(String[] args) {
        ListNode head1 = new ListNode(0);
        head1.next = new ListNode(1);
        ListNode head2 = new ListNode(2);
        head2.next = new ListNode(3);
        ListNode p = merge(head1, head2);
        while (p != null) {
            System.out.println(p.val);
            p = p.next;
        }
    }

    private static ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }
}
