package com.books.way2offer;

import java.util.*;

/**
 * Created by gordon on 1/5/18.
 */
public class Solution41 {

    /*和为s的两个数字(two sum problem)*/

    public static void main(String[] args) {
        int[] sample = new int[]{1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(twoSum(sample, 4)));
        System.out.println(Arrays.toString(twoSumIndex(sample, 10)));
    }

    private static int[] twoSum(int[] nums, int s) {
        int[] retValue = new int[]{-1, -1};
        Set<Integer> records = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (records.contains(s - nums[i])) {
                retValue[0] = s - nums[i];
                retValue[1] = nums[i];
                return retValue;
            } else {
                records.add(nums[i]);
            }
        }
        return retValue;
    }

    private static int[] twoSumIndex(int[] nums, int s) {
        int[] retValue = new int[]{-1, -1};
        Map<Integer, Integer> records = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (records.containsKey(s - nums[i])) {
                retValue[0] = records.get(s - nums[i]);
                retValue[1] = i;
                return retValue;
            } else {
                records.put(nums[i], i);
            }
        }
        return retValue;
    }
}
