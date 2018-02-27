package com.lintcode.solves;

/**
 * Created by gordon on 2/26/18.
 */
public class Solution83 {

    public static void main(String[] args) {
        System.out.println(singleNumberII(new int[]{
                1, 1, 1, 4
        }));
    }

    /**
     * 把数组中数字的每一位累加起来对3取余，剩下的结果就是那个单独数组该位上的数字，
     * 由于我们累加的过程都要对3取余，那么每一位上累加的过程就是0->1->2->0，换成
     * 二进制的表示为00->01->10->00，那么我们可以写出对应关系：
     * 00 (+) 1 = 01
     * 01 (+) 1 = 10
     * 10 (+) 1 = 00 ( mod 3)
     * 那么我们用ab来表示开始的状态，对于加1操作后，得到的新状态的ab的算法如下：
     * b = b xor r & ~a;
     * a = a xor r & ~b;
     * 我们这里的ab就是上面的三种状态00，01，10的十位和各位，刚开始的时候，a和b都是
     * 0，当此时遇到数字1的时候，b更新为1，a更新为0，就是01的状态；再次遇到1的时候，
     * b更新为0，a更新为1，就是10的状态；再次遇到1的时候，b更新为0，a更新为0，就是
     * 00的状态，相当于重置了；最后的结果保存在b中。明白了上面的分析过程，就能写出代
     * 码如下；
     */
    // TODO: 2/26/18  need to be remembered.
    public static int singleNumberII(int[] A) {
        int a = 0, b = 0;
        for (int i = 0; i < A.length; i++) {
            b = (b ^ A[i]) & ~a;
            a = (a ^ A[i]) & ~b;
        }
        return b;
    }
}
