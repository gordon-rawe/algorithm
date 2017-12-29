package com.lintcode;

/**
 * Created by books on 12/11/17.
 */
public class TestLand {

    private static int maxOnesIndex(int[] nums) {
        int maxIndex = 0, curMax = 0, pZero = -1, ppZero = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (i - ppZero - 1 > curMax) {
                    curMax = i - ppZero - 1;
                    maxIndex = pZero;
                }
                ppZero = pZero;
                pZero = i;
            }
        }
        if (nums.length - ppZero - 1 > curMax) {
            curMax = nums.length - ppZero - 1;
            maxIndex = pZero;
        }
        return maxIndex;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 0, 1};
        System.out.println(maxOnesIndex(nums));
    }
}
