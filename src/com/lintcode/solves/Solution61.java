package com.lintcode.solves;

/**
 * Created by gordon on 2/25/18.
 */
public class Solution61 {

    public static void main(String[] args) {

    }

    public int[] searchRange(int[] A, int target) {
        int start = -1, end = -1;
        if (A == null || A.length == 0) {
            return new int[]{start, end};
        }
        for (int i = 0; i < A.length; i++) {
            if (A[i] == target) {
                start = start == -1 ? i : start;
                end = start == -1 ? start : i;
            }
        }
        return new int[]{start, end};
    }
}
