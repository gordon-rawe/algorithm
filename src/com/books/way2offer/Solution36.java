package com.books.way2offer;

import java.util.Arrays;

/**
 * Created by gordon on 1/6/18.
 */
public class Solution36 {

    /*数组中的逆序对,其实本质就是归并排序中的一次统计，当前面小于后面的时候+1*/

    public static void main(String[] args) {
        int[] sample = new int[]{1, 4, 2, 3, 5, 7, 6};
        mergeSort(sample);
        System.out.println(Arrays.toString(sample));
        sample = new int[]{7, 5, 6, 4};
        System.out.println(countPairs(sample));
    }

    private static int countPairs(int[] nums) {
        int[] copySpace = new int[nums.length];
        int[] statistic = new int[1];
        mergeHelper(nums, copySpace, 0, nums.length - 1, statistic);
        return statistic[0];
    }

    /*
    * 以7 5 6 4为例，先7,5变为5,7构成一例，6，4变为4，6构成一例，5,7,4,6以 7 -> 46和5 -> 4,构成三例，
    * 方法，在归并排序过程中不断计算逆序对.
    * */
    private static void mergeHelper(int[] nums, int[] copySpace, int left, int right, int[] statistic) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeHelper(nums, copySpace, left, mid, statistic);
            mergeHelper(nums, copySpace, mid + 1, right, statistic);
            int p1 = mid, p2 = right, p3 = right;
            while (p1 >= left && p2 >= mid + 1) {
                if (nums[p2] < nums[p1]) {
                    statistic[0] += p2 - mid;
                }
                copySpace[p3--] = nums[p2] > nums[p1] ? nums[p2--] : nums[p1--];
            }
            while (p1 >= left) {
                copySpace[p3--] = nums[p1--];
            }
            while (p2 >= mid + 1) {
                copySpace[p3--] = nums[p2--];
            }
            for (int i = right; i >= left; i--) {
                nums[i] = copySpace[i];
            }
        }
    }

    /*以下区域是归并排序的区域，它和寻找逆序对的操作有相同之处*/
    private static void mergeSort(int[] nums) {
        int[] copySpace = new int[nums.length];
        mergeHelper(nums, copySpace, 0, nums.length - 1);
    }

    private static void mergeHelper(int[] nums, int[] copySpace, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeHelper(nums, copySpace, left, mid);
            mergeHelper(nums, copySpace, mid + 1, right);
            int p1 = mid, p2 = right, p3 = right;
            while (p1 >= left && p2 >= mid + 1) {
                copySpace[p3--] = nums[p2] > nums[p1] ? nums[p2--] : nums[p1--];
            }
            while (p1 >= left) {
                copySpace[p3--] = nums[p1--];
            }
            while (p2 >= mid + 1) {
                copySpace[p3--] = nums[p2--];
            }
            for (int i = right; i >= left; i--) {
                nums[i] = copySpace[i];
            }
        }
    }
}
