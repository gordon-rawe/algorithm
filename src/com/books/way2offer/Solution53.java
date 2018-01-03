package com.books.way2offer;

/**
 * Created by gordon on 1/3/18.
 */
public class Solution53 {

    /*正则表达式*/

    public static void main(String[] args) {
        System.out.println(regexMatch("b", "a*b"));
    }

    /**
     * 构建一个boolean矩阵，非空串与空串模式匹配为false，空串与非空串模式匹配，主要看是否能构成x*的循环，
     * 那么条件是j > 1 && dpTable[0][j - 2]。
     * 在非空串的匹配中，分两种，一种是当前字符是*，这种情况dpTable[i][j-2]表示x*匹配空串，之前的ok，还
     * 有当x*的确匹配上，那就需要dpTable[i - 1][j] && (s.charAt(i - 1) == p.charAt(j - 2) ||
     * p.charAt(j - 2) == '.')；另外一种是非*字符，dpTable[i - 1][j - 1] &&
     * (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')
     */
    private static boolean regexMatch(String s, String p) {
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
