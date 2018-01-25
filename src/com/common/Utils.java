package com.common;

import com.books.datastructure.linkedlist.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gordon on 12/29/17.
 */
public class Utils {
    public static void swap(int[] nums, int left, int right) {
        int copyValue = nums[left];
        nums[left] = nums[right];
        nums[right] = copyValue;
    }

    public static ListNode buildSampleList() {
        ListNode retNode = new ListNode(1);
        retNode.next = new ListNode(1);
        retNode.next.next = new ListNode(2);
        retNode.next.next.next = new ListNode(3);
        retNode.next.next.next.next = new ListNode(3);
        retNode.next.next.next.next.next = new ListNode(4);
        return retNode;
    }

    public static List<Integer> arr2List(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int num : nums) {
            res.add(num);
        }

        return res;
    }
}
