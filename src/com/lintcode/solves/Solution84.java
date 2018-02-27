package com.lintcode.solves;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gordon on 2/26/18.
 */
public class Solution84 {

    public static void main(String[] args) {
        System.out.println(singleNumberIII(new int[]{
                1, 2, 3, 3, 2, 4, 1, 5
        }));
    }

    public static List<Integer> singleNumberIII(int[] A) {
        int testBit = 0, oneBit = 1;
        for (int i = 0; i < A.length; i++) {
            testBit ^= A[i];
        }
        while ((testBit & oneBit) == 0) {
            oneBit <<= 1;
        }
        List<Integer> groupA = new ArrayList<>(), groupB = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            if ((oneBit & A[i]) == 0) {
                groupA.add(A[i]);
            } else {
                groupB.add(A[i]);
            }
        }
        int retA = 0, retB = 0;
        for (Integer value : groupA) {
            retA ^= value;
        }
        for (Integer value : groupB) {
            retB ^= value;
        }
        return Arrays.asList(retA, retB);
    }
}
