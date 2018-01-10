package com.books.way2offer;

import com.books.datastructure.linkedlist.ListNode;
import com.books.datastructure.linkedlist.ListUtils;

/**
 * Created by gordon on 1/10/18.
 */
public class Solution13 {

    /*在O(1)的时间删除节点*/

    public static void main(String[] args) {
        ListNode node = ListUtils.buildSortedList();
        ListNode midNode = node;
        for (int i = 0; i < 3; i++) {
            midNode = midNode.next;
        }
        ListNode retNode = deleteNode(node, midNode);
        while (retNode != null) {
            System.out.println(retNode.val);
            retNode = retNode.next;
        }
        ListNode oneNode = new ListNode(1);
        retNode = deleteNode(oneNode,oneNode);
        System.out.println(retNode == null);
    }

    /**
     * 方法比较简单，将后面的值拷贝给前面，但是要注意边缘条件的处理，主要
     * 1. 考虑尾巴节点，需要从头找到上一个节点，还有如果
     * 2. 只有一个节点的情况，需要特殊处理
     */
    private static ListNode deleteNode(ListNode pHead, ListNode node) {
        if (pHead == null || node == null) {
            return pHead;
        }
        if (node.next != null) {
            node.val = node.next.val;
            node.next = node.next.next;
        } else if (node == pHead) {
            pHead = null;
        } else {
            ListNode pLast = pHead;
            while (pLast.next != node) {
                pLast = pLast.next;
            }
            pLast.next = null;
        }
        return pHead;
    }
}
