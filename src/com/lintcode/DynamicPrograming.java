package com.lintcode;

import java.util.*;

/**
 * Created by books on 10/24/17.
 */
public class DynamicPrograming {


    /**
     * 计算三角形的最大路径
     */
    public int minimumTrianglePath(int[][] triangle) {
        int ROW_COUNT = triangle.length, COL_COUNT = triangle[ROW_COUNT - 1].length;
        for (int i = ROW_COUNT - 2; i >= 0; i--) {
            for (int j = 0; j < triangle[i].length; j++) {
                triangle[i][j] += Math.min(triangle[i + 1][j], triangle[i + 1][j + 1]);
            }
        }
        return triangle[0][0];
    }

    /**
     * 计算三角形的最大路径,空间压缩版
     */
    public int minimumTrianglePathSpaceEfficient(int[][] triangle) {
        int ROW_COUNT = triangle.length;
        int[] dpTable = new int[ROW_COUNT];
        for (int i = ROW_COUNT - 1; i >= 0; i--) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (i == ROW_COUNT - 1) {
                    dpTable[j] = triangle[i][j];
                } else {
                    dpTable[j] = Math.min(dpTable[j], dpTable[j + 1]) + triangle[i][j];
                }
            }
        }
        return dpTable[0];
    }

    /**
     * 0，1背包
     * 可以有cost为0的元素
     */
    public static int knapsack(int[] costs, int[] values, int bagLimit) {
        int[][] dpTable = new int[costs.length + 1][bagLimit + 1];
        for (int i = 0; i <= costs.length; i++) {
            for (int j = 0; j <= bagLimit; j++) {
                if (i * j != 0) {
                    dpTable[i][j] = dpTable[i - 1][j];
                    if (j >= costs[i - 1]) {
                        dpTable[i][j] = Math.max(dpTable[i][j], dpTable[i - 1][j - costs[i - 1]] + values[i - 1]);
                    }
                }
            }
        }
        return dpTable[costs.length][bagLimit];
    }

    /**
     * 0，1背包
     * 可以有cost为0的元素
     */
    public static int knapsacksSpaceEfficient(int[] costs, int[] values, int bagLimit) {
        int[] table = new int[bagLimit + 1];
        for (int i = 0; i < costs.length; i++) {
            for (int j = bagLimit; j >= 0; j--) {
                if (j >= costs[i]) {
                    table[j] = Math.max(table[j], table[j - costs[i]] + values[i]);
                }
            }
        }
        return table[bagLimit];
    }

    /**
     * 多重背包
     * 不能有cost为0的元素
     */
    public static int knapsacksMulti(int[] costs, int[] values, int bagLimit) {
        int[] table = new int[bagLimit + 1];
        for (int i = 0; i < costs.length; i++) {
            for (int j = bagLimit; j >= 0; j--) {
                for (int k = 1; k <= j / costs[i]; k++) {
                    table[j] = Math.max(table[j], table[j - k * costs[i]] + k * values[i]);
                }
            }
        }
        return table[bagLimit];
    }

    /**
     * 根据股票趋势图算交易一次的股票最大收益
     */
    public static int maxStockProfitI(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int min = nums[0], maxProfit = 0;
        for (int i = 1; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            maxProfit = Math.max(maxProfit, nums[i] - min);
        }
        return maxProfit;
    }

    /**
     * 根据股票趋势图算交易无限次的股票最大收益
     */
    private static int maxStockProfitII(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int maxProfit = 0;
        for (int i = 1; i < nums.length; i++) {
            maxProfit += Math.max(0, nums[i] - nums[i - 1]);
        }
        return maxProfit;
    }

    /**
     * 根据股票趋势图算交易最多2次的股票最大收益
     */
    public static int maxStockProfitIII(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int maxProfit = 0, min = nums[0], max = nums[nums.length - 1];
        int[] preProfits = new int[nums.length];
        int[] postProfits = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            preProfits[i] = Math.max(preProfits[i - 1], nums[i] - min);
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            max = Math.max(max, nums[i]);
            postProfits[i] = Math.max(postProfits[i + 1], max - nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            maxProfit = Math.max(maxProfit, preProfits[i] + postProfits[i]);
        }
        return maxProfit;
    }

    /**
     * TODO: 12/9/17  this needs to be remembered before interview, cannot be understood.
     * */
    public static int maxStockProfitIV(int[] nums, int k) {
        if (nums == null || nums.length < 2) return 0;
        if (k >= nums.length / 2) return maxStockProfitII(nums);
        int[][] local = new int[nums.length][k + 1];
        int[][] global = new int[nums.length][k + 1];
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j < k + 1; j++) {
                local[i][j] = Math.max(global[i - 1][j - 1], local[i - 1][j] + nums[i] - nums[i - 1]);
                global[i][j] = Math.max(global[i - 1][j], local[i][j]);
            }
        }
        return global[nums.length - 1][k];
    }

    public static int maxTwoSubArrays(List<Integer> nums) {
        if (nums == null || nums.size() < 2) return nums.get(0);
        int[] preMaxes = new int[nums.size()];
        int[] postMaxes = new int[nums.size()];
        int preSum = 0, postSum = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (i == 0) {
                preSum = nums.get(0);
                preMaxes[i] = nums.get(i);
            } else {
                preSum = preSum >= 0 ? preSum + nums.get(i) : nums.get(i);
                preMaxes[i] = Math.max(preMaxes[i - 1], preSum);
            }
        }
        for (int i = nums.size() - 1; i >= 0; i--) {
            if (i == nums.size() - 1) {
                postMaxes[i] = nums.get(i);
                postSum = nums.get(i);
            } else {
                postSum = postSum > 0 ? postSum + nums.get(i) : nums.get(i);
                postMaxes[i] = Math.max(postMaxes[i + 1], postSum);
            }
        }
        int maxResult = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size() - 1; i++) {
            maxResult = Math.max(maxResult, preMaxes[i] + postMaxes[i + 1]);
        }
        return maxResult;
    }

    /**
     * 主要难点在于某个位置的数字的表示上面，采取空间换时间比较合适，
     * 因为其实整数是没有多少位数的，空间上面不会有多大开销
     */
    public static boolean isPalindromeEfficient(int num) {
        if (num < 0) return false;
        if (num == 0) return true;
        int N = 0;
        int tester = num;
        while (tester > 0) {
            N++;
            tester /= 10;
        }
        int[] denoteArr = new int[N];
        tester = num;
        int i = N - 1;
        while (tester > 0) {
            denoteArr[i--] = tester % 10;
            tester /= 10;
        }
        int left = 0, right = N - 1;
        while (left < right) {
            if (denoteArr[left++] != denoteArr[right--]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 回文的最小分割步骤,先算回文dpTable，递归看之前的最小。
     */
    private static int palindromePartitionEfficient(String s) {
        int COUNT = s.length();
        boolean[][] memo = new boolean[COUNT][COUNT];
        int[] dpTable = new int[COUNT];
        for (int l = 1; l <= COUNT; l++) {
            for (int i = 0; i < COUNT - l + 1; i++) {
                int j = i + l - 1;
                if (l == 1) {
                    memo[i][j] = true;
                } else if (l == 2) {
                    memo[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    memo[i][j] = memo[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                }
            }
        }
        for (int i = 0; i < COUNT; i++) {
            if (memo[0][i]) {
                dpTable[i] = 0;
            } else {
                dpTable[i] = Integer.MAX_VALUE;
                for (int j = 1; j <= i; j++) {
                    if (memo[j][i]) {
                        dpTable[i] = Math.min(dpTable[i], dpTable[j - 1] + 1);
                    }
                }
            }
        }
        return dpTable[COUNT - 1];
    }

    /**
     * 长度从1到COUNT，1长度为1时回文长度为1，长度为2时如果相等为2，更多的就需要满足i+1,j-1是回文并且相等这个条件了。
     */
    public static String longestPalindrome(String s) {
        assert s != null;
        int COUNT = s.length();
        int[][] memo = new int[COUNT][COUNT];
        int maxI = 0, maxJ = 0, max = 0;
        for (int l = 1; l <= COUNT; l++) {
            for (int i = 0; i < COUNT - l + 1; i++) {
                int j = i + l - 1;
                if (l == 1) {
                    memo[i][j] = 1;
                } else if (l == 2) {
                    memo[i][j] = s.charAt(i) == s.charAt(j) ? 2 : 0;
                } else {
                    memo[i][j] = memo[i + 1][j - 1] > 0 && s.charAt(i) == s.charAt(j) ?
                            memo[i + 1][j - 1] + 2 : 0;
                }
                if (max < memo[i][j]) {
                    max = memo[i][j];
                    maxI = i;
                    maxJ = j;
                }
            }
        }
        return s.substring(maxI, maxJ + 1);
    }

    /**
     * 题目：字符串是否可被词组拆解
     * 在每个i位置，j扫描之前的0 -> i-1位置，看是否满足j是可以分解，并且最后一个词语也可以分解
     */
    public static boolean wordBreak(String str, Set<String> dict) {
        if (dict.size() == 0 || str == null || str.length() == 0) return false;
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

    /**
     * 硬币的表示方法数目
     */
    public static int denotationOfCoins(int n) {
        int[] coins = new int[]{1, 5, 10, 25};
        if (n <= 0) return 0;
        int[][] dpTable = new int[coins.length][n + 1];
        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == 0) {
                    //只使用1的来分解，则只有一种表示方法
                    dpTable[i][j] = 1;
                } else {
                    //不用和用这枚硬币的两种历史记录的和
                    dpTable[i][j] = dpTable[i - 1][j];
                    if (j - coins[i] >= 0) {
                        dpTable[i][j] += dpTable[i][j - coins[i]];
                    }
                }
            }
        }
        return dpTable[coins.length - 1][n];
    }


    /**
     * 这道题主要是有三个条件
     * 一位数的时候不能为0，二位数的时候要小于27，而且两位数的时候不能以0开头
     * 主要难以理解是2位数的时候0打头不算,这个理解了这道题很好做
     */
    public static int decodeWays(String numberString) {
        if (numberString == null || numberString.length() == 0) return 0;
        int[] dpTable = new int[numberString.length() + 1];
        dpTable[0] = 1;//考虑第二个字符，如果和第一个匹配为字母，那么会增加1，而参考的基数是dpTable[0]，所以赋1
        for (int i = 1; i <= numberString.length(); i++) {
            char ch = numberString.charAt(i - 1);
            dpTable[i] = ch == '0' ? 0 : dpTable[i - 1];
            if (i > 1) {
                char lastCh = numberString.charAt(i - 2);
                if (lastCh == '1' || lastCh == '2' && ch < '7') {
                    dpTable[i] += dpTable[i - 2];
                }
            }
        }
        return dpTable[numberString.length()];
    }

    /**
     * 最长相同字串
     */
    public static int lcs(String a, String b) {
        int[][] dpTable = new int[a.length()][b.length()];
        int max = 0;
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                if (i == 0 || j == 0) {
                    dpTable[i][j] = a.charAt(i) == b.charAt(j) ? 1 : 0;
                } else {
                    dpTable[i][j] = a.charAt(i) == b.charAt(j) ? dpTable[i - 1][j - 1] + 1 : 0;
                }
                if (max < dpTable[i][j]) {
                    max = dpTable[i][j];
                }
            }
        }
        return max;
    }

    /**
     * 一个双端队列，从后往前去掉小的，从前往后挤掉老的
     */
    public static List<Integer> maxSlideWindow(int[] nums, int k) {
        List<Integer> maxNums = new ArrayList<>();
        if (nums == null || nums.length == 0) return maxNums;
        Deque<Integer> maxQueue = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (maxQueue.size() > 0 && i < nums.length && nums[maxQueue.peekLast()] <= nums[i]) {
                maxQueue.pollLast();
            }
            maxQueue.add(i);
        }
        for (int i = k; i < nums.length; i++) {
            maxNums.add(nums[maxQueue.peekFirst()]);
            while (maxQueue.size() > 0 && nums[maxQueue.peekLast()] <= nums[i]) {
                maxQueue.pollLast();
            }
            while (maxQueue.size() > 0 && maxQueue.peekFirst() <= i - k) {
                maxQueue.pollFirst();
            }
            maxQueue.add(i);
        }
        maxNums.add(nums[maxQueue.peekFirst()]);
        return maxNums;
    }

    /**
     * 序列source中包含多少个target,主要考虑两种情况，如果source和target的最后一个字符不相等，
     * 那么，需要看source舍弃当前这位字母的包含情况，如果相等，那么要还要加上都舍弃最后一位的包含数，
     * 状态转移是dp[i][j] = s[i] == t[j] ? dp[i - 1][j] : dp[i - 1][j] + dp[i - 1][j - 1];
     */
    // TODO: 12/9/17  this needs to be remembered before interview, cannot be understood.
    public static int maxDistinctSequences(String source, String target) {
        int[][] dpTable = new int[source.length() + 1][target.length() + 1];
        for (int i = 0; i <= source.length(); i++) {
            for (int j = 0; j <= target.length(); j++) {
                if (j == 0) {
                    dpTable[i][j] = 1;
                } else if (i == 0) {
                    dpTable[i][j] = 0;
                } else {
                    if (source.charAt(i - 1) != target.charAt(j - 1)) {
                        dpTable[i][j] = dpTable[i - 1][j];
                    } else {
                        dpTable[i][j] = dpTable[i - 1][j] + dpTable[i - 1][j - 1];
                    }
                }
            }
        }
        for (int[] ints : dpTable) {
            System.out.println(Arrays.toString(ints));
        }

        return dpTable[source.length()][target.length()];
    }

    /**
     * strT是否由strA和strB交错组合而成，先算边界，全取a或b，
     * 然后每个位置都看是否能够构成strT
     * dpTable[i][j]的定义是strA的前i个字符和strB的前j个字符能够组成strT的前i+j-1个字符
     */
    public static boolean isInterleaving(String strA, String strB, String strT) {
        if (strA == null || strB == null || strT == null) return false;
        int lenA = strA.length(), lenB = strB.length(), lenT = strT.length();
        if (lenA + lenB != lenT) return false;
        boolean[][] dpTable = new boolean[lenA + 1][lenB + 1];
        dpTable[0][0] = true;
        for (int i = 1; i <= lenA; i++) {
            dpTable[i][0] = dpTable[i - 1][0] && strA.charAt(i - 1) == strT.charAt(i - 1);
        }
        for (int i = 1; i <= lenB; i++) {
            dpTable[0][i] = dpTable[0][i - 1] && strB.charAt(i - 1) == strT.charAt(i - 1);
        }
        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {
                dpTable[i][j] =
                        (dpTable[i - 1][j] && strA.charAt(i - 1) == strT.charAt(i + j - 1)) ||
                                (dpTable[i][j - 1] && strB.charAt(j - 1) == strT.charAt(i + j - 1));
            }
        }
        return dpTable[lenA][lenB];
    }

    public static float medianOfTwoArraySorted(int[] arrA, int[] arrB) {
        assert arrA != null && arrB != null;
        int lenA = arrA.length, lenB = arrB.length, total = lenA + lenB;
        if (total % 2 == 0) {
            return kThOfTwoSortedArray(arrA, arrB, total / 2 + 1) / 2.0F +
                    kThOfTwoSortedArray(arrA, arrB, total / 2) / 2.0F;
        } else {
            return kThOfTwoSortedArray(arrA, arrB, total / 2 + 1);
        }
    }

    private static float kThOfTwoSortedArray(int[] arrA, int[] arrB, int k) {
        int lenA = arrA.length, lenB = arrB.length;
        if (lenA > lenB) {
            return kThOfTwoSortedArray(arrB, arrA, k);
        }
        if (lenA == 0) return arrB[k - 1];
        int left = 0, right = lenA;
        while (left <= right) {
            int mid1 = (left + right) / 2;//arrA第mid1个数字
            int mid2 = (k - mid1);//arrB第mid2个数字
            if (mid2 > lenB) {
                return kThOfTwoSortedArray(arrB, arrA, k);
            }
            int L1 = mid1 == 0 ? Integer.MIN_VALUE : arrA[mid1 - 1];
            int L2 = mid2 == 0 ? Integer.MIN_VALUE : arrB[mid2 - 1];
            int R1 = mid1 == lenA ? Integer.MAX_VALUE : arrA[mid1];
            int R2 = mid2 == lenB ? Integer.MAX_VALUE : arrB[mid2];
            if (L1 > R2) {
                right = mid1 - 1;
            } else if (L2 > R1) {
                left = mid1 - 1;
            } else {
                return Math.max(L1, L2);
            }
        }
        return -1F;
    }

    public static int[] medianII(int[] nums) {
        assert nums != null && nums.length > 0;
        int[] result = new int[nums.length];
        PriorityQueue<Integer> smallNumbers = new PriorityQueue<>((x, y) -> y - x);
        PriorityQueue<Integer> bigNumbers = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            if (smallNumbers.size() == 0) {
                smallNumbers.add(nums[i]);
            } else if (smallNumbers.size() == bigNumbers.size()) {
                if (nums[i] > bigNumbers.peek()) {
                    smallNumbers.add(bigNumbers.peek());
                    bigNumbers.poll();
                    bigNumbers.add(nums[i]);
                } else {
                    smallNumbers.add(nums[i]);
                }
            } else {
                if (nums[i] < smallNumbers.peek()) {
                    bigNumbers.add(smallNumbers.poll());
                    smallNumbers.add(nums[i]);
                } else {
                    bigNumbers.add(nums[i]);
                }
            }
            result[i] = smallNumbers.peek();
        }
        return result;
    }

    /**
     * 道理很简单，主要是为了避免出现大数越界问题
     */
    public static double pow(double x, int n) {
        /*主要要解决大数问题,负数*/
        double res = 1.0;
        for (int i = n; i != 0; i /= 2) {
            /*当奇数和1的时候，刚好进行赋值，非常的巧*/
            if (i % 2 == 0) res *= x;
            x *= x;
        }
        return n > 0 ? res : 1 / res;
    }

    /**
     * 正则表达式,*可以匹配一串x*,分两种场景，s为空的时候，只能全部为x*x*滚动着走，不然就不行，
     * 往后的运算中，如果p最后一个字符为*分两种，把x*看作空，那么需要dpTable[i][j - 2]为true,
     * 把x*不看做空，那么需要满足：1. 他们的上一个字符对上，即s.charAt(i - 1) == p.charAt(j - 2)
     * || p.charAt(j - 2) == '.', 2. 即然x*配上的至少是s.charAt(i - 1),那么要求dpTable[i - 1][j]必须为true
     */
    public static boolean regexMatching(String s, String p) {
        assert s != null && p != null;
        int lenS = s.length(), lenP = p.length();
        boolean[][] dpTable = new boolean[lenS + 1][lenP + 1];
        dpTable[0][0] = true;
        for (int j = 1; j <= lenP; j++) {
            dpTable[0][j] = j > 1 && dpTable[0][j - 2] && p.charAt(j - 1) == '*';
        }
        for (int i = 1; i <= lenS; i++) {
            for (int j = 1; j <= lenP; j++) {
                if (p.charAt(j - 1) == '*' && j > 1) {
                    //如果x*的前面是true，则增加x*也可以
                    dpTable[i][j] = dpTable[i][j - 2] ||

                            (s.charAt(i - 1) == p.charAt(j - 2) ||
                                    p.charAt(j - 2) == '.') && dpTable[i - 1][j];
                } else {
                    //匹配当前字符和当前pattern的最后字符
                    dpTable[i][j] = dpTable[i - 1][j - 1] &&
                            (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.');
                }
            }
        }
        return dpTable[lenS][lenP];
    }

    /**
     * wildcard,*可以匹配一串任意字符,分两种场景，s为空的时候，只能全部为*，
     * 往后的运算中，如果p最后一个字符为*，把*看作空，那么需要dpTable[i][j - 1]为true,
     * 把*看作一串，那么需要dpTable[i - 1][j]为true，意思是p已经当成*使用匹配了前面一
     * 串了，那么搭上最后一个任意字符也ok
     */
    public static boolean wildcard(String s, String p) {
        assert s != null && p != null;
        int lenS = s.length(), lenP = p.length();
        boolean[][] dpTable = new boolean[lenS + 1][lenP + 1];
        dpTable[0][0] = true;
        for (int j = 1; j <= lenP; j++) {
            dpTable[0][j] = dpTable[0][j - 1] && p.charAt(j - 1) == '*';
        }
        for (int i = 1; i <= lenS; i++) {
            for (int j = 1; j <= lenP; j++) {
                dpTable[i][j] = ((p.charAt(j - 1) == '?' ||
                        p.charAt(j - 1) == s.charAt(i - 1)) && dpTable[i - 1][j - 1])
                        || (p.charAt(j - 1) == '*' && (dpTable[i][j - 1] || dpTable[i - 1][j]));
            }
        }
        return dpTable[lenS][lenP];
    }

    public static void main(String[] args) {
        System.out.println(palindromePartitionEfficient("abcba"));
    }
}
