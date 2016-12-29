package sorting.heap;

import java.util.Arrays;

// minimum-oriented priority queue with key-value (0 1 2 3 ... 10)
public class IndexMinPQ<K extends Comparable<K>> {

    private static final int DEFAULT_SIZE = 10;

    private int size = 0;          // number of elements on queue
    private int[] queue;           // store value indexes in order of priority (smaller key has high priority)
    private int[] indexes;         // store queue indexes: inverse A[B[i]] = B[A[i]] = i ( to refer to each other)
    private Object[] values;       // values with priorities

    public IndexMinPQ() {
        this(DEFAULT_SIZE);
    }

    @SuppressWarnings("unchecked")
    public IndexMinPQ(int size) {

        queue = new int[size + 1];
        indexes = new int[size + 1];
        values = new Object[size + 1];

        for (int i = 0; i <= size; i++)
            indexes[i] = -1;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(int i) {
        return indexes[i] != -1;
    }

    public void insert(int i, K value) {

        if (size >= queue.length)
            grow(size + 1);

        size++;
        queue[size] = i;      // key's index:   queue[indexes[i]] = i
        indexes[i] = size;    // queue's index: indexes[queue[i]] = i
        values[i] = value;
        swim(size);
    }

    public int minIndex() {
        return queue[1];
    }

    @SuppressWarnings("unchecked")
    public K minValue() {
        return (K)values[queue[1]];
    }

    public int delMin() {
        int indexOfMin = queue[1];
        swap(1, size);             // swap the root with the last element
        sink(1);                   // sink reheapify
        queue[size] = -1;          // remove value's index
        indexes[indexOfMin] = -1;  // reset index
        values[indexOfMin] = null; // reset value
        size--;
        return indexOfMin;
    }

    @SuppressWarnings("unchecked")
    public K elementAt(int index) {
        return (K)values[index];
    }

    public void changeAt(int index, K value) {
        values[index] = value;
        swim(indexes[index]);
        sink(indexes[index]);
    }

    public void delete(int index) {
        int i = indexes[index];
        swap(i, size); // swap a target with the last element
        swim(i);
        sink(i);

        queue[size--] = -1;
        indexes[index] = -1;
        values[index] = null;

    }


    private void grow(int minCapacity) {
        int oldCapacity = queue.length;
        // Double size if small; else grow by 50%
        int newCapacity = oldCapacity +
            ((oldCapacity < 64) ? (oldCapacity + 2) : (oldCapacity >> 1));

        queue = Arrays.copyOf(queue, newCapacity);
        indexes = Arrays.copyOf(indexes, newCapacity);
        values = Arrays.copyOf(values, newCapacity);
    }


    // Bottom-up reheapify
    private void swim(int target) {

        // compare [target](child) with [target / 2](parent) until a child is greater than its parent
        while (target > 1 && greater(queue[target / 2], queue[target])) {
            swap(target, target / 2);
            target /= 2;
        }
    }

    // Top-down reheapify
    private void sink(int target) {
        // remember target = 0 is never used
        while (target * 2 <= size) {
            int child = target * 2;

            // check if child+1 exists then choose a smaller one
            if (child < size && greater(queue[child], queue[child + 1]))
                child++;
            /*
            * All children must be smaller than parent
            *      2       3                   4
            *     / \     / \   -> wrong,     / \    -> right
            *    3   4   2   4               2   3
            *
            * */
            if (!greater(target, child))
                break;

            swap(target, child);
            target = child;
        }
    }

    @SuppressWarnings("unchecked")
    private boolean greater(Object a, Object b) {
        return ((K)a).compareTo((K)b) > 0;
    }

    private void swap(int a, int b) {
        int tmp = queue[a];
        queue[a] = queue[b];
        queue[b] = tmp;

        indexes[queue[a]] = a;
        indexes[queue[b]] = b;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 1; i <= size; i++)
            builder.append(values[queue[i]]).append(" ");

        return builder.toString();
    }
}
