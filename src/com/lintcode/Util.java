package com.lintcode;

import com.books.datastructure.linkedlist.ListNode;

/**
 * Created by books on 10/20/17.
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

    /**
     * 实用数组来解决大数问题
     * */
    public int leftRotate(int num, int d) {
        d = d % 32;
        int[] bits = intTo32Ints(num);
        rorateLeft(bits, d);
        return intsToInt(bits);
    }

    private static int intsToInt(int[] bits) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            if (bits[i] == 1) {
                result |= 1 << (31 - i);
            }
        }
        return result;
    }

    private static int[] intTo32Ints(int number) {
        int[] bits = new int[32];
        int index = bits.length - 1;
        while(index >= 0) {
            bits[index--] = (number & 1) == 1 ? 1 : 0;
            number = number >> 1;
        }
        return bits;
    }

    private static void rorateLeft(int[] bits, int d) {
        if(bits.length == 0 || d == 0) return;
        reverse(bits, 0, d - 1);
        reverse(bits, d, bits.length - 1);
        reverse(bits, 0, bits.length - 1);
    }

    private static void reverse(int[] bits, int left, int right) {
        while(left < right) {
            int tmpValue = bits[left];
            bits[left++] = bits[right];
            bits[right--] = tmpValue;
        }
    }

    public static void main(String[] args) {

    }
}
