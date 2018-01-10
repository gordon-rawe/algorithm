package com.books.way2offer;

/**
 * Created by gordon on 1/10/18.
 */
public class Solution20 {

    /*顺时针打印数组*/

    public static void main(String[] args) {
        printClockWise(new int[][]{
                new int[]{1, 2, 3, 4},
                new int[]{4, 5, 6, 7},
                new int[]{7, 8, 9, 10},
        });
    }

    private static void printClockWise(int[][] matrix) {
        assert matrix != null && matrix.length > 0 && matrix[0].length > 0;
        int ROW = matrix.length;
        int COL = matrix[0].length;
        int ROUND = Math.min(ROW, COL) / 2;
        for (int step = 0; step < ROUND; step++) {
            for (int i = step; i < COL - step; i++) {
                System.out.print(matrix[step][i]);
                System.out.print(" ");
            }
            for (int i = step + 1; i < ROW - step; i++) {
                System.out.print(matrix[i][COL - step - 1]);
                System.out.print(" ");
            }
            for (int i = COL - step - 2; i >= step; i--) {
                System.out.print(matrix[ROW - step - 1][i]);
                System.out.print(" ");
            }
            for (int i = ROW - step - 2; i > step; i--) {
                System.out.print(matrix[i][step]);
                System.out.print(" ");
            }
        }
        for (int i = ROUND; i < Math.max(ROW, COL) - ROUND; i++) {
            System.out.print(ROW > COL ? matrix[i][ROUND] : matrix[ROUND][i]);
            System.out.print(" ");
        }
    }
}
