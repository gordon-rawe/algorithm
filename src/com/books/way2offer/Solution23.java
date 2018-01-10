package com.books.way2offer;

import com.books.datastructure.tree.TreeNode;
import com.books.datastructure.tree.TreeUtils;
import com.books.extra.BTreePrinter;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by gordon on 1/10/18.
 */
public class Solution23 {

    /*从上往下打印二叉树*/

    public static void main(String[] args) {
        BTreePrinter.printNode(TreeUtils.buildSampleTree());
        layerPrintTree(TreeUtils.buildSampleTree());
    }

    private static void layerPrintTree(TreeNode node) {
        if (node == null) {
            return;
        }
        Queue<TreeNode> visitHelper = new LinkedList<>();
        visitHelper.add(node);
        while (!visitHelper.isEmpty()) {
            int curSize = visitHelper.size();
            for (int i = 0; i < curSize; i++) {
                TreeNode tNode = visitHelper.poll();
                System.out.print(tNode.val + " ");
                if (tNode.left != null) visitHelper.add(tNode.left);
                if (tNode.right != null) visitHelper.add(tNode.right);
            }
            System.out.println();
        }
    }
}
