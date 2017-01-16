package sorting.quick;


public class QuickSort {

    /**
     * Quick sort
     *
     * Unstable, In-place
     * I usually has better performance than Merge sort
     */
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int lo, int hi) {
        if (lo >= hi)
            return;

        int pivotIndex = partition(arr, lo, hi);
        quickSort(arr, lo, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, hi);
    }

    public static int partition(int[] arr, int lo, int hi)
    {

        int left = lo, right = hi - 1; // left and right scan indices
        int pivot = arr[hi];           // pivot

        while (true) {
            // scan from left first: stop at element >= pivot
            while (left <= right && arr[left] < pivot)
                left++;

            // scan from right
            while (left <= right && arr[right] > pivot)
                right--;

            // scan complete
            if (left > right)
                break;

            // switch when left and right are equal to pivot always switch
            swap(arr, left, right);
            left++;
            right--; // it's necessary in case values equal to pivot
        }

        arr[hi] = arr[left];
        arr[left] = pivot; // Put pivot into a[left] because it scans left first
        return left; // return left because left search is the first
    }

    /**
     * Quick sort with Median of 3
     * (sort first, middle, last at the first time to avoid the worst case)
     */
    public static void quickSortMedian3(int[] arr) {
        quickSortMedian3(arr, 0, arr.length - 1);
    }

    private static void quickSortMedian3(int[] arr, int lo, int hi) {
        if (lo >= hi)
            return;

        int pivotIndex = partitionMedianOf3(arr, lo, hi);
        quickSortMedian3(arr, lo, pivotIndex - 1);
        quickSortMedian3(arr, pivotIndex + 1, hi);
    }

    /**
     * compare left, center and right then partition with pivot(median)
     */
    private static int partitionMedianOf3(int[] arr, int lo, int hi) {

        // the number of elements > 3
        if (hi - lo > 2) {
            int center = (lo + hi) / 2;

            if (arr[lo] > arr[center])
                swap(arr, lo, center);

            if (arr[lo] > arr[hi])
                swap(arr, lo, hi);

            if (arr[center] > arr[hi])
                swap(arr, center, hi);

            // swap center with arr[hi - 1] to use a pivot;
            swap(arr, center, hi - 1);

            // lo < center < hi
            return partition(arr, lo + 1, hi - 1);
        } else
            return partition(arr, lo, hi);
    }

    /**
     * Quick3 sort (with 3-way partitioning to partition equal values)
     *
     * Unstable
     * Better performance when many values are duplicate (pivot is range)
     */
    public static void quick3Sort(int[] arr) {
        quick3Partition(arr, 0, arr.length - 1);
    }

    private static void quick3Partition(int[] arr, int lo, int hi) {

        if (lo >= hi)
            return;

        int pivot = arr[lo];
        int pLo = lo;    // less than [lo..pLo-1]
        int pHi = hi;    // greater than [pHi+1..hi]
        int i = lo + 1;  // index to compare

        // [lo..pLo-1]: less
        // [pLo..i-1]:  the range of values that equal to pivot
        // [i..pHi]:    not compared yet
        // [pHi+1..hi]: greater
        while (i <= pHi) {
            if (arr[i] < pivot) {           // less then
                swap(arr, i, pLo);
                pLo++;
                i++; // pLo == pivot, no need to compare
            } else if (arr[i] == pivot) {   // equals to [pLo..pHi] = pivot
                i++; // pLo ~ i-1 == equals to pivot
            } else {                        // greater than
                swap(arr, i , pHi);
                pHi--;
                // Do not increase index to compare changed value with pivot
            }
        }

        quick3Partition(arr, lo, pLo - 1);
        quick3Partition(arr, pHi + 1, hi);
    }

    private static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}