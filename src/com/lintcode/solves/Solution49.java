package com.lintcode.solves;

/**
 * Created by gordon on 2/23/18.
 */
public class Solution49 {

    public static void main(String[] args) {
        char[] rawString = "abAcD".toCharArray();
        sortLetters(rawString);
        System.out.println(rawString);
    }

    private static void sortLetters(char[] chars) {
        if(chars == null || chars.length == 0) {
            return;
        }
        int start = 0, end = chars.length - 1;
        while (start < end) {
            while (start < end && Character.isLowerCase(chars[start])) start++;
            while (start < end && Character.isUpperCase(chars[end])) end--;
            if (start < end) {
                char tmpValue = chars[start];
                chars[start] = chars[end];
                chars[end] = tmpValue;
            }
        }
    }
}
