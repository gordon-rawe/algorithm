package com.lintcode.solves;

import java.util.*;

/**
 * Created by gordon on 3/1/18.
 */
public class Solution120 {

    public static void main(String[] args) {
        System.out.println(ladderLength("a", "c", new HashSet<>(Arrays.asList("a", "b", "c"))));
    }

    private static int ladderLength(String start, String end, Set<String> dict) {
        Queue<WordNode> queue = new LinkedList<>();
        queue.add(new WordNode(start, 1));
        dict.add(end);
        while (!queue.isEmpty()) {
            WordNode topNode = queue.remove();
            if (end.equals(topNode.word)) {
                return topNode.stepCount;
            }
            char[] chars = topNode.word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    char tmpValue = chars[i];
                    chars[i] = c;
                    String word = new String(chars);
                    if (dict.contains(word)) {
                        queue.add(new WordNode(word, topNode.stepCount + 1));
                        dict.remove(word);
                    }
                    chars[i] = tmpValue;
                }
            }
        }
        return 0;
    }

    static class WordNode {
        String word;
        int stepCount;

        public WordNode(String word, int stepCount) {
            this.word = word;
            this.stepCount = stepCount;
        }
    }
}


