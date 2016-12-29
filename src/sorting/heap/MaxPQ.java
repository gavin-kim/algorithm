package sorting.heap;

import java.util.Arrays;

// maximum-oriented priority queue (10 9 8 7 ... 0)
public class MaxPQ<K extends Comparable<K>> {

    private static final int DEFAULT_SIZE = 10;

    private Object[] queue;

    private int size = 0;

    public MaxPQ() {
        this(DEFAULT_SIZE);
    }

    public MaxPQ(int size) {
        queue = new Object[size + 1];
    }

    public int size() {
        return size;
    }

    public void insert(K value) {

        // size: x -> queue[0..x], 0 is not used
        if (size >= queue.length)
            grow(size + 1);

        queue[++size] = value;
        swim(size);
    }

    @SuppressWarnings("unchecked")
    public K removeMax() {
        K max = (K)queue[1]; // get max from the root
        swap(1, size);      // swap the root with the last element
        queue[size] = null; // delete the last element
        size--;             // reduce size;
        sink(1);
        return max;
    }


    private void grow(int minCapacity) {
        int oldCapacity = queue.length;
        // Double size if small; else grow by 50%
        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
            (oldCapacity + 2) :
            (oldCapacity >> 1));

        queue = Arrays.copyOf(queue, newCapacity);
    }


    // Bottom-up reheapify
    @SuppressWarnings("unchecked")
    private void swim(int target) {

        // compare [target](child) with [target / 2](parent) until a child is less than its parent
        while (target > 1 && less(queue[target / 2], queue[target])) {
            swap(target, target / 2);
            target /= 2;
        }
    }

    // Top-down reheapify
    private void sink(int target) {
        // remember target = 0 is never used
        while (target * 2 <= size) {
            int child = target * 2;

            // check if child+1 exists then choose a greater one
            if (child < size && less(queue[child], queue[child + 1]))
                child++;
            /*
            * All children must be smaller than parent
            *      2       3                   4
            *     / \     / \   -> wrong,     / \    -> right
            *    3   4   2   4               2   3
            *
            * */
            if (less(child, target))
                break;

            swap(target, child);
            target = child;
        }
    }

    @SuppressWarnings("unchecked")
    private boolean less(Object a, Object b) {
        return ((K)a).compareTo((K)b) < 0;
    }

    private void swap(int a, int b) {
        Object tmp = queue[a];
        queue[a] = queue[b];
        queue[b] = tmp;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 1; i <= size; i++)
            builder.append(queue[i]).append(" ");

        return builder.toString();
    }
}
