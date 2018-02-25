package com.lintcode.solves;

import com.common.Utils;

/**
 * Created by gordon on 2/24/18.
 */
public class Solution34 {

    public static void main(String[] args) {
        System.out.println(solveNQueens(4));
    }

    private static int solveNQueens(int n) {
        int[] count = new int[1];
        if (n <= 0) {
            return count[0];
        }
        int[] matrix = new int[n];
        for (int i = 0; i < n; i++) {
            matrix[i] = i;
        }
        traverseHelper(matrix, 0, count);
        return count[0];
    }

    private static void traverseHelper(int[] nums, int layer, int[] count) {
        if (layer == nums.length) {
            checkOnce(nums, count);
            return;
        }
        for (int i = layer; i < nums.length; i++) {
            Utils.swap(nums, i, layer);
            traverseHelper(nums, layer + 1, count);
            Utils.swap(nums, i, layer);
        }
    }

    private static void checkOnce(int[] nums, int[] count) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) == Math.abs(i - j)) {
                    return;
                }
            }
        }
        count[0]++;
    }
}
