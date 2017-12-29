package com.company;

/**
 * Created by gordon on 10/23/17.
 */
public class TreeNode {
    public int val;
    public TreeNode left, right, parent;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}
