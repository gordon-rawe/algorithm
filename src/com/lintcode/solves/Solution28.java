package com.lintcode.solves;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution28 {

    /**
     * Example 1:
     * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
     * <p>
     * Example 2:
     * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
     * <p>
     * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
     */

    public static void main(String[] args) {
        for (Interval interval : insert(Arrays.asList(
                new Interval(1, 3),
                new Interval(4, 5),
                new Interval(6, 7)
        ), new Interval(0, 5))) {
            System.out.println(interval);
        }
    }

    private static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        for (Interval current : intervals) {
            if (current.end < newInterval.start) {
                res.add(current);
            } else if (current.start > newInterval.end) {
                res.add(newInterval);
                newInterval = current;
            } else if (current.end >= newInterval.start || current.start <= newInterval.end) {
                newInterval = new Interval(Math.min(current.start, newInterval.start), Math.max(newInterval.end, current.end));
            }
        }
        res.add(newInterval);
        return res;
    }

    static class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "[" + start + "," + end + "]";
        }
    }
}
