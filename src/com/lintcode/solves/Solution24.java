package com.lintcode.solves;

import java.util.HashMap;
import java.util.Map;

public class Solution24 {

    public static class Node {
        Node prev;
        Node next;
        int frequency;
        int key;
        public int value;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.frequency = 1;
        }
    }

    public static class LFUCache {

        Node head = new Node(0, 0), tail = new Node(0, 0);
        Map<Integer, Node> data = new HashMap<>();
        int capacity = 8;

        LFUCache(int capacity) {
            this.capacity = capacity;
            head.next = tail;
            tail.next = head;
        }

        private void moveNode(Node node) {

        }

        private void remove(Node node) {

        }

        public void set(int key, int value) {
            if (data.containsKey(key)) {
                Node oldNode = data.get(key);
                oldNode.value = value;
                oldNode.frequency++;
                moveNode(oldNode);
            } else {
                Node newNode = new Node(key, value);
                if (data.size() >= capacity) {
                    data.remove(tail.key);
                    remove(tail);
                }
                data.put(key, newNode);
                moveNode(newNode);
            }
        }

        public int get(int key) {
            if (data.containsKey(key)) {
                Node node = data.get(key);
                remove(node);
                moveNode(node);
                return node.value;
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(3);
        cache.set(2, 2);
        cache.set(1, 1);
        System.out.println(cache.get(2));
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        cache.set(3, 3);
        cache.set(4, 4);
        System.out.println(cache.get(3));
        System.out.println(cache.get(2));
        System.out.println(cache.get(1));
        System.out.println(cache.get(4));
    }
}
