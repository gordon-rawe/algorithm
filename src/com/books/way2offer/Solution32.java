package com.books.way2offer;

/**
 * Created by gordon on 1/9/18.
 */
public class Solution32 {

    /*0 -> n的整数中包含k(0 -> 9)的个数*/

    public static void main(String[] args) {
        System.out.println(digitCounts(1, 11));
    }

    /**
     * 数学公式：
     * 当curBit < k -> base * higher
     * 当curBit = k -> base * higher + 1 + low
     * 当curBit > k -> base * (higher + 1)
     */
    private static int digitCounts(int k, int n) {
        if (k == 0 && n == 0) {
            return 1;
        }
        int count = 0;
        int base = 1;
        int flag = 1;
        if (n == 0) {
            flag = 10;
        }
        while (n / base >= flag) {
            int curBit = n % (base * 10) / base;
            int higher = n / (base * 10);
            int lower = n % base;
            if (curBit < k) {
                count += base * higher;
            } else if (curBit == k) {
                count += 1 + (base * higher) + lower;
            } else {
                count += base * (higher + 1);
            }
            base *= 10;
        }
        return count;
    }
}
