
import java.util.*;


public class QuickSort {

    public static final int MAX = 1000000;

    public static void main(String[] args) {

        int[] arr = new Random().ints(MAX, 0, 1).toArray();

        long start = System.nanoTime();

        quickSort(arr, 0, arr.length - 1);

        long end = System.nanoTime();
        System.out.println((end - start) + "ns");

        arr = new Random().ints(MAX, 0, 1).toArray();

        start = System.nanoTime();

        quickSort3(arr, 0, arr.length - 1);

        end = System.nanoTime();
        System.out.println((end - start) + "ns");
    }


    // in-place quicksort
    public static void quickSort(int[] arr, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(arr, lo, hi);
        quickSort(arr, lo, j - 1);
        quickSort(arr, j + 1, hi);
    }


    private static int partition(int[] arr, int lo, int hi)
    {
        int left = lo, right = hi - 1; // left and right scan indices
        int pivot = arr[hi];           // pivot
        while (true)
        {
            // scan left first: [lo ~ left-1] must be less than or equals to pivot
            while (left <= right && arr[left] < pivot)
                left++;

            // scan right
            while (left <= right && arr[right] > pivot)
                right--;

            // scan complete
            if (left > right)
                break;

            // switch when left and right are equal to pivot always switch
            int tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            left++; right--; // it's necessary in case values equal to pivot
        }

        arr[hi] = arr[left];
        arr[left] = pivot; // Put pivot into a[left] because it scans left first
        return left;
    }

    // quicksort with 3-way partitioning (to partition equal values)
    public static void quickSort3(int[] arr) {
        quickSort3(arr, 0, arr.length - 1);
    }

    private static void quickSort3(int[] arr, int lo, int hi) {
        if (lo < hi) {
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
                    int tmp = arr[pLo];
                    arr[pLo] = arr[i];
                    arr[i] = tmp;
                    pLo++;
                    i++; // pLo == pivot, no need to compare
                } else if (arr[i] == pivot) {   // equals to [pLo..pHi] = pivot
                    i++; // pLo ~ i-1 == equals to pivot
                } else {                        // greater than
                    int tmp = arr[pHi];
                    arr[pHi] = arr[i];
                    arr[i] = tmp;
                    pHi--;
                    // Do not increase index to compare changed value pivot
                }
            }

            quickSort3(arr, lo, pLo - 1);
            quickSort3(arr, pHi + 1, hi);
        }
    }
}