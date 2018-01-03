package com.lintcode.vintage.lru;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    Node head = null, tail = head;
    Map<Integer, Node> data = new HashMap<>();
    int capacity = 8;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public void set(int key, int value) {
        if (data.containsKey(key)) {
            Node old = data.get(key);
            old.value = value;
            remove(old);
            setHead(old);
        } else {
            Node newNode = new Node(key, value);
            if (capacity <= data.size()) {
                remove(tail);
                data.remove(tail.key);
            }
            setHead(newNode);
            data.put(key, newNode);
        }
    }

    public int get(int key) {
        if (data.containsKey(key)) {
            Node node = data.get(key);
            remove(node);
            setHead(node);
            return node.value;
        }
        return -1;
    }

    private void setHead(Node node) {
        node.next = head;
        node.pre = null;
        if (head != null) head.pre = node;
        head = node;
        if (tail == null) tail = head;
    }

    private void remove(Node node) {
        if (node.pre != null) {
            node.pre.next = node.next;
        } else {
            head = node.next;
        }
        if(node.next != null) {
            node.next.pre = node.pre;
        } else {
            tail = node.pre;
        }
    }
}
