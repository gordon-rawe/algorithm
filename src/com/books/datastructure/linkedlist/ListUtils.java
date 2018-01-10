package com.books.datastructure.linkedlist;

/**
 * Created by gordon on 1/6/18.
 */
public class ListUtils {

    public static ListNode buildSampleList() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        return head;
    }

    public static ListNode buildSortedList() {
        ListNode pHead = new ListNode(0);
        pHead.next = new ListNode(1);
        pHead.next.next = new ListNode(2);
        pHead.next.next.next = new ListNode(3);
        return pHead;
    }

}
