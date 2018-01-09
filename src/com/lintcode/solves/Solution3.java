package com.lintcode.solves;

/**
 * Created by gordon on 1/3/18.
 */
public class Solution3 {

    /**
     * Count the number of k’s between 0 and n. k can be 0 - 9.
     * */

    public static void main(String[] args) {
        System.out.println(digitCounts(1, 13));
    }

    /**
     * 有点看不懂
     * //todo need to be remembered
     * */
    private static int digitCounts(int k, int n) {
        int count = 0;
        int base = 1;
        int flag = 1;
        if (k == 0 && n == 0) {
            return 1;
        }
        if (k == 0) {
            /*求0的相当于求10的，要进行转化*/
            flag = 10;
        }
        while (n / base >= flag) {
            int curBit = n % (base * 10) / base;
            int higer = n / (base * 10);
            int lower = n % base;
            if (curBit < k) {
                count += base * higer;
            } else if (curBit == k) {
                count += 1 + base * higer + lower;
            } else {
                count += base * (higer + 1);
            }
            base *= 10;
        }
        return count;
    }
}
