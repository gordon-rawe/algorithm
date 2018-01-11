package com.books.way2offer;

/**
 * Created by gordon on 1/11/18.
 */
public class Solution10 {

    /*整数的二进制表示中1的位数*/

    public static void main(String[] args) {
        System.out.println(numOf1Bits(437289));
    }

    private static int numOf1Bits(int num) {
        int count = 0;
        while (num != 0) {
            num = num & (num - 1);
            count++;
        }
        return count;
    }
}
