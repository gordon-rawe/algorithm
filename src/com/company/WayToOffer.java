package com.company;

import com.company.inner.Point;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by gordon on 11/15/17.
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

    public static int diff(Point a, Point origin) {
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

    public static int findMinDiffsOfTwoSets(int[] arr) {
        int len = arr.length;
        int[] dpTable = new int[len];
        dpTable[0] = arr[0];
        for (int i = 1; i < len; i++) {
            dpTable[i] = Math.min(
                    Math.abs(dpTable[i - 1] + arr[i]),
                    Math.abs(dpTable[i - 1] - arr[i])
            );
        }
        return dpTable[len - 1];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 6, 11};
        System.out.println(findMinDiffsOfTwoSets(nums));
    }
}
