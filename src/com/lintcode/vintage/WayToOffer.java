package com.lintcode.vintage;

import com.lintcode.vintage.inner.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by books on 11/15/17.
 */
public class WayToOffer {
    private static boolean searchMatrix(int[][] matrix, int value) {
        int ROW = matrix.length;
        int COL = matrix[0].length;
        for (int r = ROW - 1; r >= 0; r--) {
            if (value >= matrix[r][0]) {
                for (int c = 0; c < COL; c++) {
                    if (matrix[r][c] == value) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void rotatePrint(int[][] matrix) {
        int ROW = matrix.length;
        int COL = matrix[0].length;
        int MAX_ROUND = ROW < COL ? ROW / 2 : COL / 2;
        for (int round = 0; round < MAX_ROUND; round++) {
            for (int i = round; i <= COL - round - 1; i++) {
                System.out.println(matrix[round][i]);
            }
            for (int i = round + 1; i <= ROW - round - 1; i++) {
                System.out.println(matrix[i][COL - round - 1]);
            }
            for (int i = COL - round - 2; i >= round; i--) {
                System.out.println(matrix[ROW - round - 1][i]);
            }
            for (int i = ROW - round - 2; i >= round + 1; i--) {
                System.out.println(matrix[i][round]);
            }
        }
        if (ROW > COL) {
            for (int i = MAX_ROUND; i < ROW - MAX_ROUND; i++) {
                System.out.println(matrix[i][MAX_ROUND]);
            }
        } else {
            for (int i = MAX_ROUND; i < COL - MAX_ROUND; i++) {
                System.out.println(matrix[MAX_ROUND][i]);
            }
        }
    }

    public Point[] kClosest(Point[] points, Point origin, int k) {
        if (points == null) return null;
        PriorityQueue<Point> maxHeap = new PriorityQueue<>(k,
                (a, b) -> {
                    int diffA = diff(a, origin);
                    int diffB = diff(b, origin);
                    if (diffA != diffB) {
                        return diffB - diffA;
                    } else if (a.x != b.x) {
                        return b.x - a.x;
                    }
                    return b.y - a.y;
                }
        );
        for (Point point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        Point[] retPoints = new Point[k];
        int size = maxHeap.size();
        for (int i = size - 1; i >= 0; i--) {
            retPoints[i] = maxHeap.poll();
        }
        return retPoints;
    }

    private static int diff(Point a, Point origin) {
        return (int) (Math.pow((a.x - origin.x), 2) + Math.pow((a.y - origin.y), 2));
    }

    public static int triangleCount(int[] S) {
        if (S == null) return 0;
        Arrays.sort(S);
        int count = 0;
        for (int i = 0; i < S.length; i++) {
            for (int j = i + 1; j < S.length; j++) {
                int subSum = S[i] + S[j];
                int low = j + 1, high = S.length - 1;
                while (low <= high) {
                    int pivot = (low + high) / 2;
                    if (S[pivot] < subSum) {
                        if (pivot + 1 == S.length || S[pivot + 1] >= subSum) {
                            count += pivot - j;
                            break;
                        }
                        low = pivot + 1;
                    } else {
                        high = pivot - 1;
                    }
                }
            }
        }
        return count;
    }

    private static int findMinHelp(int[] arr, int index, int accumulated, int sum) {
        if (index == 0) {
            return sum - 2 * accumulated;
        }
        return Math.min(
                findMinHelp(arr, index - 1, accumulated + arr[index - 1], sum),
                findMinHelp(arr, index - 1, accumulated, sum)
        );
    }

    private static int findMinDiffsOfTwoSetsRecursive(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return findMinHelp(arr, arr.length, 0, sum);
    }

    private static int findMinDiffsOfTwoSetsDp(int[] arr) {
        int len = arr.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += arr[i];
        }
        boolean[][] dpTable = new boolean[len + 1][sum + 1];
        for (int i = 0; i <= len; i++) {
            for (int j = 0; j <= sum; j++) {
                if (i == 0) {
                    dpTable[i][j] = true;
                } else if (j == 0) {
                    dpTable[i][j] = false;
                } else {
                    dpTable[i][j] = dpTable[i - 1][j];
                    if (j >= arr[i - 1]) {
                        dpTable[i][j] |= dpTable[i - 1][j - arr[i - 1]];
                    }
                }
            }
        }
        for (int i = sum / 2; i >= 0; i--) {
            if (dpTable[len][i]) {
                return sum - 2 * i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1};
        System.out.println(findRotateMin(arr));
    }

    public static int findRotateMin(int[] arr) {
        int len = arr.length;
        if (len == 1) return arr[0];
        int left = 0, right = len - 1;
        while (arr[left] >= arr[right]) {
            if (left + 1 == right) {
                return arr[right];
            }
            int mid = (left + right) / 2;
            if (arr[mid] == arr[left] && arr[left] == arr[right]) {
                int min = arr[left];
                for (int i = left; i <= right; i++) {
                    if (arr[i] < min) {
                        min = arr[i];
                    }
                }
                return min;
            }
            if (arr[mid] >= arr[left]) {
                left = mid;
            } else if (arr[mid] <= arr[right]) {
                right = mid;
            }
        }
        return arr[left];
    }

    public static int searchInRotatedArray(int[] array, int target) {
        int left = 0, right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] == target) return mid;
            if (array[mid] >= array[left]) {
                if (array[left] <= target && array[mid] >= target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (array[mid] <= target && target <= array[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public List<List<Integer>> threeSum(int[] numbers) {
        List<List<Integer>> retValues = new ArrayList<>();
        if (numbers.length < 3) return retValues;
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length - 2; i++) {
            findHelper(numbers, numbers[i], i + 1, numbers.length - 1, retValues);
        }
        return retValues;
    }

    public static void findHelper(int[] numbers, int num, int left, int right, List<List<Integer>> result) {
        while (left < right) {
            if (numbers[left] + numbers[right] + num == 0) {
                List<Integer> oneBatch = new ArrayList<>();
                oneBatch.add(num);
                oneBatch.add(numbers[left]);
                oneBatch.add(numbers[right]);
                result.add(oneBatch);
                while (left < right && numbers[left] == numbers[left + 1]) left++;
                while (left < right && numbers[right] == numbers[right - 1]) right--;
                left++;
                right--;
            } else if (numbers[left] + numbers[right] + num < 0) {
                left++;
            } else {
                right--;
            }
        }
    }
}
