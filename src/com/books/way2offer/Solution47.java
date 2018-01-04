package com.books.way2offer;

/**
 * Created by gordon on 1/4/18.
 */
public class Solution47 {

    /*不用加减乘除做加法*/

    public static void main(String[] args) {
        System.out.println(opAdd(1,10));
    }

    /**
     * 先做不进位的加法a^b,再专门加上要进的位的结果(a & b) << 1,两者相加为结果,
     * 当需要进位的结果为0的时候，那么另外一方刚好为两者的和。
     * */
    private static int opAdd(int a, int b) {
        while (b != 0) {
            int _a = a ^ b;
            int _b = (a & b) << 1;
            a = _a;
            b = _b;
        }
        return a;
    }
}
