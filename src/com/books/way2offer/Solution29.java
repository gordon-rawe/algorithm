package com.books.way2offer;

/**
 * Created by gordon on 1/9/18.
 */
public class Solution29 {

    /*数组中出现次数超过一半的数字*/

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,2,2,2,2,5,4};
        System.out.println(numberAppearMoreThanHalf(nums));
    }

    /**
     * 利用数组的特点，加入一个计数器timeCount，不相等抵消减1，相等增加加1，余
     * 下的那个数字肯定对应为那个数，每次在计数为0的时候，切换为别的数字，但是还是
     * 要在最终检查超过一半，done
     * */
    private static int numberAppearMoreThanHalf(int[] nums) {
        int result = -1;
        int timeCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (timeCount == 0) {
                result = nums[i];
                timeCount++;
            } else if (result == nums[i]) {
                timeCount++;
            } else {
                timeCount--;
            }
        }
        if (checkMoreThanHalf(nums, result)) {
            return result;
        }
        return -1;
    }

    private static boolean checkMoreThanHalf(int[] nums, int target) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target == nums[i]) count++;
        }
        return count > nums.length / 2;
    }
}
