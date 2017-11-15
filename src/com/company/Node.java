package com.company;

/**
 * Created by gordon on 10/23/17.
 */
class Node {
    int key;
    int value;
    Node pre;
    Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
