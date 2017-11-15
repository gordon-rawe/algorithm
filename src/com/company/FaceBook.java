package com.company;

import java.util.*;

/**
 * Created by gordon on 10/31/17.
 */
public class FaceBook {
    public static List<Integer> countAndSay(int n) {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        for (int i = 0; i < n - 1; i++) {
            int index = 1, count = 1;
            List<Integer> helper = new LinkedList<>();
            while (index < list.size()) {
                if (Objects.equals(list.get(index), list.get(index - 1))) {
                    count++;
                } else {
                    helper.add(count);
                    helper.add(list.get(index - 1));
                    count = 1;
                }
                index++;
            }
            helper.add(count);
            helper.add(list.get(index - 1));
            list = helper;
        }
        return list;
    }

    public static int numIslands(boolean[][] grid) {
        int row = grid.length, col = grid[0].length;
        int count = 0;
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!visited[i][j] && grid[i][j]) {
                    count++;
                    System.out.println(i + " " + j);
                    helper(grid, visited, i, j, row, col);
                }
            }
        }
        return count;
    }

    public static void helper(boolean[][] grid, boolean[][] visited, int row, int col, int ROW, int COL) {
        if (col >= COL || row >= ROW || row < 0 || col < 0 || visited[row][col] || !grid[row][col]) {
            return;
        }
        visited[row][col] = true;
        helper(grid, visited, row + 1, col, ROW, COL);
        helper(grid, visited, row, col + 1, ROW, COL);
        helper(grid, visited, row - 1, col, ROW, COL);
        helper(grid, visited, row, col - 1, ROW, COL);
    }

    public static int sqrt(int x) {
        if (x < 0) return -1;
        if (x == 0) return 0;
        int l = 1, r = x / 2 + 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (m * m <= x && m * m + 2 * m + 1 > x) {
                return m;
            }
            if (m * m < x) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return 0;
    }

    public static boolean isPalindrome(String S) {
        if (S == null || S.length() < 2) return true;
        int p1 = 0, p2 = S.length() - 1;
        S = S.toLowerCase();
        while (p1 < p2) {
            if (!isValid(S.charAt(p1))) {
                p1++;
            } else if (!isValid(S.charAt(p2))) {
                p2--;
            } else if (S.charAt(p1) != S.charAt(p2)) {
                return false;
            } else {
                p1++;
                p2--;
            }
        }
        return true;
    }

    public static boolean isValid(Character c) {
        return Character.isAlphabetic(c) || Character.isDigit(c);
    }

    public static void permutation(int[] nums, int index) {
        if (index == nums.length) {
            System.out.println(java.util.Arrays.toString(nums));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            permutation(nums, index + 1);
            swap(nums, index, i);
        }
    }

    private static void swap(int[] sample, int a, int b) {
        int tmp = sample[a];
        sample[a] = sample[b];
        sample[b] = tmp;
    }

    public static List<String> letterCombinations(String digits) {
        List<String> retValue = new ArrayList<>();
        helper(digits, 0, new ArrayList<>(), retValue);
        return retValue;
    }

    public static void helper(String digits, int index, List<Character> list, List<String> res) {
        if (index == digits.length()) {
            StringBuilder builder = new StringBuilder();
            for (Character character : list) {
                builder.append(character);
            }
            res.add(builder.toString());
            return;
        }
        Character[] chars = mapping(digits.charAt(index));
        if (chars.length == 0) {
            helper(digits, index + 1, list, res);
        } else {
            for (int i = 0; i < chars.length; i++) {
                list.add(chars[i]);
                helper(digits, index + 1, list, res);
                list.remove(list.size() - 1);
            }
        }
    }

    public static Character[] mapping(Character n) {
        switch (n) {
            case '2':
                return new Character[]{'a', 'b', 'c'};
            case '3':
                return new Character[]{'d', 'e', 'f'};
            case '4':
                return new Character[]{'g', 'h', 'i'};
            case '5':
                return new Character[]{'j', 'k', 'l'};
            case '6':
                return new Character[]{'m', 'n', 'o'};
            case '7':
                return new Character[]{'p', 'q', 'r', 's'};
            case '8':
                return new Character[]{'t', 'u', 'v'};
            case '9':
                return new Character[]{'w', 'x', 'y', 'z'};
            default:
                return new Character[]{};
        }
    }

    public static int minimumSize(int[] nums, int s) {
        int pre = 0, minSize = nums.length + 1, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum >= s) {
                while (sum - nums[pre] >= s) {
                    sum -= nums[pre];
                    pre++;
                }
                if (i - pre + 1 < minSize) {
                    minSize = i - pre + 1;
                }
            }
        }
        return minSize == nums.length + 1 ? -1 : minSize;
    }

    public static List<String> anagrams(String[] strs) {
        List<String> retValue = new ArrayList<>();
        Map<Character, Integer> tracer = new HashMap<>();
        boolean[] visited = new boolean[strs.length];
        for (int i = 0; i < strs.length; i++) {
            if (visited[i]) {
                continue;
            }
            boolean hasAnalog = false;
            for (int j = i + 1; j < strs.length; j++) {
                if (isAnalog(strs[i], strs[j], tracer)) {
                    visited[i] = visited[j] = true;
                    hasAnalog = true;
                    retValue.add(strs[j]);
                }
            }
            if (hasAnalog) {
                retValue.add(strs[i]);
            }
        }
        return retValue;
    }

    public static boolean isAnalog(String a, String b, Map<Character, Integer> tracer) {
        if (a == null || b == null || a.length() != b.length()) return false;
        tracer.clear();
        for (int i = 0; i < a.length(); i++) {
            if (tracer.containsKey(a.charAt(i))) {
                tracer.put(a.charAt(i), tracer.get(a.charAt(i)) + 1);
            } else {
                tracer.put(a.charAt(i), 1);
            }
        }
        for (int i = 0; i < b.length(); i++) {
            if (tracer.containsKey(b.charAt(i))) {
                tracer.put(b.charAt(i), tracer.get(b.charAt(i)) - 1);
            }
        }
        for (Integer integer : tracer.values()) {
            if (integer > 0) {
                return false;
            }
        }
        return true;
    }

    public static int findPeak(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (left == right) {
                return nums[left];
            } else if (left + 1 == right) {
                return nums[left] > nums[right] ? nums[left] : nums[right];
            } else if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return nums[mid];
            } else if (nums[mid] < nums[mid - 1]) {
                right = mid - 1;
            } else if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static int findPivotBinarySearch(int[] array) {
        if (array == null || array.length == 0) return -1;
        if (array.length == 1 || array[0] < array[array.length - 1]) return 0;
        int left = 0, right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (mid < array.length - 1 && array[mid] > array[mid + 1]) {
                return mid + 1;
            } else if (array[mid] > array[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return 0;
    }

    public static Set<Integer> getDuplicates(int[] array) {
        int n = array.length;
        Set<Integer> duplicates = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (array[Math.abs(array[i])] > 0) {
                array[Math.abs(array[i])] = -array[Math.abs(array[i])];
            } else {
                duplicates.add(Math.abs(array[i]));
            }
        }
        return duplicates;
    }

    public static int lowerBound(int[] result, int left, int right, int value) {
        int rightBound = right;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (result[mid] > value) {
                if (mid == rightBound || result[mid - 1] < value) {
                    return mid;
                }
                right = mid - 1;
            } else if (result[mid] == value) {
                return mid;
            } else if (result[mid] > value) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static int lisLogN(int[] source) {
        int len = source.length;
        if (len == 0) return 0;
        int[] result = new int[len];
        result[0] = source[0];
        int curLen = 1;
        for (int i = 1; i < len; i++) {
            int index = lowerBound(result, 0, curLen - 1, source[i]);
            System.out.println(index);
            if (index == -1) {
                result[curLen++] = source[i];
            } else {
                result[index] = source[i];
            }
        }
        System.out.println(Arrays.toString(result));
        return curLen;
    }

    public static int lis(int[] source) {
        int len = source.length;
        if (len == 0) return 0;
        int[] result = new int[len];
        result[0] = source[0];
        int curLen = 1;
        for (int i = 1; i < len; i++) {
            int index = -1;
            for (int j = curLen - 1; j >= 0; j--) {
                if (source[i] < result[j]) {
                    index = j;
                    break;
                }
            }
            System.out.println(index);
            if (index == -1) {
                result[curLen++] = source[i];
            } else {
                result[index] = source[i];
            }
        }
        return curLen;
    }

    public static int longestBitonicSequence(int[] source) {
        int len = source.length;
        if (len == 1) return 1;
        int[] lis = new int[len];
        int[] lds = new int[len];

        for (int i = 0; i < len; i++) {
            lis[i] = 1;
            for (int j = 0; j < i; j++) {
                if (source[i] > source[j]) {
                    lis[i] = Math.max(lis[j] + 1, lis[i]);
                }
            }
        }
        for (int i = 0; i < len; i++) {
            lds[i] = 1;
            for (int j = 0; j < i; j++) {
                if (source[i] < source[j]) {
                    lds[i] = Math.max(lds[j] + 1, lds[i]);
                }
            }
        }
        int maxLen = lis[0] + lds[0] - 1;
        for (int i = 1; i < len; i++) {
            if (maxLen < lis[i] + lds[i] - 1) {
                maxLen = lis[i] + lds[i] - 1;
            }
        }
        return maxLen;
    }

    public int getRequiredIndex(int[] binaryArray) {
        int prevIndex = -1, prevPrevIndex = -1;
        int maxLen = -1, requiredIndex = -1;
        for (int i = 0; i < binaryArray.length; i++) {
            if (binaryArray[i] == 0) {
                if (prevPrevIndex != -1) {
                    int curLen = i - prevPrevIndex - 1;
                    if (curLen > maxLen) {
                        maxLen = curLen;
                        requiredIndex = prevIndex;
                    }
                }
                prevIndex = i;
                prevPrevIndex = prevIndex;
            }
        }
        if (maxLen == -1) {
            if (prevPrevIndex == -1) {
                requiredIndex = prevIndex;
            } else {
                if (prevIndex > binaryArray.length - prevPrevIndex - 1) {
                    requiredIndex = prevPrevIndex;
                } else {
                    requiredIndex = prevIndex;
                }
            }
        }
        return requiredIndex;
    }

    public static void main(String[] args) {
        System.out.println(maximumProfitMulti(
                new int[]{
                        100, 80, 120, 130, 70, 60, 100, 125
                }
        ));
    }

    public static List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(11, (o1, o2) -> o2 - o1);

        List<int[]> ver = new ArrayList<>();  // 记录每一个竖线
        for (int i = 0; i < buildings.length; i++) {
            int[] temp = buildings[i];
            ver.add(new int[]{temp[0], temp[2]});  // 左边界竖线
            ver.add(new int[]{temp[1], -temp[2]});  // 右边界竖线 为了区分 存入负值
        }
        ver.sort((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return b[1] - a[1];
        });

        int cur, pre = 0;
        for (int i = 0; i < ver.size(); i++) {
            int[] temp = ver.get(i);
            if (temp[1] > 0) {
                maxHeap.offer(temp[1]);
                cur = maxHeap.peek();
            } else {
                maxHeap.remove(-temp[1]);
                cur = maxHeap.peek() == null ? 0 : maxHeap.peek();
            }
            if (cur != pre) {
                res.add(new int[]{temp[0], cur});
                pre = cur;
            }
        }
        return res;
    }

    public static int trapWater(int[] towers) {
        int len = towers.length;
        if (len == 0 || len == 1) return 0;
        int left = 0, right = len - 1, water = 0;
        int leftHeight = towers[left], rightHeight = towers[right];
        while (left < right) {
            if (leftHeight < rightHeight) {
                left++;
                if (leftHeight > towers[left]) {
                    water += leftHeight - towers[left];
                } else {
                    leftHeight = towers[left];
                }
            } else {
                right--;
                if (rightHeight > towers[right]) {
                    water += rightHeight - towers[right];
                } else {
                    rightHeight = towers[right];
                }
            }
        }
        return water;
    }

    public static int maximumProfit(int[] stockPrices) {
        int profit = 0;
        int minimumPrice = Integer.MAX_VALUE;
        for (int i = 0; i < stockPrices.length; i++) {
            profit = Math.max(profit, stockPrices[i] - minimumPrice);
            minimumPrice = Math.min(minimumPrice, stockPrices[i]);
        }
        return profit;
    }

    public static int maximumProfitMulti(int[] stockPrices) {
        int profit = 0;
        for (int i = 1; i < stockPrices.length; i++) {
            if (stockPrices[i] > stockPrices[i - 1]) {
                profit += stockPrices[i] - stockPrices[i - 1];
            }
        }
        return profit;
    }

    public static ListNode reverseLinkedList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode current = head;
        while (current.next != null) {
            ListNode auxNode = current.next;
            current.next = auxNode.next;
            auxNode.next = head;
            head = auxNode;
        }
        return head;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode parent = new ListNode(0);
        parent.next = head;
        int step = n - m;
        ListNode startNode = parent;
        for (int i = 0; i < m - 1; i++) {
            startNode = startNode.next;
        }
        ListNode tmpHead = startNode.next, current = tmpHead;
        while (step > 0) {
            ListNode auxNode = current.next;
            current.next = auxNode.next;
            auxNode.next = tmpHead;
            tmpHead = auxNode;
            step--;
        }
        startNode.next = tmpHead;
        return parent.next;
    }

    public ListNode partition(ListNode head, int x) {
        if (head == null) return null;
        ListNode lessHead = new ListNode(0), greatHead = new ListNode(0);
        ListNode pNode = head, less = lessHead, great = greatHead;
        while (pNode != null) {
            ListNode node = pNode;
            pNode = pNode.next;
            if (node.val >= x) {
                great.next = node;
                great = node;
                node.next = null;

            } else {
                less.next = node;
                less = node;
                node.next = null;
            }
        }
        less.next = greatHead.next;
        return lessHead.next;
    }

    public static void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode head2 = slow.next, head1 = head;
        slow.next = null;
        ListNode current = head2;
        while (current.next != null) {
            ListNode auxNode = current.next;
            current.next = auxNode.next;
            auxNode.next = head2;
            head2 = auxNode;
        }
        while (head2 != null) {
            ListNode node = head2;
            head2 = head2.next;
            node.next = head1.next;
            head1.next = node;
            head1 = node.next;
        }
    }
}
