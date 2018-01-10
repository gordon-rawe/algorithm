package com.books.way2offer;

import java.util.Stack;

/**
 * Created by gordon on 1/10/18.
 */
public class Solution22 {

    /*判断两个数组是否是一个栈的压入弹出顺序*/

    public static void main(String[] args) {
        System.out.println(isStackOrder(
                new int[]{1, 2, 3, 4, 5},
                new int[]{4, 5, 3, 2, 1}
        ));
        System.out.println(isStackOrder(
                new int[]{1, 2, 3, 4, 5},
                new int[]{4, 3, 5, 1, 2}
        ));
    }

    /*采用一个栈进行模拟，每次压入一个元素，然后用现在的pop序列贪心的去pop*/
    // TODO: 1/10/18  need to practise more
    private static boolean isStackOrder(int[] sPush, int[] sPop) {
        if (sPop == null || sPush == null || sPush.length != sPop.length) {
            return false;
        }
        Stack<Integer> records = new Stack<>();
        int pPop = 0;
        for (int i = 0; i < sPush.length; i++) {
            records.push(sPush[i]);
            if (sPop[pPop] == records.peek()) {
                /*贪心的往外pop*/
                while (pPop < sPop.length && sPop[pPop] == records.peek()) {
                    pPop++;
                    records.pop();
                }
            }
        }
        return records.isEmpty();
    }
}
