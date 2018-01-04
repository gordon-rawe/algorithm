package com.books.way2offer;

import com.books.datastructure.tree.TreeNode;
import com.books.datastructure.tree.TreeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by gordon on 1/4/18.
 */
public class Solution61 {

    /*按之字形打印二叉树*/

    public static void main(String[] args) {
        for (List<Integer> integers : getZigzagLayers(TreeUtils.buildSampleTree())) {
            System.out.println(integers);
        }

    }
    /**
     * 使用双stack交换，并且在加入元素的时候加入方向控制order，便可以完成按层次打印
     * */
    private static List<List<Integer>> getZigzagLayers(TreeNode node) {
        assert node != null;
        List<List<Integer>> retValues = new ArrayList<>();
        List<Integer> oneBatch = new ArrayList<>();
        Stack<TreeNode> workStack = new Stack<>();
        Stack<TreeNode> nextStack = new Stack<>();
        workStack.add(node);
        boolean order = false;
        while (!workStack.isEmpty()) {
            TreeNode tNode = workStack.pop();
            if (order) {
                if (tNode.left != null) nextStack.add(tNode.left);
                if (tNode.right != null) nextStack.add(tNode.right);
            } else {
                if (tNode.right != null) nextStack.add(tNode.right);
                if (tNode.left != null) nextStack.add(tNode.left);
            }
            oneBatch.add(tNode.val);
            if (workStack.isEmpty()) {
                order = !order;
                retValues.add(oneBatch);
                oneBatch = new ArrayList<>();
                Stack<TreeNode> tRef = workStack;
                workStack = nextStack;
                nextStack = tRef;
            }
        }
        return retValues;
    }
}
