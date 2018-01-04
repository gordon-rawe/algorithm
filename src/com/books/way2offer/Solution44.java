package com.books.way2offer;

import java.util.Arrays;

/**
 * Created by gordon on 1/4/18.
 */
public class Solution44 {

    /*求一组数字是不是扑克牌的顺子(straight)*/

    public static void main(String[] args) {
        System.out.println(straight(new int[]{1, 12, 13, 0, 0}));
    }

    /**
     * 先排序，然后计算非王的gap累计，如果可以用王进行弥补就是顺子。
     * 问考官，如果考虑1为Ace，那么12345不会是顺子，则需要将1处理为14
     */
    private static boolean straight(int[] pokes) {
        assert pokes != null && pokes.length == 5;
        int numOfGhost = 0;
        for (int i = 0; i < pokes.length; i++) {
            if (pokes[i] == 0) numOfGhost++;
            /*如果考虑1为Ace，那么12345不会是顺子，则需要将1处理为14*/
            if (pokes[i] == 1) pokes[i] = 14;
        }
        assert numOfGhost <= 2;
        Arrays.sort(pokes);
        int pIndex = numOfGhost;
        int gap = 0;
        while (pIndex < pokes.length - 1) {
            gap += (pokes[pIndex + 1] - pokes[pIndex] - 1);
            pIndex++;
        }
        return gap <= numOfGhost;
    }
}
