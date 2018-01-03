package com.lintcode.solves;

/**
 * Created by gordon on 1/3/18.
 */
public class Solution1 {
    /* Write a function that add two numbers A and B.
    * You should not use + or any arithmetic operators.*/

    public static void main(String[] args) {
        System.out.println(plus(123, 321));
    }

    /**
     * 可以这么理解，每个加法分为两部分相加，a^b表示不需要进位的部分的结果，
     * (a & b) << 1表示先取a和b的同时为1的部分然后进一位，结果是需要将这
     * 两部分相加，由于避免使用加法，那么我们可以等其中的某个数值退化为0，另
     * 外的数字便是结果，在这里对b进行观察便可得到结果。
     * */
    private static int plus(int a, int b) {
        while (b != 0) {
            /*找出不需要进位的结果*/
            int _a = a ^ b;
            /*找出同时为1的数位进位，模拟进位*/
            int _b = (a & b) << 1;
            a = _a;
            b = _b;
        }
        return a;
    }
}
