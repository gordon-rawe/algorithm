package com.lintcode;

/**
 * Created by books on 12/14/17.
 */

class DictNode {
    char val;
    int min;
    DictNode[] branches;

    DictNode(char val) {
        this.val = val;
        branches = new DictNode[26];
    }
}

public class WordDictionary {
    DictNode root;

    private WordDictionary() {
        root = new DictNode(' ');
    }

    private void addWord(String word) {
        if (word == null || word.length() == 0) return;
        DictNode pNode = root;
        int curMin = word.length() - 1;
        for (int i = 0; i < word.length(); i++) {
            char curChar = word.charAt(i);
            int bucket = curChar - 'a';
            if (pNode.branches[bucket] == null) {
                pNode.branches[bucket] = new DictNode(curChar);
                pNode.branches[bucket].min = curMin;
            } else if (curMin < pNode.branches[bucket].min) {
                pNode.branches[bucket].min = curMin;
            }
            pNode = pNode.branches[bucket];
            curMin--;
        }
    }

    private boolean search(String word) {
        return searchTree(word, root);
    }

    private boolean searchTree(String word, DictNode node) {
        if (word.length() == 0) {
            return node.min <= 0;
        }
        char curChar = word.charAt(0);
        if (curChar == '.') {
            for (int i = 0; i < node.branches.length; i++) {
                if (searchTree(word.substring(1), node.branches[i])) {
                    return true;
                }
            }
            return false;
        } else {
            int bucket = curChar - 'a';
            return node.branches[bucket] != null && node.branches[bucket].val == curChar
                    && searchTree(word.substring(1), node.branches[bucket]);
        }
    }

    public static void main(String[] args) {
        WordDictionary dict = new WordDictionary();
        dict.addWord("books");
        dict.addWord("gordonx");
        dict.addWord("rawe");
        System.out.println(dict.search("books"));
    }
}
