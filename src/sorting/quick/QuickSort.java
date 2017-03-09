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

    public static int partition(int[] arr, int left, int right) {

        int pivot = arr[right]; // pivot
        int p = right--;        // right is pivot (right starts from right - 1)

        while (left <= right) {

            // find greater than or equals to pivot
            while (left <= right && arr[left] < pivot)
                left++;

            // find less than or equals to pivot
            while (left <= right && arr[right] > pivot)
                right--;

            if (left <= right) {
                // switch when left and right are equal to pivot always switch
                swap(arr, left, right);
                left++;
                right--; // it's necessary in case values equal to pivot
            }
        }

        // swap pivot with left
        arr[p] = arr[left];
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

        int pivot = arr[lo]; // store pivot to another variable
        int less = lo;       // less than [lo..left-1]
        int great = hi;      // greater than [right+1..hi]
        int i = lo + 1;      // index to compare

        /*
         * Partitioning:
         *
         *   left part    center part    not searched yet    right part
         * +----------------------------------------------------------+
         * |  < pivot1  |  == pivot  |           ?          | > pivot |
         * +----------------------------------------------------------+
         *              ^                        ^          ^
         *              |                        |          |
         *             less                      i        great
         *
         * Invariants:
         *
         *              all in (lo ~~ less - 1)  < pivot
         *              all in [less ~~~ great) == pivot
         *              all in (great + 1 ~ hi)  > pivot
         *
         * Pointer k is the first index of ?-part.
         */

        while (i <= great) {
            if (arr[i] < pivot) {           // less than
                swap(arr, i, less);
                less++;
                i++; // less == pivot, no need to compare
            } else if (arr[i] == pivot) {   // equals to [less..great] = pivot
                i++; // less ~ i-1 == equals to pivot
            } else {                        // greater than
                swap(arr, i , great);
                great--;
                // Do not increase index to compare changed value with pivot
            }

        }

        quick3Partition(arr, lo, less - 1);
        quick3Partition(arr, great + 1, hi);
    }

    private static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}