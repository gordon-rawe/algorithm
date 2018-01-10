package com.books.way2offer;

import com.common.Utils;

import java.util.Arrays;

/**
 * Created by gordon on 1/10/18.
 */
public class Solution14 {

    /*调整数组顺序使得奇数位于偶数前面,这道题目参考partition分分钟搞定*/

    public static void main(String[] args) {
        int[] sample = new int[]{1, 2, 3, 4, 5, 6, 7};
        adjustArray(sample);
        System.out.println(Arrays.toString(sample));
    }

    private static void adjustArray(int[] nums) {
        if (nums == null) {
            return;
        }
        int odd = 0, even = nums.length - 1;
        while (odd < even) {
            while (odd < even && nums[odd] % 2 != 0) odd++;
            while (odd < even && nums[even] % 2 == 0) even--;
            if (odd < even) Utils.swap(nums, odd, even);
        }
    }
}
