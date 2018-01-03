package com.books.way2offer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by gordon on 1/3/18.
 */
public class Solution51 {

    /*一个数组，其数值范围在[0 - (N - 1)]之间，求所有重复的数字*/

    public static void main(String[] args) {
        int[] sample = new int[]{1, 1, 0, 0};
        System.out.println("findDuplicateSetExtraSpace:");
        for (Integer integer : findDuplicateSetExtraSpace(sample)) {
            System.out.println(integer);
        }
        System.out.println("findDuplicateSet:");
        for (Integer integer : findDuplicateSet(sample)) {
            System.out.println(integer);
        }
        System.out.println("findSingleDuplicate:");
        System.out.println(findSingleDuplicate(sample));
    }

    /*
    * T(N) O(1)
    * 使用hash的处理办法
    * 优点，不打乱数组
    * */
    private static Set<Integer> findDuplicateSetExtraSpace(int[] nums) {
        int SIZE = nums.length;
        int[] records = new int[SIZE];
        Set<Integer> duplicates = new HashSet<>();
        for (int i = 0; i < SIZE; i++) {
            records[nums[i]] += 1;
            if (records[nums[i]] > 1) {
                duplicates.add(nums[i]);
            }
        }
        return duplicates;
    }

    /*
    * T(N) O(1)
    * 将访问到的0标记为-SIZE,将正数标记为负数，碰到SIZE或者负数表示曾经访问过，是duplicate.
    * 缺点：会打乱原有数组，当然可以恢复
    * */
    private static Set<Integer> findDuplicateSet(int[] nums) {
        int SIZE = nums.length;
        Set<Integer> duplicates = new HashSet<>();
        for (int i = 0; i < SIZE; i++) {
            if (SIZE == Math.abs(nums[i]) || nums[Math.abs(nums[i])] < 0) {
                duplicates.add(Math.abs(nums[i]));
            } else {
                int oldValue = nums[Math.abs(nums[i])];
                nums[Math.abs(nums[i])] = oldValue == 0 ? -SIZE : -oldValue;
            }
        }
        return duplicates;
    }

    /*
    * T(N) O(1)
    * 将访问到的0标记为-SIZE,将正数标记为负数，碰到SIZE或者负数表示曾经访问过，是duplicate.
    * 缺点：会打乱原有数组，当然可以恢复
    */
    private static int findSingleDuplicate(int[] nums) {
        int SIZE = nums.length;
        int retDefault = -1;
        for (int i = 0; i < SIZE; i++) {
            if (SIZE == Math.abs(nums[i]) || nums[Math.abs(nums[i])] < 0) {
                retDefault = Math.abs(nums[i]);
                break;
            } else {
                int oldValue = nums[Math.abs(nums[i])];
                nums[Math.abs(nums[i])] = oldValue == 0 ? -SIZE : -oldValue;
            }
        }
        return retDefault;
    }
}
