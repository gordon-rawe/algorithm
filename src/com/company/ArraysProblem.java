package com.company;

/**
 * Created by gordon on 10/23/17.
 */
public class ArraysProblem {

    static int longestSubSequence(String X, String Y, int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (X.charAt(m - 1) == Y.charAt(n - 1)) {
            return longestSubSequence(X, Y, m - 1, n - 1) + 1;
        }
        return Math.max(longestSubSequence(X, Y, m - 1, n), longestSubSequence(X, Y, m, n - 1));
    }

    static int longestSubSequenceTable(String X, String Y, int m, int n) {
        int[][] table = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == 0 || j == 0) {
                    table[i][j] = 0;
                } else if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                } else {
                    table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
                }
            }
        }
        return table[m][n];
    }

    static int longestSubString(String X, String Y, int m, int n) {
        int[][] table = new int[m + 1][n + 1];
        int maxValue = 0;
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == 0 || j == 0) {
                    table[i][j] = 0;
                } else if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    int lastValue = table[i - 1][j - 1];
                    table[i][j] = lastValue + 1;
                    if(maxValue < table[i][j]) maxValue = table[i][j];
                } else {
                    table[i][j] = 0;
                }
            }
        }
        return maxValue;
    }

    public static void main(String[] args) {
        String X = "ABCBDABC";
        String Y = "BDCABAC";
        System.out.println(longestSubString(X, Y, X.length(), Y.length()));
    }
}
