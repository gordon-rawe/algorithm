package com.books.way2offer;

/**
 * Created by gordon on 1/7/18.
 */
public class Solution34 {

    /*第n个丑数，丑数是指只由2、3、5作为因数的数字*/

    public static void main(String[] args) {
        System.out.println(kThUglyNumber(100));
    }

    private static int kThUglyNumber(int k) {
        if (k <= 0) {
            return -1;
        }
        int[] uglyNumbers = new int[k + 1];
        uglyNumbers[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        for (int i = 1; i <= k; i++) {
            uglyNumbers[i] = min(uglyNumbers[i2] * 2, uglyNumbers[i3] * 3, uglyNumbers[i5] * 5);
            while (uglyNumbers[i2] * 2 <= uglyNumbers[i]) i2++;
            while (uglyNumbers[i3] * 3 <= uglyNumbers[i]) i3++;
            while (uglyNumbers[i5] * 5 <= uglyNumbers[i]) i5++;
        }
        return uglyNumbers[k];
    }

    private static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
