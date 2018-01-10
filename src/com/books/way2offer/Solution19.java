package com.books.way2offer;

import com.books.datastructure.tree.TreeNode;
import com.books.datastructure.tree.TreeUtils;
import com.books.extra.BTreePrinter;

/**
 * Created by gordon on 1/10/18.
 */
public class Solution19 {

    /*二叉树的镜像，又称反转二叉树*/

    public static void main(String[] args) {
        TreeNode tree = TreeUtils.buildSampleTree();
        BTreePrinter.printNode(tree);
        reverseTree(tree);
        BTreePrinter.printNode(tree);
    }

    private static void reverseTree(TreeNode node) {
        if (node == null) return;
        TreeNode tNode = node.left;
        node.left = node.right;
        node.right = tNode;
        if (node.left != null) reverseTree(node.left);
        if (node.right != null) reverseTree(node.right);
    }
}
