package com.books.way2offer;

/**
 * Created by gordon on 1/11/18.
 */
public class Solution3 {

    /*排序数组中搜索数字,
    * 方法： 从右上角开始往左往下搜索
    * */

    public static void main(String[] args) {
        int[][] sample = new int[][]{
                new int[]{1, 5, 6},
                new int[]{6, 6, 7},
                new int[]{8, 9, 10}
        };
        System.out.println(searchInMatrix(sample, 4));
    }

    private static boolean searchInMatrix(int[][] matrix, int value) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int ROW = matrix.length, COL = matrix[0].length;
        int row = 0, col = COL - 1;
        boolean retValue = false;
        while (row < ROW && col >= 0) {
            if (matrix[row][col] == value) {
                retValue = true;
                break;
            } else if (matrix[row][col] > value) {
                col--;
            } else {
                row++;
            }
        }
        return retValue;
    }
}
