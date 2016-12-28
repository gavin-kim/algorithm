package sorting.heap;

public class Heap {

    private static final int DEFAULT_SIZE = 11;

    int[] pq;

    int size = 0;

    public Heap() {
        this(DEFAULT_SIZE);
    }

    public Heap(int size) {
        pq = new int[size];
    }

    // Bottom-up reheapify
    private void swim(int target) {

        // compare [target](child) with [target / 2](parent) until a child is less than its parent
        while (target > 1 && pq[target] > pq[target / 2]) {
            swap(target, target / 2);
            target /= 2;
        }
    }

    // Top-down reheapify
    private void sink(int target) {
        // remember target = 0 is never used
        while (target * 2 <= size && pq[target] < pq[target * 2]) {
            int child = target * 2;

            // check if child+1 exists then choose a greater one
            if (child < size && pq[child] < pq[child + 1])
                child++;

            swap(target, child);
            target = child;
        }
    }

    private void swap(int a, int b) {
        int tmp = pq[a];
        pq[a] = pq[b];
        pq[b] = tmp;
    }

}
