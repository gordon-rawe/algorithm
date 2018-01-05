package com.books.way2offer;

public class Solution42 {

    public static void main(String[] args) {
        String sample = "I am fucking right, how about you?";
        System.out.println(String.valueOf(reverseSentence(sample.toCharArray())));
    }

    private static char[] reverseSentence(char[] sentence) {
        assert sentence != null && sentence.length > 1;
        reverseRange(sentence, 0, sentence.length - 1);
        int pIndex = 0;
        while (pIndex < sentence.length) {
            if (sentence[pIndex] != ' ') {
                int pEnd = pIndex;
                while (pEnd + 1 < sentence.length && sentence[pEnd + 1] != ' ') pEnd++;
                reverseRange(sentence, pIndex, pEnd);
                pIndex = pEnd + 1;
            } else {
                pIndex++;
            }
        }
        return sentence;
    }

    private static void reverseRange(char[] words, int left, int right) {
        while (left < right) {
            swap(words, left++, right--);
        }
    }

    private static void swap(char[] words, int a, int b) {
        char tChar = words[a];
        words[a] = words[b];
        words[b] = tChar;
    }
}
