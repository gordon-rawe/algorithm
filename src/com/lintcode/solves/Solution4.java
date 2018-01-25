package com.lintcode.solves;

/**
 * Created by gordon on 1/24/18.
 */
public class Solution4 {

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(9));
    }

    private static int nthUglyNumber(int n) {
        if (n <= 0) {
            return -1;
        }
        int i2 = 0, i3 = 0, i5 = 0;
        int[] history = new int[n];
        history[0] = 1;
        for (int i = 1; i < n; i++) {
            history[i] = min(history[i2] * 2, history[i3] * 3, history[i5] * 5);
            while (history[i2] * 2 <= history[i]) i2++;
            while (history[i3] * 3 <= history[i]) i3++;
            while (history[i5] * 5 <= history[i]) i5++;
        }
        return history[n - 1];
    }

    public static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
