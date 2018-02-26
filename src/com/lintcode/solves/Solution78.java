package com.lintcode.solves;

/**
 * Created by gordon on 2/25/18.
 */
public class Solution78 {

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{
                "a",
                "abcde"
        }));
    }

    private static String longestCommonPrefix(String[] strs) {
        String retValue = "";
        if (strs == null || strs.length == 0) {
            return retValue;
        }
        int endPosition = 0;
        while (isValid(strs, endPosition)) {
            endPosition++;
        }
        return strs[0] != null && strs[0].length() >= endPosition ? strs[0].substring(0, endPosition) : "";
    }

    private static boolean isValid(String[] strs, int endPosition) {
        if (strs[0] == null || endPosition >= strs[0].length()) {
            return false;
        }
        char tmpChar = strs[0].charAt(endPosition);
        for (String str : strs) {
            if (str == null || str.length() <= endPosition || str.charAt(endPosition) != tmpChar) {
                return false;
            }
        }
        return true;
    }
}
