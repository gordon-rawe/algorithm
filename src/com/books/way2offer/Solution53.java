package com.books.way2offer;

/**
 * Created by gordon on 1/3/18.
 */
public class Solution53 {

    /*正则表达式*/

    public static void main(String[] args) {
        System.out.println(regexMatch("b","a*b"));
    }

    public static boolean regexMatch(String s, String p) {
        assert s != null && p != null;
        int lenS = s.length(), lenP = p.length();
        boolean[][] dpTable = new boolean[lenS + 1][lenP + 1];
        dpTable[0][0] = true;
        for (int j = 1; j <= lenP; j++) {
            dpTable[0][j] = j > 1 && p.charAt(j - 1) == '*' && dpTable[0][j - 2];
        }
        for (int i = 1; i <= lenS; i++) {
            for (int j = 1; j <= lenP; j++) {
                if (p.charAt(j - 1) == '*' && j > 1) {
                    dpTable[i][j] = dpTable[i][j - 2] ||
                            (dpTable[i - 1][j] &&
                                    (s.charAt(i - 1) == p.charAt(j - 2) ||
                                            p.charAt(j - 2) == '.'));
                } else {
                    dpTable[i][j] = dpTable[i - 1][j - 1] &&
                            (s.charAt(i - 1) == p.charAt(j - 1) ||
                                    '.' == p.charAt(j - 1));
                }
            }
        }
        return dpTable[lenS][lenP];
    }
}
