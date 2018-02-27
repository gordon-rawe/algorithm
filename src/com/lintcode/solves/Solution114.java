package com.lintcode.solves;

/**
 * Created by gordon on 2/27/18.
 */
public class Solution114 {

    public static void main(String[] args) {

    }

    public static int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (m == 1 || n == 1) {
            return 1;
        }
        int[][] dpTable = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dpTable[i][j] = 1;
                } else {
                    dpTable[i][j] = dpTable[i - 1][j] + dpTable[i][j - 1];
                }
            }
        }
        return dpTable[m - 1][n - 1];
    }
}
