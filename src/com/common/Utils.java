package com.common;

/**
 * Created by gordon on 12/29/17.
 */
public class Utils {
    public static void swap(int[] nums, int left, int right) {
        int copyValue = nums[left];
        nums[left] = nums[right];
        nums[right] = copyValue;
    }
}
