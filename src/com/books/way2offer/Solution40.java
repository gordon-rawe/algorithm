package com.books.way2offer;

/**
 * Created by gordon on 1/5/18.
 */
public class Solution40 {

    /*一组数字里面可能有两个不成对的数字，我们要找出这两个数字*/

    public static void main(String[] args) {
        printDistinctNumbers(new int[]{1, 2, 0, 1, 24, 0});
    }

    private static void printDistinctNumbers(int[] nums) {
        int xorResult = 0;
        for (int i = 0; i < nums.length; i++) {
            xorResult ^= nums[i];
        }
        int oneBit = 1;
        while ((oneBit & xorResult) == 0) {
            oneBit <<= 1;
        }
        int x = 0, y = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & oneBit) == 0) {
                x ^= nums[i];
            } else {
                y ^= nums[i];
            }
        }
        System.out.println("x -> " + x + " y -> " + y);
    }
}
