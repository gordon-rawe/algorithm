package com.books.way2offer;

import com.books.datastructure.linkedlist.ListNode;
import com.common.Utils;

/**
 * Created by gordon on 1/3/18.
 */
public class Solution57 {

    /*删除链表中重复的节点,使用两个辅助节点和一个parent节点*/

    public static void main(String[] args) {
        ListNode srcList = Utils.buildSampleList();
        ListNode ret = deleteDuplicates(srcList);
        System.out.println(ret.val);
    }

    private static ListNode deleteDuplicates(ListNode head) {
        ListNode mParent = new ListNode(0);
        mParent.next = head;
        ListNode pNode = mParent, cNode = head;
        while (cNode != null && cNode.next != null) {
            if (cNode.next.val == cNode.val) {
                ListNode node = cNode;
                while (node.next != null && node.next.val == node.val) {
                    node = node.next;
                }
                cNode = node.next;
                pNode.next = cNode;
            } else {
                cNode = cNode.next;
                pNode = pNode.next;
            }
        }
        return mParent.next;
    }
}
