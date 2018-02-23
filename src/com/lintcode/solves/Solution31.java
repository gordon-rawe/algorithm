package com.lintcode.solves;

import com.common.Utils;

import java.util.Arrays;

/**
 * Created by gordon on 2/22/18.
 */
public class Solution31 {

    public static int partitionArray(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            while (left < right && nums[left] < k) {
                left++;
            }
            while (left < right && nums[right] >= k) {
                right--;
            }
            if (left < right) Utils.swap(nums, left, right);
        }
        return right;
    }

    public static void main(String[] args) {
        int[] sample = new int[]{6, 6, 8, 6, 7, 9, 8, 7, 9, 6, 8, 6, 8, 9, 8, 7, 8, 6, 7, 6, 6, 8, 6, 6};
        System.out.println(partitionArray(sample, 5));
        System.out.println(Arrays.toString(sample));
    }
}
