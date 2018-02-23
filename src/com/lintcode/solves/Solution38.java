package com.lintcode.solves;

/**
 * Created by gordon on 2/22/18.
 */
public class Solution38 {


    public static boolean searchMatrixII(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int ROW = matrix.length, COL = matrix[0].length;
        int row = 0, col = COL - 1;
        while (row < ROW && col >= 0) {
            if (matrix[row][col] > target) {
                col--;
            } else if (matrix[row][col] < target) {
                row++;
            } else {
                return true;
            }
        }
        return false;
    }

    public static int searchMatrixIII(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int count = 0;
        int ROW = matrix.length, COL = matrix[0].length;
        int row = 0, col = COL - 1;
        while (row < ROW && col >= 0) {
            if (matrix[row][col] > target) {
                col--;
            } else if (matrix[row][col] < target) {
                row++;
            } else {
                row++;
                col = COL - 1;
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 3, 5, 7},
                {2, 3, 7, 8},
                {3, 5, 9, 10}
        };
        System.out.println(searchMatrixIII(matrix, 3));
    }
}
