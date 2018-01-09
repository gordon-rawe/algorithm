package com.books.way2offer;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by gordon on 1/9/18.
 */
public class Solution30 {

    /*最大的k个数字*/

    public static void main(String[] args) {
        KMFinder finder = new KMFinder(3);
        finder.add(1);
        finder.add(2);
        finder.add(3);
        finder.add(4);
        finder.add(1);
        finder.add(2);
        finder.add(3);
        finder.add(4);
        System.out.println(Arrays.toString(finder.dump()));
        int[] nums = new int[]{1, 3, 8, 4, 5, 3, 7, 2};
        System.out.println(Arrays.toString(maxKNums(nums, 2)));
    }

    /*适合处理海量数据*/
    private static class KMFinder {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        int limit;

        KMFinder(int k) {
            limit = k;
        }

        public void add(int value) {
            maxHeap.add(value);
            if (maxHeap.size() > limit) {
                maxHeap.poll();
            }
        }

        Integer[] dump() {
            Integer[] retValue = new Integer[maxHeap.size()];
            maxHeap.toArray(retValue);
            return retValue;
        }
    }

    private static int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        while (left < right) {
            while (left < right && nums[right] > pivot) {
                right--;
            }
            if (left < right) nums[left++] = nums[right];
            while (left < right && nums[left] < pivot) {
                left++;
            }
            if (left < right) nums[right--] = nums[left];
        }
        nums[left] = pivot;
        return left;
    }

    private static int[] maxKNums(int[] nums, int k) {
        assert nums != null && k > 0 && nums.length >= k;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int pivot = partition(nums, left, right);
            if (pivot > k - 1) {
                right = pivot - 1;
            } else if (pivot < k - 1) {
                left = pivot + 1;
            } else {
                break;
            }
        }
        return nums;
    }
}
