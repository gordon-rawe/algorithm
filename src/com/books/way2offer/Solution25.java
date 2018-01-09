package com.books.way2offer;

import com.books.datastructure.tree.TreeNode;
import com.books.datastructure.tree.TreeUtils;
import com.books.extra.BTreePrinter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gordon on 1/9/18.
 */
public class Solution25 {

    /*二叉树路径为某个值的路径*/

    public static void main(String[] args) {
        TreeNode tree = TreeUtils.buildSampleTree();
        BTreePrinter.printNode(tree);
        printPath(tree, 10);
    }

    private static void printPath(TreeNode node, int targetValue) {
        printPath(node, new ArrayList<>(), 0, targetValue);
    }

    private static void printPath(TreeNode node, List<TreeNode> historyNodes, int historyValue, int targetValue) {
        if (historyValue + node.val == targetValue) {
            historyNodes.add(node);
            for (TreeNode historyNode : historyNodes) {
                System.out.println(historyNode.val);
            }
        } else {
            historyNodes.add(node);
            if (node.left != null) {
                printPath(node.left, historyNodes, historyValue + node.val, targetValue);
                printPath(node.right, historyNodes, historyValue + node.val, targetValue);
            }
            historyNodes.remove(node);
        }
    }
}
