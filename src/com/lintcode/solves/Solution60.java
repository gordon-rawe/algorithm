package com.lintcode.solves;

/**
 * Created by gordon on 2/25/18.
 */
public class Solution60 {

    public static int searchInsert(int[] A, int target) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int position = 0;
        while (position < A.length && A[position] < target) position++;
        return position;
    }

    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 7));
    }
}
