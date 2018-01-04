package com.books.way2offer;

/**
 * Created by gordon on 1/4/18.
 */
public class Solution45 {

    /*约瑟夫环问题
    * 有n个人，从第一个人开始，数数，数到k的人退出，剩下的人循环数数，重复操作
    * 求最后剩下的那个人
    * // todo to be remembered
    * 数学公式:
    * f(n,k) = 0                        when n = 1
    *          (f(n - 1, k) + k) % n    when n > 1
    * */

    public static void main(String[] args) {
        System.out.println(lastRemain(14,2));
    }


    private static int lastRemain(int n, int k) {
        assert n >= 1 && k >= 1;
        /*当步长为1，剩下的为第一个数*/
        if (n == 1) return 0;
        return (lastRemain(n - 1, k) + k) % n;
    }
}
