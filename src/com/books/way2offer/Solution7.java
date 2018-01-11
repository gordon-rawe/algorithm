package com.books.way2offer;

import java.util.Stack;

/**
 * Created by gordon on 1/11/18.
 */
public class Solution7 {

    /*使用两个栈实现一个队列*/

    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.appendTail(1);
        queue.appendTail(2);
        queue.appendTail(3);
        queue.appendTail(4);
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        queue.appendTail(5);
        queue.appendTail(6);
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        queue.appendTail(8);
        queue.appendTail(7);
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
    }

    static class Queue {
        Stack<Integer> left = new Stack<>();
        Stack<Integer> right = new Stack<>();

        void appendTail(int value) {
            left.push(value);
        }

        Integer deleteHead() {
            if (!right.isEmpty()) {
                return right.pop();
            }
            if (left.isEmpty()) {
                return null;
            }
            while (left.size() > 0) {
                right.push(left.pop());
            }
            return right.pop();
        }
    }
}
