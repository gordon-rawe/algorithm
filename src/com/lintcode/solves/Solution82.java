package com.lintcode.solves;

/**
 * Created by gordon on 2/26/18.
 */
public class Solution82 {

    public static void main(String[] args) {
        System.out.println(0 ^ 4);
        System.out.println();
    }

    public int singleNumber(int[] A) {
        if (A == null || A.length < 1) {
            return -1;
        }
        int ret = A[0];
        for (int i = 1; i < A.length; i++) {
            ret ^= A[i];
        }
        return ret;
    }
}
