package com.books.way2offer;

/**
 * Created by gordon on 1/7/18.
 */
public class Solution35 {

    /*第一个只出现一次的字符*/

    public static void main(String[] args) {
        char[] sample = "gxxoogllee".toCharArray();
        System.out.println(firstNoneRepeatChar(sample));
    }

    private static char firstNoneRepeatChar(char[] target) {
        int[] records = new int[256];
        for (int i = 0; i < target.length; i++) {
            records[target[i]]++;
        }
        for (int i = 0; i < target.length; i++) {
            if (records[target[i]] == 1) {
                return target[i];
            }
        }
        return ' ';
    }
}
