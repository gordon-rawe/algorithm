package com.books.way2offer;

/**
 * Created by gordon on 1/11/18.
 */
public class Solution9 {

    /**求fibonacci数列的第k个数字*/

    public static void main(String[] args) {
        System.out.println(fibonacci(1000));
    }

    /**如果采用递归会有效率问题，历史值被丢弃，应该采用动态规划*/
    private static double fibonacci(int k) {
        if (k <= 0) return -1;
        if (k == 1) return 1;
        if (k == 2) return 2;
        double base1 = 1, base2 = 2;
        for (int i = 2; i < k; i++) {
            double next = base1 + base2;
            base1 = base2;
            base2 = next;
        }
        return base2;
    }
}
