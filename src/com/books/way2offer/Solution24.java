package com.books.way2offer;

/**
 * Created by gordon on 1/10/18.
 */
public class Solution24 {

    /*判断数组是否是一棵树的后续遍历结果*/

    public static void main(String[] args) {
        System.out.println(isPostVisitOrder(new int[]{5, 7, 6, 9, 11, 10, 8}));
        System.out.println(isPostVisitOrder(new int[]{7, 4, 6, 5}));
    }


    /*判断方法，最后一个值是pivot，将前面的数据分为两组，其中前面的值小于后面的，不断重复这个过程*/
    private static boolean isPostVisitOrder(int[] nums) {
        if (nums == null && nums.length <= 0) {
            return false;
        }
        return visitHelper(nums, 0, nums.length - 1);
    }

    private static boolean visitHelper(int[] nums, int left, int right) {
        int pivot = nums[right];
        int pSplit = left;
        while (pSplit < right && nums[pSplit] < pivot) pSplit++;
        for (int i = pSplit; i < right; i++) {
            if (nums[i] < pivot) {
                return false;
            }
        }
        boolean rLeft = true;
        if (pSplit > left) rLeft = visitHelper(nums, left, pSplit - 1);
        boolean rRight = true;
        if (pSplit < right - 1) rRight = visitHelper(nums, pSplit, right - 1);
        return rLeft && rRight;
    }
}
