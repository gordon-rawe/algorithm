package com.lintcode.solves;

import java.util.ArrayList;
import java.util.List;

public class Solution22 {

    public interface NestedInteger {
        boolean isInteger();

        Integer getInteger();

        List<NestedInteger> getList();
    }

    public static List<Integer> flatten(List<NestedInteger> nestedList) {
        List<Integer> result = new ArrayList<>();
        dfsHelper(nestedList, result);
        return result;
    }

    private static void dfsHelper(List<NestedInteger> nestedList, List<Integer> result) {
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                result.add(nestedInteger.getInteger());
            } else {
                dfsHelper(nestedInteger.getList(), result);
            }
        }
    }

    public static void main(String[] args) {

    }
}
