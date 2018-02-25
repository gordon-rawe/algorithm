package com.lintcode.solves;

/**
 * Created by gordon on 2/24/18.
 */
public class Solution53 {

    public static void main(String[] args) {
        System.out.println(reverseWords("the sky is blue"));
    }

    private static String reverseWords(String s) {
        StringBuilder retValue = new StringBuilder();
        if (s == null || s.trim().length() == 0) return retValue.toString();
        String[] splits = s.split(" ");
        for (int i = splits.length - 1; i >= 1; i--) {
            if (!splits[i].equals("")) {
                retValue.append(splits[i]);
                retValue.append(" ");
            }
        }
        retValue.append(splits[0]);
        return retValue.toString();
    }
}
