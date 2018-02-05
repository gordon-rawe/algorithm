package com.lintcode.solves;

import java.util.*;

public class Solution15 {

    public static void main(String[] args) {
        for (Map.Entry<Integer, Double> entry : dicesSum(15)) {
            System.out.println(entry);
        }

    }

    private static List<List<Integer>> permutations(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        dfsHelper(res, new LinkedList<>(), nums, visited);
        return res;
    }

    private static void dfsHelper(List<List<Integer>> res, Deque<Integer> list, int[] nums, boolean[] visited) {
        if (list.size() == nums.length) {
            res.add(new LinkedList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            //当前面的相同元素还没有使用的时候，就不应该让后面的该元素使用
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            list.addLast(nums[i]);
            visited[i] = true;
            dfsHelper(res, list, nums, visited);
            visited[i] = false;
            list.pollLast();
        }
    }

    public static List<Map.Entry<Integer, Double>> dicesSum(int n) {
        Map<Integer, Double> res = new HashMap<>();
        if (n <= 0) {
            return new ArrayList<>(res.entrySet());
        }
        int SIDES = 6;
        int LENGTH = SIDES * n + 1;
        List<Double[]> possibilityTable = Arrays.asList(new Double[LENGTH], new Double[LENGTH]);
        int workingIndex = 0;
        for (int time = 1; time <= n; time++) {
            Double[] curGroup = possibilityTable.get(workingIndex);
            for (int i = 0; i < curGroup.length; i++) {
                curGroup[i] = 0d;
            }
            for (int i = time; i <= time * SIDES; i++) {
                possibilityTable.get(workingIndex)[i] = time == 1 ? 1d : addRange(possibilityTable.get(1 - workingIndex), i - SIDES, i);
            }
            workingIndex = 1 - workingIndex;
        }
        double allSum = 0;
        for (int i = n; i < LENGTH; i++) {
            allSum += possibilityTable.get(1 - workingIndex)[i];
        }
        for (int i = n; i < LENGTH; i++) {
            res.put(i, possibilityTable.get(1 - workingIndex)[i] / allSum);
        }
        System.out.println(Arrays.toString(possibilityTable.get(1 - workingIndex)));
        return new ArrayList<>(res.entrySet());
    }

    private static int addRange(Double[] nums, int start, int end) {
        int sum = 0;
        for (int i = Math.max(start, 0); i < end; i++) {
            sum += nums[i];
        }
        return sum;
    }
}
