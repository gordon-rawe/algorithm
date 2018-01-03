package com.books.way2offer;

/**
 * Created by gordon on 1/2/18.
 */
public class Solution52 {
    /*
    * 给定一个数组 A[0,1,...,n-1]，请构建一个数组 B[0,1,...,n-1]。
    * 其中B中的元素B[i]=A[0]∗A[1]∗...∗A[i−1]∗A[i+1]∗ ... ∗A[n−1]。
    * 不能使用除法。
    * */

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3};
        for (int i : calcMatrixEfficient(a)) {
            System.out.println(i);
        }
    }

    private static int[] calcMatrixEfficient(int[] a) {
        if (a == null || a.length == 0) return a;
        int LEN = a.length;
        int[] c = new int[LEN];
        c[0] = 1;
        for (int i = 1; i < LEN; i++) {
            c[i] = c[i - 1] * a[i - 1];
        }
        int[] d = new int[LEN];
        d[LEN - 1] = 1;
        for (int i = LEN - 2; i >= 0; i--) {
            d[i] = d[i + 1] * a[i + 1];
        }
        int[] retArr = new int[LEN];
        for (int i = 0; i < LEN; i++) {
            retArr[i] = c[i] * d[i];
        }
        return retArr;
    }
}
