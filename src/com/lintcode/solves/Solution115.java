package com.lintcode.solves;

/**
 * Created by gordon on 2/27/18.
 */
public class Solution115 {

    public static void main(String[] args) {

    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        int M = obstacleGrid.length, N = obstacleGrid[0].length;
        int[][] dpTable = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0 && j == 0) {
                    dpTable[i][j] = obstacleGrid[i][j] == 0 ? 1 : 0;
                } else if (i == 0) {
                    dpTable[i][j] = obstacleGrid[i][j] == 0 ? dpTable[i][j - 1] : 0;
                } else if (j == 0) {
                    dpTable[i][j] = obstacleGrid[i][j] == 0 ? dpTable[i - 1][j] : 0;
                } else {
                    dpTable[i][j] = obstacleGrid[i][j] == 0 ? dpTable[i - 1][j] + dpTable[i][j - 1] : 0;
                }
            }
        }
        return dpTable[M - 1][N - 1];
    }
}
