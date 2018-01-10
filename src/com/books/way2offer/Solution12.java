package com.books.way2offer;

import java.util.Arrays;

/**
 * Created by gordon on 1/10/18.
 */
public class Solution12 {

    /*打印从1到最大的n位树*/

    public static void main(String[] args) {
        print1ToN(20);
    }

    /**
     * 这是一个大数问题，采用排列组合的方法模拟求解
     */
    private static void print1ToN(int n) {
        int[] simu = new int[n];
        printHelper(simu, 0);
    }

    private static void printHelper(int[] nums, int curBit) {
        if (curBit == nums.length) {
            System.out.println(Arrays.toString(nums));
            return;
        }
        for (int i = 0; i < 10; i++) {
            nums[curBit] = i;
            printHelper(nums, curBit + 1);
        }
    }
}
