package com.lintcode.solves;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Solution24 {

    public static class Node {
        Node prev;
        Node next;
        int key;
        public int value;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static class LFUCache {

        Node head, tail;
        Map<Integer, Node> data = new HashMap<>();
        int capacity = 8;

        LFUCache(int capacity) {
            this.capacity = capacity;
        }

        private void setHead(Node node) {
            node.next = head;
            node.prev = null;
            if (head != null) {
                head.prev = node;
            }
            head = node;
            if (tail == null) {
                tail = head;
            }
        }

        private void remove(Node node) {
            if (node.prev != null) {
                node.prev.next = node.next;
            } else {
                head = node.next;
            }
            if (node.next != null) {
                node.next.prev = node.prev;
            } else {
                tail = node.prev;
            }
        }

        void set(int key, int value) {
            if (data.containsKey(key)) {
                Node oldNode = data.get(key);
                oldNode.value = value;
                remove(oldNode);
                setHead(oldNode);
            } else {
                Node newNode = new Node(key, value);
                if (data.size() >= capacity) {
                    data.remove(tail.key);
                    remove(tail);
                }
                data.put(key, newNode);
                setHead(newNode);
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

        Set<Integer> getKeys() {
            return data.keySet();
        }
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(3);
        for (int i = 0; i < 10; i++) {
            cache.set(i, i);
        }
        for (Integer integer : cache.getKeys()) {
            System.out.println(integer);
        }
    }
}
