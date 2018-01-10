package com.books.way2offer;

/**
 * Created by gordon on 1/10/18.
 */
public class Solution11 {

    /*数字的整数次方*/

    public static void main(String[] args) {
        System.out.println(pow(2,4));
    }

    // TODO: 1/11/18  practise
    private static double pow(double base, int k) {
        double retValue = 1.0;
        while (k > 0) {
            if (k % 2 == 1) retValue *= base;
            base *= base;
            k = k / 2;
        }
        return retValue;
    }
}
