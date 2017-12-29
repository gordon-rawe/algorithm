package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println(majorityNumberIII(Arrays.asList(1, 2, 1, 2, 1, 3, 3), 3));
    }

    private static void bubbleSort(int[] sample) {
        for (int i = 0; i < sample.length; i++) {
            for (int j = i; j < sample.length; j++) {
                if (sample[i] < sample[j]) {
                    swap(sample, i, j);
                }
            }
        }
    }

    private static void selectSort(int[] sample) {
        for (int i = 0; i < sample.length; i++) {
            int maxPos = i;
            for (int j = i + 1; j < sample.length; j++) {
                if (sample[j] > sample[maxPos]) maxPos = j;
            }
            if (maxPos != i) swap(sample, maxPos, i);
        }
    }

    /**
     * passed
     */
    private static void insertSort(int[] nums) {
        if (nums == null || nums.length < 2) return;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                int value = nums[i];
                int k = i;
                while (k > 1 && nums[k] < nums[k - 1]) {
                    nums[k] = nums[k - 1];
                    k--;
                }
                nums[k] = value;
            }
        }
    }

    private static void swap(int[] sample, int a, int b) {
        sample[a] = sample[a] ^ sample[b];
        sample[b] = sample[a] ^ sample[b];
        sample[a] = sample[a] ^ sample[b];
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode h1 = l1, h2 = l2, head, cursor = head = new ListNode(0);
        int add = 0;
        while (h1 != null || h2 != null || add > 0) {
            ListNode node = new ListNode(0);
            int v1 = h1 == null ? 0 : h1.val;
            int v2 = h2 == null ? 0 : h2.val;
            h1 = h1 == null ? null : h1.next;
            h2 = h2 == null ? null : h2.next;
            node.val = (add + v1 + v2) % 10;
            add = (add + v1 + v2) / 10;
            cursor.next = node;
            cursor = node;
        }
        return head.next;
    }

    public static ListNode addLists2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        Stack<Integer> one = new Stack<>(), two = new Stack<>();
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                one.push(l1.val);
                l1 = l1.next;
            }
            if (l2 != null) {
                two.push(l2.val);
                l2 = l2.next;
            }
        }
        int add = 0;
        ListNode lastNode = null;
        while (one.size() > 0 || two.size() > 0 || add > 0) {
            Integer v1 = one.size() > 0 ? one.pop() : 0;
            Integer v2 = two.size() > 0 ? two.pop() : 0;
            ListNode node = new ListNode((v1 + v2 + add) % 10);
            add = (v1 + v2 + add) / 10;
            if (lastNode == null) {
                lastNode = node;
            } else {
                node.next = lastNode;
                lastNode = node;
            }
        }
        return lastNode;
    }

    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> auxMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (auxMap.containsKey(target - nums[i])) {
                result[0] = i + 1;
                result[1] = auxMap.get(target - nums[i]);
                return result;
            }
            auxMap.put(nums[i], i + 1);
        }
        return result;
    }

    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        ListNode pointer = head;
        while (pointer.next != null) {
            if (pointer.next.val == val) pointer.next = pointer.next.next;
            else pointer = pointer.next;
        }
        return head.val == val ? head.next : head;
    }

    public static ListNode swapNodes(ListNode head, int v1, int v2) {
        ListNode p = head, q = head;
        while (p != null && p.val != v1) {
            p = p.next;
        }
        while (q != null && q.val != v2) {
            q = q.next;
        }
        if (p == null || q == null) return null;
        p.val = p.val ^ q.val;
        q.val = p.val ^ q.val;
        p.val = p.val ^ q.val;
        return head;
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = head, q = head.next;
        while (p != null && q != null) {
            p.val = p.val ^ q.val;
            q.val = p.val ^ q.val;
            p.val = p.val ^ q.val;
            p = q.next;
            q = p != null ? p.next : null;
        }
        return head;
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        int len1 = 0, len2 = 0;
        ListNode p = headA, q = headB;
        while (p != null) {
            len1++;
            p = p.next;
        }
        while (q != null) {
            len2++;
            q = q.next;
        }
        ListNode noOffsetNode = len1 < len2 ? headA : headB;
        ListNode offsetNode = len1 > len2 ? headA : headB;
        int offset = len1 > len2 ? len1 - len2 : len2 - len1;
        while (offset-- > 0) {
            offsetNode = offsetNode.next;
        }
        while (noOffsetNode != null && offsetNode != null) {
            if (offsetNode == noOffsetNode) {
                return offsetNode;
            }
            offsetNode = offsetNode.next;
            noOffsetNode = noOffsetNode.next;
        }
        return null;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode p = head, q = head;
        for (int i = 0; i < n; i++) {
            p = p.next;
            if (p == null) {
                head = head.next;
                return head;
            }
        }
        while (p.next != null) {
            p = p.next;
            q = q.next;
        }
        if (q.next != null) {
            q.next = q.next.next;
        }
        return head;
    }

    public static ListNode reverseLinkedList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode current = head, aux = head.next;
        while (current.next != null) {
            current.next = aux.next;
            aux.next = head;
            head = aux;
            aux = current.next;
        }
        return head;
    }

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        int step = n - m;
        ListNode parent = new ListNode(0);
        parent.next = head;
        ListNode tmpHead = parent;
        for (int i = 0; i < m - 1; i++) {
            tmpHead = tmpHead.next;
        }
        ListNode startNode = tmpHead.next, current = startNode, aux = current.next;
        while (step > 0) {
            current.next = aux.next;
            aux.next = startNode;
            startNode = aux;
            aux = current.next;
            step--;
        }
        tmpHead.next = startNode;
        return parent.next;
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode auxNode = head;
        while (auxNode.next != null) {
            auxNode = auxNode.next;
        }
        auxNode.next = head;
        for (int i = 0; i < k - 1; i++) {
            auxNode = auxNode.next;
        }
        head = auxNode.next;
        auxNode.next = null;
        return head;
    }

    public static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode parent = new ListNode(0);
        parent.next = head;
        ListNode currentNode = head;
        while (currentNode.next != null) {
            if (currentNode.val > currentNode.next.val) {
                ListNode auxNode = currentNode.next;
                currentNode.next = auxNode.next;
                ListNode tmpHead = parent;
                while (tmpHead.next.val < auxNode.val) {
                    tmpHead = tmpHead.next;
                }
                auxNode.next = tmpHead.next;
                tmpHead.next = auxNode;
            } else {
                currentNode = currentNode.next;
            }
        }
        return parent.next;
    }


    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return true;
        }
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        /* reverse slow later list */
        ListNode tmpHead = slow.next;
        ListNode current = tmpHead, aux;
        while (current.next != null) {
            aux = current.next;
            current.next = aux.next;
            aux.next = tmpHead;
            tmpHead = aux;
        }
        slow.next = tmpHead;
        /* now move slow to head, compare tmpHead and slow sequentially */
        slow = head;
        while (tmpHead != null) {
            if (tmpHead.val != slow.val) {
                return false;
            }
            tmpHead = tmpHead.next;
            slow = slow.next;
        }
        return true;
    }

    public ListNode nthToLast(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode probe = head, second = head;
        for (int i = 0; i < n; i++) {
            probe = probe.next;
            if (probe == null) {
                return null;
            }
        }
        while (probe != null) {
            probe = probe.next;
            second = second.next;
        }
        return second;
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode auxNode = head;
        while (auxNode.next != null) {
            if (auxNode.next.val == auxNode.val) {
                auxNode.next = auxNode.next.next;
            } else {
                auxNode = auxNode.next;
            }
        }
        return head;
    }

    public static ListNode deleteDuplicatesDistinct(ListNode head) {
        if (head == null) return null;
        ListNode parent = new ListNode(0);
        parent.next = head;
        ListNode auxNode = parent;
        while (auxNode.next != null && auxNode.next.next != null) {
            if (auxNode.next.val == auxNode.next.next.val) {
                ListNode tmpNode = auxNode.next;
                while (tmpNode.next != null && tmpNode.next.val == tmpNode.val) {
                    tmpNode.next = tmpNode.next.next;
                }
                auxNode.next = auxNode.next.next;
            } else {
                auxNode = auxNode.next;
            }
        }
        return parent.next;
    }

    public static ListNode mergeKLists(List<ListNode> lists) {
        if (lists.size() == 0) return null;
        return helper(lists, 0, lists.size() - 1);
    }

    public static ListNode helper(List<ListNode> lists, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            return mergeTwoLists(helper(lists, l, m), helper(lists, m + 1, r));
        }
        return lists.get(l);
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val > l2.val) {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        } else {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head, slow = head, meetNode = null;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                meetNode = slow;
                break;
            }
        }
        if (meetNode != null) {
            ListNode auxNode = meetNode.next;
            ListNode helpNode = head;
            while (auxNode != meetNode) {
                auxNode = auxNode.next;
                helpNode = helpNode.next;
            }
            auxNode = head;
            while (helpNode != meetNode) {
                helpNode = helpNode.next;
                auxNode = auxNode.next;
            }
            return auxNode;
        }
        return null;
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        /* rotation */
        ListNode tmpHead = slow.next, current = tmpHead, aux;
        while (current.next != null) {
            aux = current.next;
            current.next = aux.next;
            aux.next = tmpHead;
            tmpHead = aux;
        }
        slow.next = null;
        ListNode pHead = head, retNode = new ListNode(0);
        while (tmpHead != null) {
            aux = tmpHead.next;
            tmpHead.next = pHead.next;
            pHead.next = tmpHead;
            pHead = tmpHead.next;
            tmpHead = aux;
        }
    }

    public static ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode head1 = slow.next;
        slow.next = null;
        ListNode head2 = head;
        head1 = mergeSort(head1);
        head2 = mergeSort(head2);
        return merge(head1, head2);
    }

    public static ListNode merge(ListNode head1, ListNode head2) {
        if (head1 == null) return head2;
        if (head2 == null) return head1;
        ListNode parent = new ListNode(0), lastNode = parent;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                lastNode.next = head1;
                lastNode = head1;
                head1 = head1.next;
            } else {
                lastNode.next = head2;
                lastNode = head2;
                head2 = head2.next;
            }
        }
        while (head1 != null) {
            lastNode.next = head1;
            lastNode = head1;
            head1 = head1.next;
        }
        while (head2 != null) {
            lastNode.next = head2;
            lastNode = head2;
            head2 = head2.next;
        }
        return parent.next;
    }

    public static ListNode partition(ListNode head, int x) {
        if (head == null) return null;
        ListNode lessHead = new ListNode(0), greatHead = new ListNode(0),
                auxNode = head, less = lessHead, great = greatHead;
        while (auxNode != null) {
            ListNode next = auxNode.next;
            if (auxNode.val < x) {
                less.next = auxNode;
                less = auxNode;
                auxNode.next = null;
            } else {
                great.next = auxNode;
                great = auxNode;
                auxNode.next = null;
            }
            auxNode = next;
        }
        less.next = greatHead.next;
        return lessHead.next;
    }

    public static ListNode reverseKGroupFull(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;
        ListNode pNode = new ListNode(0), auxNode = pNode;
        pNode.next = head;
        while (true) {
            int counter = k;
            ListNode startNode = auxNode.next, tmpHead = startNode, current = tmpHead;
            while (current.next != null && counter > 1) {
                ListNode aux = current.next;
                current.next = aux.next;
                aux.next = tmpHead;
                tmpHead = aux;
                auxNode.next = aux;
                counter--;
            }
            auxNode = startNode;
            if (auxNode.next == null) {
                break;
            }
        }
        return pNode.next;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;
        ListNode pNode = new ListNode(0), auxNode = pNode;
        pNode.next = head;
        while (true) {
            int counter = k;
            ListNode tester = auxNode.next;
            while (counter > 0) {
                if (tester == null) {
                    return pNode.next;
                }
                tester = tester.next;
                counter--;
            }
            ListNode startNode = auxNode.next, tmpHead = startNode, current = tmpHead;
            counter = k;
            while (current.next != null && counter > 1) {
                ListNode aux = current.next;
                current.next = aux.next;
                aux.next = tmpHead;
                tmpHead = aux;
                auxNode.next = aux;
                counter--;
            }
            auxNode = startNode;
        }
    }

    public static DoublyListNode bstToDoublyList(TreeNode root) {
        if (root == null) return null;
        DoublyListNode retNode = constructHelper(root);
        while (retNode.prev != null) {
            retNode = retNode.prev;
        }
        return retNode;
    }

    private static DoublyListNode constructHelper(TreeNode root) {
        DoublyListNode node = new DoublyListNode(root.val);
        if (root.left != null) {
            DoublyListNode left = constructHelper(root.left);
            while (left.next != null) left = left.next;
            left.next = node;
            node.prev = left;
        }
        if (root.right != null) {
            DoublyListNode right = constructHelper(root.right);
            while (right.prev != null) right = right.prev;
            right.prev = node;
            node.next = right;
        }
        return node;
    }

    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        return bstHelper(head, null);
    }

    public static TreeNode bstHelper(ListNode left, ListNode right) {
        if (left == right) {
            return null;
        }
        ListNode slow = left, fast = left;
        while (fast.next != right && fast.next.next != right) {
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = slow.next;
        TreeNode node = new TreeNode(slow.val);
        node.left = bstHelper(left, slow);
        node.right = bstHelper(fast, right);
        return node;
    }

    public static int majorityNumber(List<Integer> nums) {
        assert nums != null && nums.size() > 0;
        Integer candidate = null;
        int count = 0;
        for (Integer num : nums) {
            if (count == 0 || Objects.equals(candidate, num)) {
                count++;
                candidate = num;
            } else {
                count--;
                candidate = count == 0 ? null : candidate;
            }
        }
        return candidate == null ? -1 : candidate;
    }

    public static int majorityNumberI(List<Integer> nums) {
        assert nums != null && nums.size() >= 3;
        int countA = 0, countB = 0;
        Integer a = null, b = null;
        for (Integer num : nums) {
            if (countA == 0 || num == a) {
                countA++;
                a = num;
            } else if (countB == 0 || num == b) {
                countB++;
                b = num;
            } else {
                countB--;
                countA--;
            }
        }
        countA = 0;
        countB = 0;
        for (Integer num : nums) {
            if (num == a) {
                countA++;
            }
            if (num == b) {
                countB++;
            }
        }
        return countA > countB ? a : b;
    }

    public static int majorityNumberII(List<Integer> nums, int k) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (Integer num : nums) {
            counter.put(num, counter.containsKey(num) ? counter.get(num) + 1 : 1);
            if (counter.size() > k) {
                List<Integer> keys = new ArrayList<>();
                for (Integer key : counter.keySet()) {
                    counter.put(key, counter.get(key) - 1);
                }
                for (Integer key : counter.keySet()) {
                    if (counter.get(key) == 0) {
                        keys.add(key);
                    }
                }
                for (Integer key : keys) {
                    counter.remove(key);
                }
            }
        }
        for (Integer integer : counter.keySet()) {
            counter.put(integer, 0);
        }
        for (Integer num : nums) {
            if (counter.containsKey(num)) {
                counter.put(num, counter.get(num) + 1);
            }
        }

        Iterator<Integer> iterator = counter.keySet().iterator();
        Integer maxKey = iterator.next(), maxValue = counter.get(maxKey);
        while (iterator.hasNext()) {
            Integer tmpKey = iterator.next();
            if (maxValue < counter.get(tmpKey)) {
                maxKey = tmpKey;
                maxValue = counter.get(tmpKey);
            }
        }
        return maxKey;
    }

    private static int majorityNumberIII(List<Integer> nums, int k) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (Integer num : nums) {
            if (counter.containsKey(num)) {
                counter.put(num, counter.get(num) + 1);
            } else {
                counter.put(num, 1);
                if (counter.size() == k) {
                    Set<Integer> removeKeys = new HashSet<>();
                    for (Integer integer : counter.keySet()) {
                        if (counter.get(integer) <= 1) {
                            removeKeys.add(integer);
                        } else {
                            counter.put(integer, counter.get(integer) - 1);
                        }
                    }
                    for (Integer removeKey : removeKeys) {
                        counter.remove(removeKey);
                    }
                }
            }
        }
        for (Integer integer : counter.keySet()) {
            counter.put(integer, 0);
        }
        for (Integer num : nums) {
            if (counter.containsKey(num)) {
                counter.put(num, counter.get(num) + 1);
            }
        }
        Map.Entry<Integer, Integer> maxEntry = null;
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            if (maxEntry == null || maxEntry.getValue() < entry.getValue()) {
                maxEntry = entry;
            }
        }
        return maxEntry == null ? -1 : maxEntry.getKey();
    }

    public static TreeNode sortedListToBST1(ListNode head) {
        if (head == null) return null;
        return help(head, null);
    }

    private static TreeNode help(ListNode left, ListNode right) {
        if (left == right) return null;
        ListNode fast = left, slow = left;
        TreeNode node = new TreeNode(slow.val);
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = slow.next;
        node.left = help(left, slow);
        node.right = help(fast, right);
        return node;
    }
}
