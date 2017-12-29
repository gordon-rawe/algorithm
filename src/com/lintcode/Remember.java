package com.lintcode;

import com.common.Utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by books on 12/11/17.
 */
public class Remember {

    /**
     * todo remember this before interview
     */
    private static void permutations(int[] nums) {
        permutationHelper(nums, 0);
    }

    /**
     * 和字符串虽然在写法上面不一样，但是其实本质上是一样的，
     */
    private static void permutationHelper(int[] nums, int index) {
        if (index == nums.length) {
            System.out.println(Arrays.toString(nums));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            Utils.swap(nums, i, index);
            permutationHelper(nums, index + 1);
            Utils.swap(nums, i, index);
        }
    }

    /**
     * TODO: 12/11/17 需要背诵,递归解法
     */
    private static Set<String> permutations(String source) {
        if (source == null || source.length() == 0) return Collections.emptySet();
        Set<String> permutationStrings = new HashSet<>();
        if (source.length() == 1) return permutationStrings;
        char sCh = source.charAt(0);
        for (String perm : permutations(source.substring(1))) {
            for (int i = 0; i <= perm.length(); i++) {
                String prefix = perm.substring(0, i);
                String suffix = perm.substring(i, perm.length());
                permutationStrings.add(prefix + sCh + suffix);
            }
        }
        return permutationStrings;
    }

    public static void main(String[] args) {
        permutations(new int[]{1, 2, 3});
    }
}
