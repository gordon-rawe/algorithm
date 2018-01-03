package com.lintcode.vintage;

/**
 * Created by books on 10/22/17.
 */
class RandomListNode {
    int label;
    RandomListNode next, random;

    RandomListNode(int x) {
        this.label = x;
    }

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        RandomListNode auxNode = head;
        while (auxNode != null) {
            RandomListNode newNode = new RandomListNode(auxNode.label);
            newNode.next = auxNode.next;
            auxNode.next = newNode;
            auxNode = newNode.next;
        }
        auxNode = head;
        while (auxNode != null) {
            if(auxNode.next.random != null) {
                auxNode.next.random = auxNode.random.next;
            }
            auxNode = auxNode.next.next;
        }
        auxNode = head;
        RandomListNode retHead = head.next;
        while (auxNode != null) {
            RandomListNode newNode = auxNode.next;
            auxNode.next = newNode.next;
            if(newNode.next!=null) {
                newNode.next = newNode.next.next;
            }
            auxNode = auxNode.next;
        }
        return retHead;
    }
}
