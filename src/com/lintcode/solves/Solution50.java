package com.lintcode.solves;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gordon on 2/23/18.
 */
public class Solution50 {

    public static void main(String[] args) {
        System.out.println(productExcludeItself(Arrays.asList(1, 2, 3)));
    }

    public static List<Long> productExcludeItself(List<Integer> nums) {
        List<Long> retValues = new ArrayList<>();
        Long[] forward = new Long[nums.size()], backward = new Long[nums.size()];
        Long lastMul = 1L;
        for (int i = 0; i < nums.size(); i++) {
            forward[i] = lastMul;
            lastMul = lastMul * nums.get(i);
        }
        lastMul = 1L;
        for (int i = nums.size() - 1; i >= 0; i--) {
            backward[i] = lastMul;
            lastMul = lastMul * nums.get(i);
        }
        for (int i = 0; i < nums.size(); i++) {
            retValues.add(forward[i] * backward[i]);
        }
        return retValues;
    }
}
