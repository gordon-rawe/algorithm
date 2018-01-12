package com.books.way2offer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gordon on 1/12/18.
 */
public class Solution65 {

    /*滑动窗口的最大值*/

    public static void main(String[] args) {
        for (Integer integer : maxSlideWindow(new int[]{2, 3, 4, 2, 6, 2, 5, 1}, 3)) {
            System.out.print(integer);
            System.out.print(" ");
        }
    }

    private static List<Integer> maxSlideWindow(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        List<Integer> retArray = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) deque.pollLast();
            deque.addLast(i);
        }
        for (int i = k; i < nums.length; i++) {
            retArray.add(nums[deque.peekFirst()]);
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) deque.pollLast();
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) deque.pollFirst();
            deque.addLast(i);
        }
        retArray.add(nums[deque.peekFirst()]);
        return retArray;
    }
}
