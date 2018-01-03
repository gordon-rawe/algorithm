package com.books.way2offer;

import com.books.datastructure.tree.TreeNode;

/**
 * Created by gordon on 1/3/18.
 */
public class Solution59 {

    /**
     * 判断一棵树是不是堆成的二叉树
     */

    public static void main(String[] args) {

    }

    private static boolean isSymetric(TreeNode node) {
        return isSymetric(node, node);
    }

    private static boolean isSymetric(TreeNode lNode, TreeNode rNode) {
        if (lNode == null && rNode == null) return true;
        if (lNode == null || rNode == null) return false;
        if (lNode.val != rNode.val) return false;
        return isSymetric(lNode.left, rNode.right)
                && isSymetric(lNode.right, rNode.left);
    }
}
