package sorting.merge;

/**
 * Stable
 * runtime: O(n log n)
 * extra space: O(n)
 */

public class MergeSort {


    // Top Down merge sort
    public static void mergeSortTD(int[] arr) {
        int[] aux = new int[arr.length];
        sortTD(arr, aux, 0, arr.length -1);
    }

    private static void sortTD(int[] arr, int[] aux, int lo, int hi) {
        if (lo < hi) {

            int mid = (lo + hi) / 2;
            sortTD(arr, aux, lo, mid);
            sortTD(arr, aux, mid + 1, hi);
            merge(arr, aux, lo, mid, hi);
        }
    }

    // Bottom Up merge sort
    public static void mergeSortBU(int[] arr) {
        sortBU(arr);
    }

    private static void sortBU(int[] arr) {
        int n = arr.length;
        int[] aux = new int[n];

        // size: 1  (1:1) ... (1:1) ...
        // size: 2  (2:2) ... (2:2) ...
        for (int size = 1; size < n; size *= 2) {    // size: subarray size  (1, 2, 4, 8 ....)
            for (int i = 0; i < n - size; i += size * 2) {  // i:    subarray index
                merge(arr, aux, i, i + size - 1 , Math.min(i + size * 2, n) - 1);
            }
        }
    }

    // Abstract in-place merge
    private static void merge(int[] arr, int[] aux, int lo, int mid, int hi) {

        // Merge arr[lo..mid] with arr[mid+1..hi]
        int i = lo;      // lo ~ mid
        int j = mid + 1; // mid + 1 ~ hi

        // Copy arr to aux
        for (int k = lo; k <= hi; k++)
            aux[k] = arr[k];

        // Merge back to arr[lo..hi]
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                arr[k] = aux[j++];
            else if (j > hi)
                arr[k] = aux[i++];
            else if (aux[j] < aux[i])
                arr[k] = aux[j++];
            else
                arr[k] = aux[i++];
        }
    }

}