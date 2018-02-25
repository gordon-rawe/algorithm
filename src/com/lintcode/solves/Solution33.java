package com.lintcode.solves;

import com.common.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gordon on 2/24/18.
 */
public class Solution33 {

    public static void main(String[] args) {
        for (List<String> strings : solveNQueens(10)) {
            System.out.println(strings);
        }
    }

    private static List<List<String>> solveNQueens(int n) {
        List<List<String>> retValue = new ArrayList<>();
        if (n <= 0) {
            return retValue;
        }
        int[] matrix = new int[n];
        for (int i = 0; i < n; i++) {
            matrix[i] = i;
        }
        traverseHelper(matrix, 0, retValue);
        return retValue;
    }

    private static void traverseHelper(int[] nums, int layer, List<List<String>> retValue) {
        if (layer == nums.length) {
            checkOnce(nums, retValue);
            return;
        }
        for (int i = layer; i < nums.length; i++) {
            Utils.swap(nums, i, layer);
            traverseHelper(nums, layer + 1, retValue);
            Utils.swap(nums, i, layer);
        }
    }

    private static void checkOnce(int[] nums, List<List<String>> retValue) {
        boolean peace = true;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) == Math.abs(i - j)) {
                    peace = false;
                    break;
                }
            }
        }
        if (peace) {
            retValue.add(drawChessboard(nums));
        }
    }

    private static List<String> drawChessboard(int[] nums) {
        List<String> chessboard = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < nums.length; j++) {
                sb.append(j == nums[i] ? 'Q' : '.');
            }
            chessboard.add(sb.toString());
        }
        return chessboard;
    }
}
