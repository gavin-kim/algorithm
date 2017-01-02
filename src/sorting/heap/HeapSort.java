package sorting.heap;


import java.util.Comparator;

public class HeapSort {

    public static void heapSort(int[] arr) {
        int size = arr.length - 1;

        // construct the heap: (n / 2 * log n)
        for (int i = size / 2; i >= 1; i--) {
            sink(arr, i, size);
        }

        // sort (n * log n)
        while (size > 1) {
            swap(arr, 1, size--); // put the max at the last
            sink(arr, 1, size);   // reheapify
        }
    }

    public static void sink(int[] arr, int i, int size) {

        while (i * 2 <= size) {
            int child = i * 2;

            if (child < size && arr[child] < arr[child + 1])
                child++;

            // maximum-oriented
            if (arr[i] >= arr[child])
                break;

            swap(arr, i, child);
            i = child;
        }
    }

    public static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
