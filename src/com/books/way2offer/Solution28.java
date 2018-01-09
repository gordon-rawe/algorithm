package com.books.way2offer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gordon on 1/9/18.
 */
public class Solution28 {

    /*字符串的全排列*/

    public static void main(String[] args) {
        for (String hello : permutations("abc")) {
            System.out.println(hello);
        }
    }

    private static List<String> permutations(String string) {
        List<String> retValues = new ArrayList<>();
        if (string.length() == 0) {
            retValues.add("");
            return retValues;
        }
        char curChar = string.charAt(0);
        for (String itemString : permutations(string.substring(1))) {
            for (int i = 0; i <= itemString.length(); i++) {
                String prefix = itemString.substring(0, i);
                String suffix = itemString.substring(i);
                retValues.add(prefix + curChar + suffix);
            }
        }
        return retValues;
    }
}
