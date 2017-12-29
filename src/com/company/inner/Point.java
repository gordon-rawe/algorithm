package com.company.inner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gordon on 11/15/17.
 */
public class Point {
    public int x;
    public int y;

    Point() {
        x = 0;
        y = 0;
    }

    Point(int a, int b) {
        x = a;
        y = b;
    }

    public List<List<Integer>> fourSum(int[] numbers, int target) {
        List<List<Integer>> retValues = new ArrayList<>();
        if (numbers.length < 4) return retValues;
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length - 3; i++) {
            if (i > 0 && numbers[i] == numbers[i - 1]) continue;
            List<List<Integer>> oneBatch = threeSum(numbers, i + 1, target - numbers[i]);
            if (oneBatch.size() > 0) {
                for (List<Integer> batch : oneBatch) {
                    List<Integer> oneGroup = new ArrayList<>();
                    oneGroup.add(numbers[i]);
                    oneGroup.addAll(batch);
                    retValues.add(oneGroup);
                }
            }
        }
        return retValues;
    }

    public static List<List<Integer>> threeSum(int[] numbers, int startPosition, int target) {
        List<List<Integer>> retValues = new ArrayList<>();
        if (numbers.length < 3) return retValues;
        for (int i = startPosition; i < numbers.length - 2; i++) {
            if (i > startPosition && numbers[i] == numbers[i - 1]) continue;
            findHelper(numbers, numbers[i], target - numbers[i], i + 1, retValues);
        }
        return retValues;
    }

    public static void findHelper(int[] numbers, int num, int target, int left, List<List<Integer>> result) {
        int right = numbers.length - 1;
        while (left < right) {
            if (numbers[left] + numbers[right] == target) {
                List<Integer> oneBatch = new ArrayList<>();
                oneBatch.add(num);
                oneBatch.add(numbers[left]);
                oneBatch.add(numbers[right]);
                result.add(oneBatch);
                while (left < right && numbers[left] == numbers[left + 1]) left++;
                while (left < right && numbers[right] == numbers[right - 1]) right--;
                right--;
                left++;
            } else if (numbers[left] + numbers[right] < target) {
                left++;
            } else {
                right--;
            }
        }
    }
}
