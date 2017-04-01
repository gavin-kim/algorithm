package quiz;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class FindMedianUsingHeap {

    /**
     * Find medians from index 0, 0~1, 0~2, 0~3, ... , 0~N-1
     *
     * O(N logN)
     *
     * O(N)    for iterate array
     * O(logN) for insertion to the heap(heapify)
     *
     *    lowerPQ                        higherPQ
     * [... 4, 6, 9]                  [15, 18, ...]
     *            |                    |
     *       lower.peek()        higher.peek()
     *
     *  values < 15                    values > 9
     */
    public static void solve(int[] arr) {

        PriorityQueue<Integer> lower = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> higher = new PriorityQueue<>();

        for(int i = 0; i < arr.length; i++){

            int v = arr[i];

            if (lower.size() < higher.size()) { // lower
                if (!higher.isEmpty() && v > higher.peek()) {
                    higher.add(v);
                    lower.add(higher.poll()); // balance
                } else
                    lower.add(v);
            } else { // higher
                if (!lower.isEmpty() && v < lower.peek()) {
                    lower.add(v);
                    higher.add(lower.poll()); // balance
                } else
                    higher.add(v);
            }

            System.out.println(getMedian(lower, higher));
        }
    }

    private static double getMedian(Queue<Integer> lower, Queue<Integer> higher) {

        if (lower.size() == higher.size())
            return (lower.peek() + higher.peek()) / 2.0;
        else
            return lower.size() > higher.size() ? lower.peek() : higher.peek();
    }
}
