package com.lintcode.solves;

/**
 * Created by gordon on 2/22/18.
 */
public class Solution28 {

    public static void main(String[] args) {

    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int ROW = matrix.length, COL = matrix[0].length;
        int start = 0, end = COL * ROW - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int row = mid / COL;
            int col = mid % COL;
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }
}
