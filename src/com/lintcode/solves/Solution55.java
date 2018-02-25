package com.lintcode.solves;

/**
 * Created by gordon on 2/24/18.
 */
public class Solution55 {

    public static void main(String[] args) {

    }

    public boolean compareStrings(String A, String B) {
        if (A == null) {
            return false;
        }
        if (B == null) {
            return true;
        }
        int[] records = new int[26];
        for (int i = 0; i < A.length(); i++) {
            records[A.charAt(i) - 'A']++;
        }
        for (int i = 0; i < B.length(); i++) {
            records[B.charAt(i) - 'A']--;
        }
        for (int i = 0; i < records.length; i++) {
            if(records[i] < 0) {
                return false;
            }
        }
        return true;
    }
}
