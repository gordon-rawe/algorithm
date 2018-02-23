package com.lintcode.solves;

import java.util.Arrays;
import java.util.List;

/**
 * Created by gordon on 2/22/18.
 */
public class Solution39 {

    public static void recoverRotatedSortedArray(List<Integer> nums) {
        if (nums == null || nums.size() < 1) {
            return;
        }
        if (nums.get(0) < nums.get(nums.size() - 1)) {
            return;
        }
        int pivot = 0;
        while (nums.get(pivot) < nums.get(pivot + 1)) {
            pivot++;
        }
        reverse(nums, 0, pivot);
        reverse(nums, pivot + 1, nums.size() - 1);
        reverse(nums, 0, nums.size() - 1);
    }

    public static void reverse(List<Integer> nums, int aIndex, int bIndex) {
        while (aIndex < bIndex) {
            Integer tmpValue = nums.get(aIndex);
            nums.set(aIndex++, nums.get(bIndex));
            nums.set(bIndex--, tmpValue);
        }
    }

    public static void main(String[] args) {
        List<Integer> sample = Arrays.asList(4, 5, 1, 2, 3);
        recoverRotatedSortedArray(sample);
        for (Integer integer : sample) {
            System.out.print(integer);
        }

    }
}
