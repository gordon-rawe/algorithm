package com.company;

import java.util.*;

/**
 * Created by gordon on 11/1/17.
 */
public class Google {
    public static int minDistance(String a, String b) {
        int lenA = a.length(), lenB = b.length();
        if (lenA == 0) return lenB;
        if (lenB == 0) return lenA;
        int[][] table = new int[lenA + 1][lenB + 1];
        for (int i = 0; i < lenA + 1; i++) {
            table[i][0] = i;
        }
        for (int j = 0; j < lenB + 1; j++) {
            table[0][j] = j;
        }
        for (int i = 1; i < lenA + 1; i++) {
            for (int j = 1; j < lenB + 1; j++) {
                int cost = a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1;
                //替换操作的话，NLP里面算作是两步，但是此题要求是一步
                table[i][j] = Math.min(cost + table[i - 1][j - 1], Math.min(table[i - 1][j], table[i][j - 1]));
            }
        }
        return table[lenA][lenB];
    }

    public static int leftRotate(int n, int d) {
        int mask = 1 << 30;
        while (d > 0) {
            d--;
        }
        return mask;
    }

    public static String minWindow(String S, String T) {
        String retValue = "";
        if (S == null || T == null || S.length() == 0 || T.length() == 0) return retValue;
        Map<Character, Integer> tracker = new HashMap<>();
        for (int i = 0; i < T.length(); i++) {
            Character thisChar = T.charAt(i);
            if (tracker.containsKey(thisChar)) {
                tracker.put(thisChar, tracker.get(thisChar) + 1);
            } else {
                tracker.put(thisChar, 1);
            }
        }
        int count = 0, pre = 0, minLen = S.length();
        for (int i = 0; i < S.length(); i++) {
            Character thisChar = T.charAt(i);
            if (tracker.containsKey(thisChar)) {
                tracker.put(thisChar, tracker.get(thisChar) - 1);
                if (tracker.get(thisChar) >= 0) count++;
                while (count == T.length()) {
                    if (tracker.containsKey(S.charAt(pre))) {
                        tracker.put(S.charAt(pre), tracker.get(S.charAt(pre)) + 1);
                        if (tracker.get(S.charAt(pre)) > 0) {
                            if (minLen > i - pre + 1) {
                                retValue = S.substring(pre, i + 1);
                                minLen = i - pre + 1;
                            }
                            count--;
                        }
                    }
                    pre++;
                }
            }
        }
        return retValue;
    }


    /**
     * 复用原来的位置，发现的时候，增加len，这样就可以实现重用
     */
    public static void countFrequenciesEfficient(int input[]) {
        int len = input.length;
        for (int i = 0; i < len; i++) {
            input[i]--;
        }
        for (int i = 0; i < len; i++) {
            input[input[i] % len] += len;
        }
        for (int i = 0; i < len; i++) {
            System.out.println((i + 1) + " -> " + input[i] / len);
            input[i] = input[i] % len + 1;
        }


//        int n = input.length;
//        for (int i = 0; i < n; i++) {
//            input[i]--;
//        }
//
//        for (int i = 0; i < n; i++) {
//            input[input[i] % n] += n;
//        }
//
//        for (int i = 0; i < n; i++) {
//            System.out.println((i + 1) + " " + input[i] / n);
//            // Change the element back to original value
//            input[i] = input[i] % n + 1;
//        }
    }

    public static List<Integer> leaders(int[] nums) {
        List<Integer> retValues = new ArrayList<>();
        int index = nums.length - 1, curMax = nums[index];
        retValues.add(curMax);
        while (--index >= 0) {
            if (nums[index] >= curMax) {
                curMax = nums[index];
                retValues.add(curMax);
            }
        }
        return retValues;
    }

    public static Set<String> permutations(String source) {
        Set<String> perms = new HashSet<>();
        if (source == null || source.length() == 0) {
            perms.add("");
            return perms;
        }
        char first = source.charAt(0);
        String remainString = source.substring(1);
        for (String perm : permutations(remainString)) {
            for (int i = 0; i < perm.length() + 1; i++) {
                String prefix = perm.substring(0, i);
                String suffix = perm.substring(i);
                perms.add(prefix + first + suffix);
            }
        }
        return perms;
    }

    public static int lis(int[] source) {
        int len = source.length;
        if (len == 0) return 0;
        int[] table = new int[len];
        table[0] = 1;
        int maxLen = 1;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (source[i] > source[j]) {
                    table[i] = Math.max(table[i], table[j] + 1);
                    maxLen = table[i];
                }
            }
        }
        return maxLen;
    }

    public static int getMaxCold(int[][] goldMine) {
        int row = goldMine.length;
        if (row == 0) return 0;
        int col = goldMine[0].length;
        int[][] matrix = new int[row][col];
        for (int c = col - 1; c >= 0; c--) {
            for (int r = 0; r < row; r++) {
                int rightValue = c + 1 <= col - 1 ? goldMine[r][c + 1] : 0;
                int rightUpValue = c + 1 <= col - 1 && r - 1 >= 0 ? goldMine[r - 1][c + 1] : 0;
                int rightDownValue = c + 1 <= col - 1 && r + 1 <= row - 1 ? goldMine[r + 1][c + 1] : 0;
                matrix[r][c] = goldMine[r][c] + Math.max(rightValue, Math.max(rightUpValue, rightDownValue));
            }
        }
        int max = matrix[0][0];
        for (int i = 1; i < row; i++) {
            if (matrix[i][0] > max) {
                max = matrix[i][0];
            }
        }
        return max;
    }

    public static void distributeChocolates(int[] chocolatePackets, int n) {


        if (chocolatePackets == null || chocolatePackets.length == 0 || n == 0) {
            return;
        }

        Arrays.sort(chocolatePackets);
        System.out.println(Arrays.toString(chocolatePackets));

        int m = chocolatePackets.length;
        if (m < n) {
            System.out.println("Number of students is more than number of packets. Cannot distribute!");
            return;
        }
        int minDiff = chocolatePackets[m - 1];
        int first = 0;
        int last = 0;
        for (int i = 0; i + n - 1 < m; i++) {
            int diff = chocolatePackets[i + n - 1] - chocolatePackets[i];
            if (diff < minDiff) {
                minDiff = diff;
                first = i;
                last = i + n - 1;
            }
        }
        System.out.println(chocolatePackets[first] + " " + chocolatePackets[last]);
    }

    public static void main(String[] args) {
        int[] chocolatePackets = {12, 4, 7, 9, 2, 23, 25, 41, 30, 40, 28, 42, 30, 44, 48, 43, 50};
        distributeChocolates(chocolatePackets, 7);
    }
}
