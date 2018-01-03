package com.lintcode.solves;

/**
 * Created by gordon on 1/3/18.
 */
public class Solution2 {
    /**
     * An algorithm which computes the number of trailing zeros in n factorial.
     */

    public static void main(String[] args) {
        System.out.println(trailingZeros(25));
    }

    public static long trailingZeros(long n) {
        long count = 0;
        for (int i = 1; Math.pow(5, i) <= n; i++) {
            count += n / (long) Math.pow(5, i);//注意强制类型转换
        }
        return count;
    }
}
