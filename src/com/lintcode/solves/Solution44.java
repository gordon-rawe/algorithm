package com.lintcode.solves;

import java.util.Arrays;
import java.util.List;

/**
 * Created by gordon on 2/23/18.
 */
public class Solution44 {

    public static void main(String[] args) {
        System.out.println(minSubArray(Arrays.asList(2, 1, 3, 4)));
    }

    public static int minSubArray(List<Integer> nums) {
        if (nums == null || nums.isEmpty()) {
            return 0;
        }
        int curSum = 0, minSum = nums.get(0);
        for (int i = 0; i < nums.size(); i++) {
            curSum = curSum > 0 ? nums.get(i) : curSum + nums.get(i);
            minSum = Math.min(minSum, curSum);
        }
        return minSum;
    }
}
