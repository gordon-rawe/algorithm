package com.books.way2offer;

import com.books.datastructure.linkedlist.ListNode;

/**
 * Created by gordon on 1/10/18.
 */
public class Solution5 {

    /*从尾到头打印链表*/

    public static void main(String[] args) {
        ListNode pHead = new ListNode(0);
        pHead.next = new ListNode(1);
        pHead.next.next = new ListNode(2);
        pHead.next.next.next = new ListNode(3);
        printReverse(pHead);
    }

    private static void printReverse(ListNode node) {
        if (node != null) {
            printReverse(node.next);
            System.out.println(node.val);
        }
    }
}
