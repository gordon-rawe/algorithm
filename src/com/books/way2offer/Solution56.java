package com.books.way2offer;

import com.books.datastructure.linkedlist.ListNode;

/**
 * Created by gordon on 12/29/17.
 */
public class Solution56 {
    /**
     * 检查一个链表是否有环并且有的话找到入口节点
     */

    private static ListNode findEntry(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode oneNode = null;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                oneNode = slow;
                break;
            }
        }
        if (oneNode == null) {
            return null;
        }
        ListNode probe = head, history = oneNode;
        /* loop for lenRing - 1 steps */
        while (oneNode.next != history) {
            oneNode = oneNode.next;
            probe = probe.next;
        }
        /*loop one more step to get lenRing steps*/
        probe = probe.next;
        oneNode = head;
        while (probe != oneNode) {
            probe = probe.next;
            oneNode = oneNode.next;
        }
        return probe;
    }


    /**
     * 测试用例:
     * 1. 一个节点的循环链表 pass
     * 2. 两个节点的循环链表 pass
     * 3. 多个节点的从头开始情况 pass
     * 4. 多个节点两节点循环链表 pass
     * 5. 多个节点多节点循环链表 pass
     * */
    private static ListNode buildSample() {
        ListNode node = new ListNode(2), history = node;
        node.next = new ListNode(3);
        node = node.next;
        ListNode split = node;
        node.next = new ListNode(4);
        node = node.next;
        node.next = new ListNode(5);
        node = node.next;
        node.next = split;
        return history;
    }

    public static void main(String[] args) {
        System.out.println(findEntry(buildSample()).val);
    }
}
