package com.books.way2offer;

/**
 * Created by gordon on 1/13/18.
 */
public class Solution66 {

    /*矩阵中是否包含字符串的路径*/

    public static void main(String[] args) {
        char[][] matrix = new char[][]{
                new char[]{'a', 'b', 'c', 'd'},
                new char[]{'a', 'b', 'c', 'd'},
                new char[]{'a', 'b', 'c', 'd'},
                new char[]{'a', 'b', 'c', 'd'},
        };
        System.out.println(searchInMatrix(matrix, "abcde"));
    }

    private static boolean searchInMatrix(char[][] matrix, String t) {
        assert matrix != null && matrix.length > -0 && matrix[0].length > 0 && t != null && t.length() > 0;
        int ROW = matrix.length, COL = matrix[0].length;
        boolean[][] visited = new boolean[ROW][COL];
        return searchHelper(matrix, visited, 0, 0, ROW, COL, 0, t);
    }

    private static boolean searchHelper(char[][] matrix, boolean[][] visited,
                                        int row, int col, int ROW, int COL, int curIndex, String t) {
        if (curIndex == t.length()) {
            return true;
        }
        if (row < 0 || row >= ROW || col < 0 || col >= COL
                || visited[row][col] || curIndex >= t.length() || t.charAt(curIndex) != matrix[row][col]) {
            return false;
        }
        visited[row][col] = true;
        boolean retValue =
                searchHelper(matrix, visited, row + 1, col, ROW, COL, curIndex + 1, t) ||
                        searchHelper(matrix, visited, row, col + 1, ROW, COL, curIndex + 1, t) ||
                        searchHelper(matrix, visited, row - 1, col, ROW, COL, curIndex + 1, t) ||
                        searchHelper(matrix, visited, row, col - 1, ROW, COL, curIndex + 1, t);
        visited[row][col] = false;
        return retValue;
    }
}
