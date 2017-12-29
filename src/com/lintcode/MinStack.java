package com.lintcode;

import java.util.Stack;

/**
 * Created by books on 11/4/17.
 */
public class MinStack {
    Stack<Integer> data = new Stack<>();
    Stack<Integer> min = new Stack<>();

    public void push(int value) {
        data.push(value);
        if (min.size() == 0 || min.firstElement() > value) {
            min.push(value);
        } else {
            min.push(min.firstElement());
        }
    }

    public void pop() {
        assert data.size() > 0 && min.size() > 0;
        data.pop();
        min.pop();
    }

    public int top() {
        assert data.size() > 0 && min.size() > 0;
        return min.firstElement();
    }
}
