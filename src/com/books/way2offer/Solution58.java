package com.books.way2offer;

import com.books.datastructure.tree.TreeNode;

/**
 * Created by gordon on 1/3/18.
 */
public class Solution58 {

    /*二叉树的下一个节点*/

    public static void main(String[] args) {

    }

    public static TreeNode successor(TreeNode node) {
        assert node != null;
        TreeNode retNode = null;
        if (node.right != null) {
            TreeNode tNode = node.right;
            while (tNode.left != null) tNode = tNode.left;
            retNode = tNode;
        } else if (node.parent != null) {
            while (node.parent != null && node.parent.right == node) {
                node = node.parent;
            }
            retNode = node.parent;
        }
        return retNode;
    }
}
