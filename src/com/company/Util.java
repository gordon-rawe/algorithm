package com.company;

/**
 * Created by gordon on 10/20/17.
 */
public class Util {
    public static void printArray(int[] values) {
        for (int value : values) {
            System.out.print(value);
            System.out.print(" ");
        }
    }

    /**
     * sample: 1->2->5
     */
    public static ListNode buildList(String sample) {
        try {
            ListNode retNode = null, tmpNode = null;
            String[] parsed = sample.split("->");
            for (String aParsed : parsed) {
                if (tmpNode == null) {
                    tmpNode = retNode = new ListNode(Integer.valueOf(aParsed));
                } else {
                    ListNode node = new ListNode(Integer.valueOf(aParsed));
                    tmpNode.next = node;
                    tmpNode = node;
                }
            }
            return retNode;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void printListNode(ListNode head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
