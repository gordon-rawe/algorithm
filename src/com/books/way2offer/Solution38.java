package com.books.way2offer;

/**
 * Created by gordon on 1/6/18.
 */
public class Solution38 {

    /*数字在排序数组中出现的次数*/

    public static void main(String[] args) {
        System.out.println(timesOfK(new int[]{1}, 1));
    }

    private static int timesOfK(int[] nums, int k) {
        assert nums != null && nums.length > 0;
        int pIndex = helper(nums, k, 0, nums.length - 1);
        if (pIndex == -1) {
            return 0;
        }
        int pLeft = pIndex, pRight = pIndex;
        while (pLeft - 1 >= 0 && nums[pLeft - 1] == k) pLeft--;
        while (pRight + 1 < nums.length && nums[pRight + 1] == k) pRight++;
        return pRight - pLeft + 1;
    }

    /*找出任意一个k的位置*/
    private static int helper(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > left) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
