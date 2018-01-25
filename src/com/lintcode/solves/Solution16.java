package com.lintcode.solves;

import com.common.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gordon on 1/24/18.
 */
public class Solution16 {

    /**
     * 去重的方法可以有多种，这里采用set，也可以采用逐位比较,O(N)
     */

    public static void main(String[] args) {
        for (List<Integer> integers : permuteUnique(new int[]{1, 3, 3})) {
            System.out.println(integers);
        }
    }

    private static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        permute(nums, 0, res);
        return res;
    }

    private static void permute(int[] nums, int layer, List<List<Integer>> res) {
        if (layer >= nums.length) {
            res.add(Utils.arr2List(nums));
            return;
        }
        for (int i = layer; i < nums.length; i++) {
            Utils.swap(nums, layer, i);
            permute(nums, layer + 1, res);
            Utils.swap(nums, layer, i);
        }
    }

    private static List<List<Integer>> permuteUnique(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        permuteUniqueHelper(nums, 0, res);
        return new ArrayList<>(res);
    }

    private static void permuteUniqueHelper(int[] nums, int layer, Set<List<Integer>> res) {
        if (layer >= nums.length) {
            res.add(Utils.arr2List(nums));
            return;
        }
        for (int i = layer; i < nums.length; i++) {
            Utils.swap(nums, layer, i);
            permuteUniqueHelper(nums, layer + 1, res);
            Utils.swap(nums, layer, i);
        }
    }
}
