package com.lintcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by books on 11/20/17.
 */
public class LinkedIn {
    /**
     * 寻找数组中的峰值，采用二分法，哪边上坡往哪边，由于mid不可能为右值，
     * 所以+1不会越界，当相遇的时候，取较大的一边即可。
     * */
    public static int findPeakElement(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (mid == left) {
                return arr[left] > arr[right] ? left : right;
            }
            /**
             * 因为mid总是会小于right，所以不会越界，如此进行比较
             * */
            if (arr[mid + 1] > arr[mid]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return arr[left];
    }

    /**
     * 将0移到最后,比较简单
     * */
    public static void moveZerosEnd(int[] nums) {
        int start = 0;
        while (start < nums.length) {
            if (nums[start] == 0) {
                int end = start;
                while (end < nums.length && nums[end] == 0) end++;
                if (end < nums.length) {
                    nums[start] = nums[start] ^ nums[end];
                    nums[end] = nums[start] ^ nums[end];
                    nums[start] = nums[start] ^ nums[end];
                }
            }
            start++;
        }
    }

    /**
     * 注意二分法的int值可能超限,引发循环
     */
    public static int findFirstBadVersion(int n) {
        int start = 1, end = n;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (mid == start) {
                return isBadVersion(start) ? start : end;
            }
            if (isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        return start;
    }

    private static boolean isBadVersion(int num) {
        return false;
    }

    /**
     * 计算数组是否包含某字符串的路径
     */
    public boolean existWord(char[][] board, String word) {
        int ROW_COUNT = board.length, COL_COUNT = board[0].length;
        boolean[][] visited = new boolean[ROW_COUNT][COL_COUNT];
        int index = 0;
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COL_COUNT; j++) {
                if (searchHelper(board, visited, word, index, i, j, ROW_COUNT, COL_COUNT)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 上下左右进行搜索，使用visited数组进行辅助
     */
    private static boolean searchHelper(char[][] board, boolean[][] visited,
                                        String word, int index, int i, int j, int ROW_COUNT, int COL_COUNT) {
        if (i >= 0 && j >= 0 && i < ROW_COUNT && j < COL_COUNT && !visited[i][j] && board[i][j] == word.charAt(index)) {
            visited[i][j] = true;
            if (index == word.length() - 1) return true;
            boolean result =
                    searchHelper(board, visited, word,
                            index + 1, i + 1, j, ROW_COUNT, COL_COUNT) ||
                            searchHelper(board, visited, word,
                                    index + 1, i, j + 1, ROW_COUNT, COL_COUNT) ||
                            searchHelper(board, visited, word,
                                    index + 1, i - 1, j, ROW_COUNT, COL_COUNT) ||
                            searchHelper(board, visited, word,
                                    index + 1, i, j - 1, ROW_COUNT, COL_COUNT);
            visited[i][j] = false;
            return result;
        }
        return false;
    }

    // TODO: 12/9/17  三个顺序倒着写
    public static void sortColors1(int[] colors) {
        int zero = 0, one = 0, two = 0;
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] == 0) {
                colors[two++] = 2;
                colors[one++] = 1;
                colors[zero++] = 0;
            } else if (colors[i] == 1) {
                colors[two++] = 2;
                colors[one++] = 1;
            } else {
                colors[two++] = 2;
            }
            System.out.println(Arrays.toString(colors));
        }
    }

    /**
     * 计算单次交易的股票最大收益
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int curMin = prices[0], maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (curMin > prices[i]) {
                curMin = prices[i];
            } else if (maxProfit < prices[i] - curMin) {
                maxProfit = prices[i] - curMin;
            }
        }
        return maxProfit;
    }

    /**
     * 加一个辅助变量reach，从0开始满足i<=reach,当reach能达到nums.length - 1就可以over啦
     */
    public static boolean jumpGame(int[] nums) {
        if (nums == null) return true;
        int reach = 0;
        for (int i = 0; i < nums.length && i <= reach; i++) {
            reach = Math.max(reach, nums[i] + i);
            if (reach >= nums.length - 1) return true;
        }
        return false;
    }

    /**
     * 达到最后的最小步数
     * TODO: 12/10/17 需要记住,4个变量
     */
    public static int jumpGameII(int[] nums) {
        if (nums == null) return -1;
        int step = 0, curMax = 0, nextMax = 0, index = 0;
        while (index <= curMax) {
            /**
             * 寻找当前科大范围内最大的区域
             * */
            while (index <= curMax) {
                nextMax = Math.max(nextMax, nums[index] + index);
                index++;
            }
            curMax = nextMax;
            step++;
            if (curMax >= nums.length - 1) return step;
        }
        return -1;
    }

    /**
     * 最长的上升子序列,一个dpTable不断check历史数据选取最优即可.
     */
    public static int lis(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dpTable = new int[nums.length];
        int maxResult = 0;
        Arrays.fill(dpTable, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dpTable[i] = Math.max(dpTable[j] + 1, dpTable[i]);
                    maxResult = Math.max(maxResult, dpTable[i]);
                }
            }
        }
        return maxResult;
    }

    /**
     * 回文的最小分割次数，首先求回文的boolean矩阵，然后用回顾法进行分割求解
     */
    public static int minPalindromeCut(String s) {
        int COUNT = s.length();
        boolean[][] memo = new boolean[COUNT][COUNT];
        int[] dpTable = new int[COUNT];
        for (int i = 0; i < COUNT; i++) {
            memo[i][i] = true;
        }
        for (int l = 2; l <= COUNT; l++) {
            for (int i = 0; i < COUNT - l + 1; i++) {
                int j = i + l - 1;
                if (l == 2) {
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
                for (int j = 0; j < i; j++) {
                    if (memo[j + 1][i] && dpTable[j] + 1 < dpTable[i]) {
                        dpTable[i] = dpTable[j] + 1;
                    }
                }
            }
        }
        return dpTable[COUNT - 1];
    }

    /**
     * 1 for land，求岛屿的最大区域
     */
    public static int numOfIslands(int[][] landscape) {
        int ROW_COUNT = landscape.length, COL_COUNT = landscape[0].length;
        boolean[][] visited = new boolean[ROW_COUNT][COL_COUNT];
        int resultCount = 0;
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COL_COUNT; j++) {
                if (!visited[i][j] && landscape[i][j] == 1) {
                    searchIsland(landscape, visited, i, j);
                    resultCount++;
                }
            }
        }
        return resultCount;
    }

    /**
     * 上下左右进行搜索回顾
     */
    private static void searchIsland(int[][] landscape, boolean[][] visited, int i, int j) {
        int ROW_COUNT = landscape.length, COL_COUNT = landscape[0].length;
        if (i >= 0 && j >= 0 && i < ROW_COUNT && j < COL_COUNT && !visited[i][j] && landscape[i][j] == 1) {
            visited[i][j] = true;
            searchIsland(landscape, visited, i + 1, j);
            searchIsland(landscape, visited, i, j + 1);
            searchIsland(landscape, visited, i - 1, j);
            searchIsland(landscape, visited, i, j - 1);
        }
    }

    /**
     * TODO: 12/10/17 需要背诵, 求矩阵的最大同质矩形区域，
     * 使用一个辅助栈
     */
    private static int maxHistogram(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0, index = 0;
        while (index < nums.length) {
            if (stack.isEmpty() || nums[index] >= nums[stack.peek()]) {
                stack.push(index++);
            } else {
                int top = stack.pop();
                int area;
                if (stack.isEmpty()) {
                    area = nums[top] * index;
                } else {
                    area = nums[top] * (index - 1 - stack.peek());
                }
                maxArea = Math.max(area, maxArea);
            }
        }
        while (!stack.isEmpty()) {
            int top = stack.pop();
            int area;
            if (stack.isEmpty()) {
                area = nums[top] * index;
            } else {
                area = nums[top] * (index - 1 - stack.peek());
            }
            maxArea = Math.max(area, maxArea);
        }
        return maxArea;
    }

    /**
     * 计算矩阵的最大值,可以遍历多个数组，如果有0，则区域截断。
     */
    public static int maximalRectangle(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int ROW_COUNT = matrix.length, COL_COUNT = matrix[0].length;
        int dpTable[] = new int[COL_COUNT];
        int maxArea = 0;
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COL_COUNT; j++) {
                dpTable[j] = matrix[i][j] == 1 ? dpTable[j] + 1 : 0;
            }
            maxArea = Math.max(maxArea, maxHistogram(dpTable));
        }
        return maxArea;
    }

    /**
     * 从矩阵的左上角走到右下角的最小的路径的值
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int ROW_COUNT = grid.length, COL_COUNT = grid[0].length;
        int[][] dpTable = new int[ROW_COUNT][COL_COUNT];
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COL_COUNT; j++) {
                if (i == 0 && j == 0) {
                    dpTable[i][j] = grid[0][0];
                } else if (i == 0) {
                    dpTable[0][j] = dpTable[0][j - 1] + grid[0][j];
                } else if (j == 0) {
                    dpTable[i][0] = dpTable[i - 1][0] + grid[i][0];
                } else {
                    dpTable[i][j] = grid[i][j] + Math.min(dpTable[i - 1][j], dpTable[i][j - 1]);
                }
            }
        }
        return dpTable[ROW_COUNT - 1][COL_COUNT - 1];
    }

    /**
     * 先从两边进行布局，那么事情就比较简单了，不太建议用位置索引。
     */
    public static int minimumEditDistance(String source, String target) {
        if (source == null || target == null) return 0;
        int lenA = source.length(), lenB = target.length();
        if (lenA == 0) return lenB;
        if (lenB == 0) return lenA;
        int[][] dpTable = new int[lenA + 1][lenB + 1];
        for (int i = 0; i <= lenA; i++) {
            for (int j = 0; j <= lenB; j++) {
                if (i == 0 && j == 0) {
                    dpTable[i][j] = 0;
                } else if (i == 0) {
                    dpTable[i][j] = j;
                } else if (j == 0) {
                    dpTable[i][j] = i;
                } else {
                    dpTable[i][j] = source.charAt(i - 1) == target.charAt(j - 1) ?
                            dpTable[i - 1][j - 1] : dpTable[i - 1][j - 1] + 1;
                    dpTable[i][j] = Math.min(dpTable[i][j], dpTable[i - 1][j] + 1);
                    dpTable[i][j] = Math.min(dpTable[i][j], dpTable[i][j - 1] + 1);
                }
            }
        }
        return dpTable[lenA][lenB];
    }

    public static void main(String[] args) {

    }
}
