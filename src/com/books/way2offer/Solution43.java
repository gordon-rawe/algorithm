package com.books.way2offer;

/**
 * Created by gordon on 1/4/18.
 */
public class Solution43 {
    /*
    * n个骰子投掷后，求所有可能的数值出现的概率,
    * 以6颗骰子投掷4次为例,使用自然索引index从1开始
    *   [0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        [0, 0, 1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        [0, 0, 0, 1, 3, 6, 10, 15, 21, 25, 27, 27, 25, 21, 15, 10, 6, 3, 1, 0, 0, 0, 0, 0, 0]
        [0, 0, 0, 0, 1, 4, 10, 20, 35, 56, 80, 104, 125, 140, 146, 140, 125, 104, 80, 56, 35, 20, 10, 4, 1]
    * */

    public static void main(String[] args) {
        possibilities(15, 6);
    }

    private static void possibilities(int time, int face) {
        if (time < 1 || face < 1) {
            return;
        }
        int[][] pRecords = new int[][]{new int[time * face + 1], new int[time * face + 1]};
        int flag = 0;
        //初始化dp的第一行
        for (int i = 1; i <= face; i++) {
            pRecords[flag][i] = 1;
        }
        for (int k = 2; k <= time; k++) {
            for (int i = 0; i < k; i++) {
                pRecords[1 - flag][i] = 0;
            }
            for (int i = k; i <= time * face; i++) {
                pRecords[1 - flag][i] = 0;
                for (int j = 1; j <= face && j <= i; j++) {
                    pRecords[1 - flag][i] += pRecords[flag][i - j];
                }
            }
            flag = 1 - flag;
        }
        double denominator = Math.pow(face, time);
        for (int i = time; i <= time * face; i++) {
            double possibility = pRecords[flag][i] / (denominator);
            System.out.println(i + " -> " + possibility);
        }
    }
}
