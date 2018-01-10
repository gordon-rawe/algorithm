package com.books.way2offer;

import com.books.datastructure.linkedlist.ListNode;
import com.books.datastructure.linkedlist.ListUtils;

/**
 * Created by gordon on 1/10/18.
 */
public class Solution15 {

    /*链表的倒数第k个节点*/


    public static void main(String[] args) {
        ListNode node = findKthToTail(ListUtils.buildSortedList(), 3);
        if (node != null) {
            System.out.println(node.val);
        }
    }

    /**
     * 这道题不难，先算长度，再两个指针，一个先走k步，然
     * 后同时走，前面的走到null，后面的就是倒数第k个节点，
     * 注意点，边界值的判断,
     * O(N)
     */
    private static ListNode findKthToTail(ListNode list, int k) {
        if (list == null || k <= 0) {
            return null;
        }
        int count = 0;
        ListNode tNode = list, retNode = list;
        while (tNode != null) {
            count++;
            tNode = tNode.next;
        }
        if (k > count) {
            return null;
        }
        tNode = list;
        for (int i = 0; i < k; i++) {
            tNode = tNode.next;
        }
        while (tNode != null) {
            tNode = tNode.next;
            retNode = retNode.next;
        }
        return retNode;
    }
}
