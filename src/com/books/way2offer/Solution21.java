package com.books.way2offer;

import java.util.Stack;

/**
 * Created by gordon on 1/10/18.
 */
public class Solution21 {

    /*包含min函数的栈*/

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.add(4);
        System.out.println(minStack.min());
        minStack.add(5);
        System.out.println(minStack.min());
        minStack.add(3);
        System.out.println(minStack.min());
        minStack.add(9);
        System.out.println(minStack.min());
        minStack.pop();
        System.out.println(minStack.min());
        minStack.pop();
        System.out.println(minStack.min());
    }

    /**
     * 两个栈，一个普通栈维护数据，另一个栈用于记录最小的值，相互协作
     * */
    static class MinStack {

        Stack<Integer> dataStack = new Stack<>();
        Stack<Integer> minStack = new Stack<>();

        void add(Integer value) {
            dataStack.push(value);
            if (minStack.isEmpty() || minStack.peek() > value) {
                minStack.push(value);
            } else {
                minStack.push(minStack.peek());
            }
        }

        Integer pop() {
            minStack.pop();
            return dataStack.pop();
        }

        Integer min() {
            return minStack.peek();
        }
    }
}
