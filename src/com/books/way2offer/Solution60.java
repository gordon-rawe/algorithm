package com.books.way2offer;

import com.books.datastructure.tree.TreeNode;
import com.books.datastructure.tree.TreeUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by gordon on 1/3/18.
 */
public class Solution60 {

    /*将二叉树打印成为多行*/

    public static void main(String[] args) {
        for (List<Integer> integers : getLayers(TreeUtils.buildSampleTree())) {
            System.out.println(integers);
        }
    }

    private static List<List<Integer>> getLayers(TreeNode root) {
        assert root != null;
        List<List<Integer>> retValues = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int curSize = queue.size();
            List<Integer> oneBatch = new ArrayList<>();
            for (int i = 0; i < curSize; i++) {
                TreeNode curNode = queue.poll();
                if (curNode.left != null) queue.add(curNode.left);
                if (curNode.right != null) queue.add(curNode.right);
                oneBatch.add(curNode.val);
            }
            retValues.add(oneBatch);
        }
        return retValues;
    }
}
