package com.books.way2offer;

import java.util.Arrays;

/**
 * Created by gordon on 12/29/17.
 */
public class Solution55 {

    /**
     * 字符流中第一个不重复的字符
     */

    private Solution55() {
        Arrays.fill(records, -1);
    }

    private int[] records = new int[256];

    private int curIndex = 0;

    private void insert(char ch) {
        records[ch] = records[ch] == -1 ? curIndex : -2;
        curIndex++;
    }

    private char getFirstOnce() {
        char retCh = '*';
        int minIndex = records.length;
        for (int i = 0; i < 256; i++) {
            if (records[i] >= 0 && i < minIndex) {
                retCh = (char) i;
                minIndex = i;
            }
        }
        return retCh;
    }

    public static void main(String[] args) {
        Solution55 solution = new Solution55();
        solution.insert('g');
        System.out.println(solution.getFirstOnce());
        solution.insert('o');
        System.out.println(solution.getFirstOnce());
        solution.insert('o');
        System.out.println(solution.getFirstOnce());
        solution.insert('g');
        System.out.println(solution.getFirstOnce());
        solution.insert('l');
        System.out.println(solution.getFirstOnce());
    }
}
