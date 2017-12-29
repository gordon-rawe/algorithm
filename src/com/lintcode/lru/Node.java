package com.lintcode.lru;

/**
 * Created by books on 10/23/17.
 */
class Node {
    int key;
    int value;
    Node pre;
    Node next;

    Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
