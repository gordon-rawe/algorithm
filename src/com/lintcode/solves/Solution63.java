package com.lintcode.solves;

/**
 * Created by gordon on 2/25/18.
 */
public class Solution63 {

    public static void main(String[] args) {

    }

    // TODO: 2/25/18  need to be repeated
    public static boolean search(int[] array, int target) {
        int left = 0, right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] == target) return true;
            if (array[mid] > array[left]) {
                if (array[left] <= target && array[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (array[mid] < array[left]) {
                if (array[mid] < target && target <= array[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                left++;
            }
        }
        return false;
    }
}
