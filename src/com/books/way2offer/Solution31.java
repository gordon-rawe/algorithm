package com.books.way2offer;

/**
 * Created by gordon on 1/9/18.
 */
public class Solution31 {

    /*求连续子数组的最大值，使用dp进行求解,要求时间复杂度为T(n),O(1)有正数有负数*/

    public static void main(String[] args) {
        System.out.println(maxContinuousSum(new int[]{1, 2, -4, 4}));
    }

    private static int maxContinuousSum(int[] nums) {
        int curSum = 0, maxSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (curSum > 0) {
                curSum = curSum + nums[i];
            } else {
                curSum = nums[i];
            }
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }
}
