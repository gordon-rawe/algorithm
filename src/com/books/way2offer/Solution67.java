package com.books.way2offer;

/**
 * Created by gordon on 1/13/18.
 */
public class Solution67 {

    /*机器人的运动范围，地上有一个M*N的矩阵，他可以在坐标x，y上运动，x，y满足的条件是x，y的数位和不超过k*/

    public static void main(String[] args) {
        System.out.println(rangeOfRobot(6, 6, 4));
    }

    private static int rangeOfRobot(int row, int col, int k) {
        if (row <= 0 || col <= 0 | k <= 0) {
            return 0;
        }
        boolean[][] visited = new boolean[row][col];
        return searchHelper(visited, 0, 0, row, col, k);
    }

    private static int searchHelper(boolean[][] visited, int row, int col, int ROW, int COL, int k) {
        if (row < 0 || col < 0 || row >= ROW || col >= COL || visited[row][col]) {
            return 0;
        }
        visited[row][col] = true;
        int count = 0;
        if (getDigitSum(row, col) <= k) {
            count += 1;
        }
        count += searchHelper(visited, row + 1, col, ROW, COL, k);
        count += searchHelper(visited, row, col + 1, ROW, COL, k);
        count += searchHelper(visited, row - 1, col, ROW, COL, k);
        count += searchHelper(visited, row, col - 1, ROW, COL, k);
        return count;
    }

    private static int getDigitSum(int a, int b) {
        int count = 0;
        while (a > 0) {
            count += a % 10;
            a = a / 10;
        }
        while (b > 0) {
            count += b % 10;
            b = b / 10;
        }
        return count;
    }
}
