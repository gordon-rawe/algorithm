package com.books.way2offer;

import com.books.datastructure.tree.TreeNode;
import com.books.datastructure.tree.TreeUtils;

/**
 * Created by gordon on 1/10/18.
 */
public class Solution18 {

    /*判断数A是不是树B的子结构，即树的值树是一样的,节点数量可以少一些*/

    public static void main(String[] args) {
        TreeNode oneTree = new TreeNode(2);
        oneTree.left = TreeUtils.buildSampleTree();
        System.out.println(isSubTree(oneTree, TreeUtils.buildSampleTree()));
        System.out.println(isSubTree(TreeUtils.buildSampleTree(), oneTree));
    }

    private static boolean isSubTree(TreeNode treeA, TreeNode treeB) {
        if (treeA == null || treeB == null) {
            return false;
        }
        return treeBContainsTreeA(treeA, treeB) ||
                isSubTree(treeA, treeB.left) ||
                isSubTree(treeA, treeB.right);
    }

    /*judge是a tree 是否是b tree的字结构*/
    private static boolean treeBContainsTreeA(TreeNode treeA, TreeNode treeB) {
        if (treeB == null) {
            return false;
        }
        if (treeA == null) {
            return true;
        }

        if (treeA.val != treeB.val) {
            return false;
        }
        return treeBContainsTreeA(treeA.left, treeB.left) && treeBContainsTreeA(treeA.right, treeB.right);
    }
}
