package com.lintcode.solves;

/**
 * Created by gordon on 2/23/18.
 */
public class Solution48 {

    /**
     * Given an array with integers.
     * Find two non-overlapping subarrays A and B, which |SUM(A) - SUM(B)| is the largest.
     */

    public static void main(String[] args) {
        System.out.println(maxDiffSubArrays(new int[]{1, 2, -3, 1}));
    }

    private static int[] calcMaxFromLeft(int[] nums) {
        int[] maxValues = new int[nums.length - 1];
        int curSum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            curSum = Math.max(curSum + nums[i], nums[i]);
            maxValues[i] = curSum;
        }
        return maxValues;
    }

    private static int[] calcMaxFromRight(int[] nums) {
        int[] maxValues = new int[nums.length - 1];
        int curSum = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            curSum = Math.max(curSum + nums[i + 1], nums[i + 1]);
            maxValues[i] = curSum;
        }
        return maxValues;
    }

    private static int[] calcMinFromLeft(int[] nums) {
        int[] minValues = new int[nums.length - 1];
        int curSum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            curSum = Math.min(curSum + nums[i], nums[i]);
            minValues[i] = curSum;
        }
        return minValues;
    }

    private static int[] calcMinFromRight(int[] nums) {
        int[] maxValues = new int[nums.length - 1];
        int curSum = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            curSum = Math.min(curSum + nums[i + 1], nums[i + 1]);
            maxValues[i] = curSum;
        }
        return maxValues;
    }

    private static int maxDiffSubArrays(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int[] maxFromLeft = calcMaxFromLeft(nums);
        int[] maxFromRight = calcMaxFromRight(nums);
        int[] minFromLeft = calcMinFromLeft(nums);
        int[] minFromRight = calcMinFromRight(nums);
        int maxAbs = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxAbs = Math.max(Math.abs(maxFromLeft[i] - minFromRight[i]), maxAbs);
            maxAbs = Math.max(Math.abs(maxFromRight[i] - minFromLeft[i]), maxAbs);
        }
        return maxAbs;
    }
}
