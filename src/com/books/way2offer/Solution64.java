package com.books.way2offer;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by gordon on 1/12/18.
 */
public class Solution64 {

    /*数据流的中位数,
    * maxHeap 1,2
    * minHeap 3,4
    *1,3,5,2,7,4,3
    * 1
    * 1 3
    * 1,3 5
    * 1,2 3,5
    * 1,2,3 5,7
    * 1,2,3 4,5,7
    * 1,2,3,3 4,5,7
    *
    * 1 2 3 2.5 3 3.5 3
    * */

    public static void main(String[] args) {
        MedianContainer box = new MedianContainer();
        System.out.println(box.getMedian());
        box.add(1);
        System.out.println(box.getMedian());
        box.add(3);
        System.out.println(box.getMedian());
        box.add(5);
        System.out.println(box.getMedian());
        box.add(2);
        System.out.println(box.getMedian());
        box.add(7);
        System.out.println(box.getMedian());
        box.add(4);
        System.out.println(box.getMedian());
        box.add(3);
        System.out.println(box.getMedian());
    }

    static class MedianContainer {

        /*大顶队*/
        Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        /*小顶队*/
        Queue<Integer> minHeap = new PriorityQueue<>();

        void add(int value) {
            if (maxHeap.isEmpty()) {
                maxHeap.add(value);
            } else if (maxHeap.size() > minHeap.size()) {
                if (value >= maxHeap.peek()) {
                    minHeap.add(value);
                } else {
                    minHeap.add(maxHeap.poll());
                    maxHeap.add(value);
                }
            } else {
                if (minHeap.peek() < value) {
                    maxHeap.add(minHeap.poll());
                    minHeap.add(value);
                } else {
                    maxHeap.add(value);
                }
            }
        }

        float getMedian() {
            if (maxHeap.isEmpty() && minHeap.isEmpty()) {
                return 0;
            }
            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            }
            return (maxHeap.peek() + minHeap.peek()) / 2.0f;
//            System.out.println(" " + maxHeap.size() + " " + maxHeap.peek() + " " +minHeap.size() + " " + minHeap.peek());
//            return -1;
        }
    }
}
