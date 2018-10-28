package com.lintcode.solves;

import java.util.Set;

/**
 * Created by gordon on 2/28/18.
 */
public class Solution107 {

    public static void main(String[] args) {

    }

    public boolean wordBreak(String str, Set<String> dict) {
        if (str == null) return false;
        int COUNT = str.length();
        boolean[] dpTable = new boolean[COUNT + 1];
        //设置初始值为true表示我们默认为是ok的
        dpTable[0] = true;
        //i表示下表
        for (int i = 1; i <= COUNT; i++) {
            for (int j = 0; j < i; j++) {
                if (dpTable[j] && dict.contains(str.substring(j, i))) {
                    dpTable[i] = true;
                    break;
                }
            }
        }
        return dpTable[COUNT];
    }

    public boolean wordBreakI(String str, Set<String> dict) {
        if (str == null) return false;
        int COUNT = str.length();
        boolean[] dpTable = new boolean[COUNT + 1];
        dpTable[0] = true;
        for (int i = 1; i <= str.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dict.contains(str.substring(j, i)) && dpTable[j]) {
                    dpTable[i] = true;
                    break;
                }
            }
        }
        return dpTable[COUNT];
    }
}
