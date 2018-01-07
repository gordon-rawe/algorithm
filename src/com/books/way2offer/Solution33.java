package com.books.way2offer;

import java.util.Arrays;

/**
 * Created by gordon on 1/7/18.
 */
public class Solution33 {

    /*把数组排成最小的数字*/

    public static void main(String[] args) {
        int[] sample = new int[]{3, 32, 321};
        minSort(sample, 0, sample.length - 1, (a, b) -> {
            String prefix = String.valueOf(a).concat(String.valueOf(b));
            String suffix = String.valueOf(b).concat(String.valueOf(a));
            int pos = 0;
            while (pos < prefix.length()) {
                if (prefix.charAt(pos) > suffix.charAt(pos)) {
                    return 1;
                } else if (prefix.charAt(pos) < suffix.charAt(pos)) {
                    return -1;
                }
                pos++;
            }
            return 0;
        });
        System.out.println(Arrays.toString(sample));
    }

    private static void minSort(int[] nums, int left, int right, Sortable sort) {
        if (left >= right) return;
        int position = partition(nums, left, right, sort);
        minSort(nums, left, position, sort);
        minSort(nums, position + 1, right, sort);
    }

    private static int partition(int[] nums, int left, int right, Sortable sort) {
        int pivot = nums[left];
        while (left < right) {
            while (left < right && sort.sort(nums[right], pivot) > 0) right--;
            if (left < right) nums[left++] = nums[right];
            while (left < right && sort.sort(nums[left], pivot) < 0) left++;
            if (left < right) nums[right--] = nums[left];
        }
        nums[left] = pivot;
        return left;
    }

    interface Sortable {
        int sort(int a, int b);
    }
}
