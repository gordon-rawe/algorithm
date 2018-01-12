package com.books.way2offer;

import com.books.datastructure.tree.TreeNode;
import com.books.extra.BTreePrinter;

/**
 * Created by gordon on 1/11/18.
 */
public class Solution6 {

    /*根据前序和中序数组还原二叉树*/

    public static void main(String[] args) {
        int[] preOrder = new int[]{1, 2, 4, 7, 3, 5, 6, 8};
        int[] postOrder = new int[]{4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode tree = buildTree(preOrder, postOrder);
        BTreePrinter.printNode(tree);
    }

    private static TreeNode buildTree(int[] preOrder, int[] postOrder) {
        if (preOrder == null || postOrder == null || preOrder.length != postOrder.length) {
            return null;
        }
        return constructHelper(preOrder, 0, preOrder.length - 1, postOrder, 0, postOrder.length - 1);
    }

    private static TreeNode constructHelper(int[] preOrder, int preStart, int preEnd, int[] postOrder, int postStart, int postEnd) {
        if (preStart > preEnd || postStart > postEnd) {
            System.out.println("it happens");
            return null;
        }
        TreeNode curNode = new TreeNode(preOrder[preStart]);
        if (preStart == preEnd) {
            return curNode;
        }
        int pSplit = postStart;
        while (postOrder[pSplit] != preOrder[preStart]) {
            pSplit++;
        }
        int len = pSplit - postStart;
        curNode.left = constructHelper(
                preOrder, preStart + 1, preStart + len,
                postOrder, postStart, pSplit - 1);
        curNode.right = constructHelper(
                preOrder, preStart + len + 1, preEnd,
                postOrder, pSplit + 1, postEnd);
        return curNode;
    }
}
