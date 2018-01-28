package com.lintcode.solves;

import com.books.datastructure.tree.TreeNode;
import com.books.datastructure.tree.TreeUtils;
import com.books.extra.BTreePrinter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gordon on 1/28/18.
 */
public class Solution11 {

    public static void main(String[] args) {
        TreeNode pNode = TreeUtils.buildSortedTree();
        BTreePrinter.printNode(pNode);
        for (Integer integer : searchRange(pNode, 10, 22)) {
            System.out.println(integer);
        }

    }

    public static List<Integer> searchRange(TreeNode root, int k1, int k2) {
        List<Integer> res = new ArrayList<>();
        if (root == null || k1 > k2) {
            return res;
        }
        traverseHelper(root, res, k1, k2);
        return res;
    }

    private static void traverseHelper(TreeNode node, List<Integer> res, int floor, int ceil) {
        if (node.left != null) {
            traverseHelper(node.left, res, floor, ceil);
        }
        if (floor <= node.val && node.val <= ceil) {
            res.add(node.val);
        }
        if (node.right != null) {
            traverseHelper(node.right, res, floor, ceil);
        }
    }
}
