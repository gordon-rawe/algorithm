package com.lintcode.solves;

import com.books.datastructure.tree.TreeNode;

/**
 * Created by gordon on 2/28/18.
 */
public class Solution88 {

    public static void main(String[] args) {

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode nodeA, TreeNode nodeB) {
        if (root == null) {
            return null;
        }
        if (root == nodeA || root == nodeB) {
            return root;
        }
        TreeNode lNode = lowestCommonAncestor(root.left, nodeA, nodeB);
        TreeNode rNode = lowestCommonAncestor(root.right, nodeA, nodeB);
        if (lNode != null && rNode != null) {
            return root;
        }
        return lNode == null ? rNode : lNode;
    }
}
