package com.lintcode.solves;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gordon on 1/24/18.
 */
public class Solution9 {

    public static void main(String[] args) {
        for (String s : fizzBuzz(100)) {
            System.out.println(s);
        }
    }

    private static List<String> fizzBuzz(int n) {
        List<String> results = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                results.add("fizz buzz");
            } else if (i % 5 == 0) {
                results.add("buzz");
            } else if (i % 3 == 0) {
                results.add("fizz");
            } else {
                results.add(String.valueOf(i));
            }
        }
        return results;
    }
}
