package com.lintcode.solves;

import com.books.datastructure.tree.TreeNode;

/**
 * Created by gordon on 2/27/18.
 */
public class Solution93 {

    public static void main(String[] args) {

    }

    /**
     * 考虑同时提取高度和平衡两种信息，用来防止下面节点的重复计算.
     */
    public static boolean isBalanced(TreeNode root) {
        return root == null || visitHelper(root).isBalanced;
    }

    private static BalanceInfo visitHelper(TreeNode root) {
        if (root == null) {
            return new BalanceInfo(0, true);
        }
        BalanceInfo leftInfo = visitHelper(root.left);
        BalanceInfo rightInfo = visitHelper(root.right);
        int depth = Math.max(leftInfo.depth, rightInfo.depth) + 1;
        if (leftInfo.isBalanced && rightInfo.isBalanced &&
                Math.abs(leftInfo.depth - rightInfo.depth) <= 1) {
            return new BalanceInfo(depth, true);
        }
        return new BalanceInfo(depth, false);
    }

    static class BalanceInfo {
        int depth;
        boolean isBalanced;

        BalanceInfo(int depth, boolean isBalanced) {
            this.depth = depth;
            this.isBalanced = isBalanced;
        }
    }
}
