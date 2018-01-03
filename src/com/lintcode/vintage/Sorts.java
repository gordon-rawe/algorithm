package com.lintcode.vintage;


import com.common.Utils;

/**
 * Created by books on 11/4/17.
 */
public class Sorts {

    public static void quickSort(int[] nums) {
        help(nums, 0, nums.length - 1);
    }

    private static void help(int[] nums, int left, int right) {
        if (left >= right) return;
        int pivotIndex = partition(nums, left, right);
        help(nums, left, pivotIndex - 1);
        help(nums, pivotIndex + 1, right);
    }

    /**
     * todo
     * 注意点：四次判断left < right, 后面就好说了
     */
    private static int partition(int[] data, int left, int right) {
        int pivot = data[left];
        while (left < right) {
            while (left < right && data[right] > pivot) right--;
            if (left < right) data[left++] = data[right];
            while (left < right && data[left] < pivot) left++;
            if (left < right) data[right--] = data[left];
        }
        data[left] = pivot;
        return left;
    }

    public static int[] kBig(int[] nums, int k) {
        int[] retValues = new int[k];
        int len = nums.length;
        if (len < k || k <= 0) return retValues;
        int left = 0, right = len - 1;
        int pivot = partition(nums, left, right);
        while (pivot != k - 1) {
            if (pivot > k - 1) {
                right = pivot - 1;
            } else {
                left = pivot + 1;
            }
            pivot = partition(nums, left, right);
        }
        for (int i = 0; i < k; i++) {
            retValues[i] = nums[i];
        }
        return retValues;
    }

    public static int count(int n) {
        if (n < 1)
            return 0;
        int count = 0;
        int base = 1;
        int round = n;
        while (round > 0) {
            int weight = round % 10;
            round /= 10;
            count += base * round;
            if (weight == 1) {
                count += (n % base) + 1;
            } else if (weight > 1) {
                count += base;
            }
            base *= 10;
        }
        return count;
    }


    int numOf1(int number) {
        if (number < 1) return 0;
        int count = 0, base = 1, round = number;
        while (round > 0) {
            int weight = round % 10;
            round /= 10;
            count += base * round;
            if (weight == 1) {
                int former = (number % base);
                count += former + 1;
            } else {
                count += base;
            }
        }
        return count;
    }

    private static void buildMaxHeap(int[] nums) {
        for (int i = nums.length / 2; i >= 0; i--) {
            maxHeap(nums, nums.length - 1, i);
        }
    }

    /**
     * todo need to be remembered.
     */
    private static void maxHeap(int[] nums, int maxIndex, int index) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int largest = index;
        if (left <= maxIndex && nums[left] > nums[largest]) {
            largest = left;
        }
        if (right <= maxIndex && nums[right] > nums[largest]) {
            largest = right;
        }
        if (largest != index) {
            Utils.swap(nums, largest, index);
            maxHeap(nums, maxIndex, largest);
        }
    }

    public static void heapSort(int[] nums) {
        buildMaxHeap(nums);
        for (int i = nums.length - 1; i > 0; i--) {
            Utils.swap(nums, 0, i);
            maxHeap(nums, i - 1, 0);
        }
    }

    public static void insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                int j = i;
                int tmpValue = nums[i];
                while (j > 0 && nums[j - 1] > tmpValue) {
                    nums[j] = nums[j - 1];
                    j--;
                }
                nums[j] = tmpValue;
            }
        }
    }

    public static void rotateArray(int[] nums, int k) {
        k = k % nums.length;
        reverseRange(nums, 0, k - 1);
        reverseRange(nums, k, nums.length - 1);
        reverseRange(nums, 0, nums.length - 1);
    }

    public static void reverseRange(int[] nums, int left, int right) {
        if (left >= right) return;
        while (left < right) {
            int tmpValue = nums[left];
            nums[left++] = nums[right];
            nums[right--] = tmpValue;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 4, 3, 2, 6, 8, 5, 7, 9};
        rotateArray(nums, 2);
        System.out.println(java.util.Arrays.toString(nums));
    }

    private static void mergeSort(int[] nums) {
        if (nums == null || nums.length < 2) return;
        mergeHelper(nums, 0, nums.length - 1);
    }

    private static void mergeHelper(int[] nums, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeHelper(nums, left, mid);
            mergeHelper(nums, mid + 1, right);
            zipHelper(nums, left, mid, right);
        }
    }

    private static void zipHelper(int[] nums, int left, int mid, int right) {
        int head1 = left, head2 = mid + 1;
        int[] copySpace = new int[right - left + 1];
        int index = 0;
        while (head1 <= mid && head2 <= right) {
            copySpace[index++] = nums[head1] > nums[head2] ? nums[head1++] : nums[head2++];
        }
        while (head1 <= mid) {
            copySpace[index++] = nums[head1++];
        }
        while (head2 <= right) {
            copySpace[index++] = nums[head2++];
        }
        for (int i = 0; i < copySpace.length; i++) {
            nums[left + i] = copySpace[i];
        }
    }
}
