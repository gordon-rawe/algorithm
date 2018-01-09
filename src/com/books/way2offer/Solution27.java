package com.books.way2offer;

import com.books.datastructure.linkedlist.DoubleListNode;
import com.books.datastructure.tree.TreeNode;
import com.books.datastructure.tree.TreeUtils;
import com.books.extra.BTreePrinter;

/**
 * Created by gordon on 1/9/18.
 */
public class Solution27 {

    /*二叉树转链表*/

    public static void main(String[] args) {
        TreeNode node = TreeUtils.buildSampleTree();
        BTreePrinter.printNode(node);
        DoubleListNode calcdNode = treeToLinklist(node);
        while (calcdNode != null) {
            System.out.println(calcdNode.val);
            calcdNode = calcdNode.next;
        }
    }

    private static DoubleListNode treeToLinklist(TreeNode node) {
        assert node != null;
        DoubleListNode retNode = constructHelper(node);
        while (retNode.prev != null) retNode = retNode.prev;
        return retNode;
    }

    private static DoubleListNode constructHelper(TreeNode node) {
        DoubleListNode newNode = new DoubleListNode(node.val);
        if (node.left != null) {
            DoubleListNode tNode = constructHelper(node.left);
            while (tNode.next != null) {
                tNode = tNode.next;
            }
            tNode.next = newNode;
            newNode.prev = tNode;
        }
        if (node.right != null) {
            DoubleListNode tNode = constructHelper(node.right);
            while (tNode.prev != null) {
                tNode = tNode.prev;
            }
            tNode.prev = newNode;
            newNode.next = tNode;
        }
        return newNode;
    }
}
