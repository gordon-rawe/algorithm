package com.books.way2offer;

/**
 * Created by gordon on 1/11/18.
 */
public class Solution8 {

    /*旋转后的数组的最小的数字,比如1，2，3，4，5旋转后为4，5，1，2，3   5,1,2,3,4  2,3,4,5,1*/

    public static void main(String[] args) {
        System.out.println(minOfSortedArray(new int[]{
                3, 1, 2
        }));
    }

    private static int minOfSortedArray(int[] nums) {
        assert nums != null && nums.length > 0;
        /*只有一个树或者没有旋转的步数为k*n*/
        if (nums[0] <= nums[nums.length - 1]) {
            return nums[0];
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (left + 1 == right) {
                return Math.min(nums[left], nums[right]);
            }
            int mid = (left + right) / 2;
            if (nums[mid] > nums[left]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}
