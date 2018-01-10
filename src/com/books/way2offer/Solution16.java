package com.books.way2offer;

import com.books.datastructure.linkedlist.ListNode;

/**
 * Created by gordon on 1/10/18.
 */
public class Solution16 {

    /*反转单链表*/

    public static void main(String[] args) {
        ListNode pHead = new ListNode(0);
        pHead.next = new ListNode(1);
        pHead.next.next = new ListNode(2);
        pHead.next.next.next = new ListNode(3);
        ListNode p = reverse(pHead);
        while (p != null) {
            System.out.println(p.val);
            p = p.next;
        }
    }

    private static ListNode reverse(ListNode head) {
        if (head == null) return null;
        ListNode current = head;
        while (current.next != null) {
            ListNode auxNode = current.next;
            current.next = auxNode.next;
            auxNode.next = head;
            head = auxNode;
        }
        return head;
    }
}
