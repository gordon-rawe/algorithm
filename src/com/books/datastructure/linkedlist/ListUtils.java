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

}
