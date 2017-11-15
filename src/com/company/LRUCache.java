package com.company;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private Node head = null, tail = head;
    private Map<Integer, Node> map = new HashMap<>();
    private int capacity = 0;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            remove(node);
            setHead(node);
            return node.value;
        }
        return -1;
    }

    public void setHead(Node node) {
        node.next = head;
        node.pre = null;
        if (head != null) head.pre = node;
        head = node;
        if (tail == null) tail = head;
    }

    public void remove(Node node) {
        if (node.pre != null) {
            node.pre.next = node.next;
        } else {
            head = node.next;
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        } else {
            tail = node.pre;
        }
    }

    public void set(int key, int value) {
        if (map.containsKey(key)) {
            Node old = map.get(key);
            old.value = value;
            remove(old);
            setHead(old);
        } else {
            Node node = new Node(key, value);
            if (map.size() >= capacity) {
                map.remove(tail.key);
                remove(tail);
                setHead(node);
            } else {
                setHead(node);
            }
            map.put(key, node);
        }
    }
}
