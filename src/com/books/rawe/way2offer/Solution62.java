package com.books.rawe.way2offer;

import com.books.rawe.datastructure.tree.TreeNode;
import com.books.rawe.datastructure.tree.TreeUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by books on 12/21/17.
 */
public class Solution62 {


    public static void main(String[] args) {
        String value = serialize(TreeUtils.buildSampleTree());
        System.out.println(value);
        TreeNode node = deserialize(value);
        System.out.println(serialize(node));
    }

    private static TreeNode deserialize(String value) {
        Queue<String> queue = new LinkedList<>();
        if (value != null && value.contains(",")) {
            String[] split = value.split(",");
            queue.addAll(Arrays.asList(split));
            return deserializeHelper(queue);
        }
        return null;
    }

    private static TreeNode deserializeHelper(Queue<String> plainValue) {
        if (plainValue.size() <= 0) return null;
        String curChar = plainValue.poll();
        try {
            int value = Integer.valueOf(curChar);
            TreeNode node = new TreeNode(value);
            node.left = deserializeHelper(plainValue);
            node.right = deserializeHelper(plainValue);
            return node;
        } catch (Exception e) {
            return null;
        }
    }

    private static String serialize(TreeNode node) {
        String value = serializeHelper(node, "");
        return value.substring(0, value.lastIndexOf(','));
    }

    private static String serializeHelper(TreeNode node, String lastValue) {
        if (node == null) {
            lastValue = lastValue + "$,";
            return lastValue;
        }
        lastValue = lastValue + node.val + ",";
        lastValue = serializeHelper(node.left, lastValue);
        lastValue = serializeHelper(node.right, lastValue);
        return lastValue;
    }
}
