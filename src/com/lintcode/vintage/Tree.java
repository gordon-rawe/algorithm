package com.lintcode.vintage;

import java.util.*;

/**
 * Created by books on 10/30/17.
 */
public class Tree {

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0) {
            int count = queue.size();
            List<Integer> oneLayer = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                oneLayer.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            result.add(oneLayer);
        }
        return result;
    }

    public static String addBinary(String a, String b) {
        if (a == null || a.length() == 0) return b;
        if (b == null || b.length() == 0) return a;
        int lenA = a.length() - 1, lenB = b.length() - 1;
        int mod = 0;
        StringBuilder builder = new StringBuilder();
        while (lenA >= 0 || lenB >= 0 || mod > 0) {
            int left = lenA >= 0 && a.charAt(lenA--) == '1' ? 1 : 0;
            int right = lenB >= 0 && b.charAt(lenB--) == '1' ? 1 : 0;
            builder.append((left + right + mod) % 2 == 0 ? '0' : '1');
            mod = (left + right + mod) / 2;
        }
        return builder.reverse().toString();
    }

    public int maxPathSum(TreeNode root) {
        int[] maxResult = new int[]{Integer.MIN_VALUE};
        searchHelper(root, 0, maxResult);
        return maxResult[0];
    }

    private static int searchHelper(TreeNode node, int lastSum, int[] maxResult) {
        if (node == null) return lastSum;
        int leftSum = searchHelper(node.left, lastSum, maxResult);
        int rightSum = searchHelper(node.right, lastSum, maxResult);
        //全局最优可以单独考虑加上左边，加上右边或者都不取
        int thisMax = node.val + Math.max(leftSum, 0) + Math.max(rightSum, 0);
        maxResult[0] = Math.max(thisMax, maxResult[0]);
        //考虑到负数和不要求到底的情况，那么我们可以选择在当前节点终止，也可以加上左边或右边，取最小值,三个选一
        return Math.max(0, Math.max(leftSum, rightSum)) + node.val;
    }

    static int counter = 0;

    private static TreeNode kThNode(TreeNode root, int k) {
        if (root == null || k == 0) {
            return null;
        }
        return core(root, k);
    }

    /**
     * TODO: 12/14/17  二叉树的第k个节点
     */
    private static TreeNode core(TreeNode node, int k) {
        TreeNode retNode = null;
        if (node.left != null) {
            retNode = core(node.left, k);
        }
        //we still don't get the node
        if (retNode == null) {
            counter++;
            if (counter == k) {
                retNode = node;
            }
        }
        //if we still don't find the node, keep searching, or avoid unnecessary steps.
        if (retNode == null && node.right != null) {
            retNode = core(node.right, k);
        }
        return retNode;
    }

    public static TreeNode successor(TreeNode node) {
        if (node == null) return null;
        TreeNode retNode = null;
        if (node.right != null) {
            TreeNode rightNode = node.right;
            while (rightNode.left != null) {
                rightNode = rightNode.left;
            }
            retNode = rightNode;
        } else if (node.parent != null) {
            TreeNode parentNode = node.parent;
            TreeNode curNode = node;
            while (parentNode != null && parentNode.right == curNode) {
                curNode = parentNode;
                parentNode = parentNode.parent;
            }
            retNode = parentNode;
        }
        return retNode;
    }

    /**
     * TODO: 12/14/17使用stack不断回顾
     */
    private static void printNextGreaterElement(int[] nums) {
        if (nums == null || nums.length == 0) return;
        Stack<Integer> stack = new Stack<>();
        stack.push(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            while (nums[i] > stack.peek()) {
                System.out.println(stack.pop() + " -> " + nums[i]);
            }
            stack.push(nums[i]);
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop() + " -> null");
        }
    }

    private static void layerPrint(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        while (stack.size() > 0) {
            int curSize = stack.size();
            for (int i = 0; i < curSize; i++) {
                TreeNode node = stack.poll();
                System.out.print(node.val);
                if (node.left != null) stack.add(node.left);
                if (node.right != null) stack.add(node.right);
            }
            System.out.println();
        }
    }

    private static void layerPrintRight(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        while (stack.size() > 0) {
            int curSize = stack.size();
            for (int i = 0; i < curSize; i++) {
                TreeNode node = stack.poll();
                System.out.print(node.val);
                if (node.right != null) stack.add(node.right);
                if (node.left != null) stack.add(node.left);
            }
            System.out.println();
        }
    }

    private static void zigZagPrint(TreeNode root) {
        Stack<TreeNode>[] stacks = new Stack[]{new Stack<TreeNode>(), new Stack<TreeNode>()};
        int workLayer = 0, nextLayer = 1;
        stacks[workLayer].push(root);
        while (stacks[workLayer].size() > 0) {
            int curSize = stacks[workLayer].size();
            for (int i = 0; i < curSize; i++) {
                TreeNode node = stacks[workLayer].pop();
                if (workLayer == 0) {
                    if (node.left != null) stacks[nextLayer].push(node.left);
                    if (node.right != null) stacks[nextLayer].push(node.right);
                } else {
                    if (node.right != null) stacks[nextLayer].push(node.right);
                    if (node.left != null) stacks[nextLayer].push(node.left);
                }
                System.out.print(node.val);
            }
            System.out.println();
            workLayer = 1 - workLayer;
            nextLayer = 1 - nextLayer;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(8);
        zigZagPrint(root);
    }
}
