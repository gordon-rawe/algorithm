package com.lintcode.solves;

/**
 * Created by gordon on 2/25/18.
 */
public class Solution62 {

    public static void main(String[] args) {

    }

    //    4, 5, 1, 2, 3 -> 2
    //    5, 1, 2, 3, 4 -> 1

    /**
     * 如果中间不相等，现检验左侧是否是递增区间，如果满足left，value和mid递增则取该区间，不然取另外的区间，
     * 同样，对于另外区间也类似。
     * */
    public static int search(int[] array, int target) {
        int start = 0, end = array.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (array[mid] == target) {
                return mid;
            }
            if (array[mid] < array[start]) {
                if (array[mid] > target && array[start] <= target) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (array[mid] < target && target <= array[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }
}
