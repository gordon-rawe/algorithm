package com.books.way2offer;

import com.books.datastructure.tree.TreeNode;
import com.books.datastructure.tree.TreeUtils;
import com.books.extra.BTreePrinter;

/**
 * Created by gordon on 1/4/18.
 */
public class Solution63 {

    /**
     * 二叉树的第k个节点
     */

    public static void main(String[] args) {
        TreeNode tree = TreeUtils.buildSampleTree();
        BTreePrinter.printNode(tree);
        TreeNode resNode = kThNodeOfTree(tree, new Counter(), 100);
        if (resNode != null) {
            System.out.println(resNode.val);
        }else {
            System.out.println("index out of bound.");
        }
    }

    private static TreeNode kThNodeOfTree(TreeNode node, Counter counter, int k) {
        TreeNode retNode = null;
        if (node.left != null) {
            retNode = kThNodeOfTree(node.left, counter, k);
        }
        if (retNode == null) {
            counter.count++;
            if (counter.count == k) {
                return node;
            }
        }
        if (retNode == null && node.right != null) {
            retNode = kThNodeOfTree(node.right, counter, k);
        }
        return retNode;
    }

    static class Counter {
        int count;
    }
}
