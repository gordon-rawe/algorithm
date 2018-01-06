package com.books.way2offer;

import com.books.datastructure.tree.TreeNode;
import com.books.datastructure.tree.TreeUtils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by gordon on 1/6/18.
 */
public class Solution39 {

    /* 求二叉树的深度和广度,
     * 判断是否是平衡二叉树,如果一棵二叉树的所有节点的高度差不超过1
     * */

    public static void main(String[] args) {
        System.out.println(treeDepth(TreeUtils.buildSampleTree()));
        System.out.println(treeWidth(TreeUtils.buildSampleTree()));
        System.out.println(isBalancedTreeEfficient(TreeUtils.buildSampleTree()));
    }

    /*树的深度*/
    private static int treeDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(treeDepth(root.left), treeDepth(root.right)) + 1;
    }

    /*树的广度*/
    private static int treeWidth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> records = new LinkedList<>();
        records.add(root);
        int maxWidth = 0;
        while (!records.isEmpty()) {
            int curWidth = records.size();
            for (int i = 0; i < curWidth; i++) {
                TreeNode tNode = records.poll();
                if (tNode.left != null) records.add(tNode.left);
                if (tNode.right != null) records.add(tNode.right);
            }
            maxWidth = Math.max(maxWidth, curWidth);
        }
        return maxWidth;
    }

    /*容易想到的解法是调用treeDepth，但是这样的效率不高，会对一个节点重复遍历*/
    private static boolean isBalancedTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        int hLeft = treeDepth(root.left);
        int hRight = treeDepth(root.right);
        if (Math.abs(hLeft - hRight) > 1) {
            return false;
        }
        return isBalancedTree(root.left) && isBalancedTree(root.right);
    }

    private static boolean isBalancedTreeEfficient(TreeNode root) {
        if (root == null) return true;
        return balanceHelper(root).isBalanced;
    }

    /*需要两个信息，树的深度和当前节点是否是平衡树*/
    private static BalanceInfo balanceHelper(TreeNode node) {
        if (node == null) {
            return new BalanceInfo(0, true);
        }
        BalanceInfo leftInfo = balanceHelper(node.left);
        BalanceInfo rightInfo = balanceHelper(node.right);
        if (leftInfo.isBalanced && rightInfo.isBalanced &&
                Math.abs(leftInfo.depth - rightInfo.depth) <= 1) {
            return new BalanceInfo(Math.max(leftInfo.depth, rightInfo.depth), true);
        }
        return new BalanceInfo(Math.max(leftInfo.depth, rightInfo.depth), false);
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
