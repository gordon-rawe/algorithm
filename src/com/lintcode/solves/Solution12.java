package com.lintcode.solves;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gordon on 1/28/18.
 */
public class Solution12 {

    /**
     * subsets need to verify the distinct numbers, we need 3 steps to achieve it:
     * 1. if curIndex -> 0, add and then remove it
     * 2. if numbers at curIndex and curIndex - 1 is different, add it.
     * 3. if number at curIndex is the same as the pushed numbers's last one, add it.
     * <p>
     * finally the [1,2,2] tree will be as:
     * []
     * [1]         []
     * [12]    [1]   [2]    []
     * [122][12][x][1][22][2][x][]
     * <p>
     * [x] means duplicated elements.
     */

    public static void main(String[] args) {
        for (List<Integer> integers : subSets(new int[]{1, 1})) {
            System.out.println(integers);
        }

    }

    private static List<List<Integer>> subSets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        //保证有序，先sort
        Arrays.sort(nums);
        dfsTraverseHelper(nums, 0, new LinkedList<>(), res);
        return res;
    }

    private static void dfsTraverseHelper(int[] nums, int curIndex, Deque<Integer> list, List<List<Integer>> res) {
        if (curIndex == nums.length) {
            res.add(new LinkedList<>(list));
            return;
        }
        //在第curIndex层取这个数字
        list.addLast(nums[curIndex]);
        dfsTraverseHelper(nums, curIndex + 1, list, res);
        //在curIndex层不取这个数字
        list.pollLast();
        if (list.isEmpty() || list.peekLast() != nums[curIndex]) {
            dfsTraverseHelper(nums, curIndex + 1, list, res);
        }
    }
}
