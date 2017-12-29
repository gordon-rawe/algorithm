package com.lintcode;

import java.util.*;

/**
 * Created by books on 11/4/17.
 */
public class DoubleQuque {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public DoubleQuque() {
        // do intialization if necessary
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /*
     * @param element: An integer
     * @return: nothing
     */
    public void push(int element) {
        stack1.push(element);
    }

    /*
     * @return: An integer
     */
    public int pop() {
        // write your code here
        if (stack2.size() == 0) {
            moveElements();
        }
        return stack2.pop();
    }

    /*
     * @return: An integer
     */
    public int top() {
        // write your code here
        if (stack2.size() == 0) {
            moveElements();
        }
        return stack2.peek();
    }

    private void moveElements() {
        while (stack1.size() > 0) {
            stack2.push(stack1.pop());
        }
    }

    public static void main(String[] args) {
        List<Stack<TreeNode>> helper = java.util.Arrays.asList(new Stack<TreeNode>(), new Stack<TreeNode>());
    }

    public ArrayList<Integer> maxSlidingWindow(int[] nums, int size) {
        ArrayList<Integer> maxWindows = new ArrayList<>();
        if (nums.length < size || size <= 0) return maxWindows;
        Deque<Integer> indexes = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            while (!indexes.isEmpty() && nums[i] > nums[indexes.getLast()]) indexes.pollLast();
            indexes.addLast(i);
        }
        for (int i = size; i < nums.length; i++) {
            maxWindows.add(nums[indexes.getFirst()]);
            while (!indexes.isEmpty() && nums[i] > nums[indexes.getLast()]) indexes.pollLast();
            while (!indexes.isEmpty() && indexes.getFirst() <= i - size) indexes.pollFirst();
            indexes.addLast(i);
        }
        maxWindows.add(nums[indexes.getFirst()]);
        return maxWindows;
    }
}
