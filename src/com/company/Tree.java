package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by gordon on 10/30/17.
 */
public class Tree {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0) {
            int count = queue.size();
            List<Integer> oneLayer = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                oneLayer.add(node.val);
                if(node.left!=null) queue.add(node.left);
                if(node.right !=null) queue.add(node.right);
            }
            result.add(oneLayer);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new StringBuilder("asd").toString().substring(0,2));
    }

    public static String addBinary(String a, String b) {
        if (a == null || a.length() == 0) return b;
        if (b == null || b.length() == 0) return a;
        int lenA = a.length() - 1, lenB = b.length() - 1;
        int mod = 0;
        StringBuilder builder = new StringBuilder();
        while(lenA >= 0 || lenB >= 0 || mod > 0) {
            int left = lenA >=0 && a.charAt(lenA--) == '1' ? 1 : 0;
            int right = lenB >= 0 && b.charAt(lenB--) == '1' ? 1 : 0;
            builder.append((left + right + mod) % 2 == 0 ? '0' : '1');
            mod = (left + right + mod) / 2;
        }
        return builder.reverse().toString();
    }
}
