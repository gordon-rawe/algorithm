package com.books.way2offer;

import com.books.datastructure.linkedlist.ListNode;
import com.books.datastructure.linkedlist.ListUtils;

/**
 * Created by gordon on 1/6/18.
 */
public class Solution37 {

    /*两个链表的第一个公共节点*/

    public static void main(String[] args) {
        ListNode tail = ListUtils.buildSampleList();
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(1);
        two.next = new ListNode(10);
        one.next = tail;
        two.next.next = tail;
        ListNode result = firstJoin(one, two);
        System.out.println(result.val);
    }

    private static ListNode firstJoin(ListNode node1, ListNode node2) {
        if (node1 == null || node2 == null) return null;
        int len1 = 0, len2 = 0;
        ListNode tNode1 = node1, tNode2 = node2;
        while (tNode1 != null) {
            tNode1 = tNode1.next;
            len1++;
        }
        while (tNode2 != null) {
            tNode2 = tNode2.next;
            len2++;
        }
        tNode1 = node1;
        tNode2 = node2;
        int delta = len1 - len2;
        if (delta > 0) {
            /*链表1更长*/
            while (delta > 0) {
                tNode1 = tNode1.next;
                delta--;
            }
        } else if (delta < 0) {
            while (delta < 0) {
                tNode2 = tNode2.next;
                delta++;
            }
        }
        while (tNode1 != null && tNode2 != null) {
            if (tNode1 == tNode2) {
                return tNode1;
            }
            tNode1 = tNode1.next;
            tNode2 = tNode2.next;
        }
        return null;
    }
}
