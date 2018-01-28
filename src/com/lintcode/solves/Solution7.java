package com.lintcode.solves;

import com.books.datastructure.tree.TreeNode;
import com.books.datastructure.tree.TreeUtils;
import com.books.extra.BTreePrinter;

import java.util.*;

/**
 * Created by gordon on 1/25/18.
 */
public class Solution7 {

    private static String serialize(TreeNode root) {
        List<String> list = new ArrayList<>();
        serializeHelper(root, list);
        return toString(list);
    }

    private static String toString(List<String> vals) {
        if (vals == null || vals.isEmpty()) return "";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < vals.size() - 1; i++) {
            builder.append(vals.get(i));
            builder.append(",");
        }
        builder.append(vals.get(vals.size() - 1));
        return builder.toString();
    }

    private static void serializeHelper(TreeNode node, List<String> builder) {
        if (node == null) {
            builder.add("#");
        } else {
            builder.add(String.valueOf(node.val));
            serializeHelper(node.left, builder);
            serializeHelper(node.right, builder);
        }
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in
     * "serialize" method.
     */
    private static TreeNode deserialize(String data) {
        String[] array = data.split(",");
        Queue<String> vals = new LinkedList<>();
        vals.addAll(Arrays.asList(array));
        return deserializeHelper(vals);
    }

    private static TreeNode deserializeHelper(Queue<String> vals) {
        if (vals.isEmpty()) {
            return null;
        }
        String curValue = vals.poll();
        if ("#".equals(curValue)) {
            return null;
        } else {
            TreeNode newNode = new TreeNode(Integer.parseInt(curValue));
            newNode.left = deserializeHelper(vals);
            newNode.right = deserializeHelper(vals);
            return newNode;
        }
    }

    public static void main(String[] args) {
        TreeNode node = TreeUtils.buildSampleTree();
        BTreePrinter.printNode(node);
        String serialString = serialize(node);
        System.out.println(serialString);
        TreeNode dTree = deserialize(serialString);
        BTreePrinter.printNode(dTree);
    }
}
