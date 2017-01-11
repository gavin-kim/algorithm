package sorting.applications;

import sorting.quick.QuickSort;

public class Applications {


    // find target: partitioning-based selection is a linear-time algorithm on average.
    // partition: N + N/2 + N/4 + N/8 + N/16 ...  => 2N
    public static int find(int[] arr, int target) {

        // need to shuffle to avoid the worst case
        int lo = 0;
        int hi = arr.length - 1;

        while (hi > lo) {

            // get a pivot index and select one part that includes the target
            int p = QuickSort.partition(arr, lo, hi);

            // lo ~ p ~ hi
            if (p == target)     // find the target
                return arr[p];
            else if (p > target) // select p ~ hi
                lo = p + 1;
            else if (p < target) // select lo ~ p
                hi = p - 1;
        }
        return arr[target];
    }

    // find a target by an index using counting approach
    public static int getMedian(int[] counts, int p) {

        int center = p / 2;
        int i = 0;
        int sum = counts[0];
        int range = 0;

        // sum <= center < next sum
        while (i < counts.length - 1 && sum <= center) {
            range = center - sum;
            sum += counts[++i];
        }

        // in case the number of elements is even and median points different
        if (p % 2 == 0 && range < 1) {

            int median = i;
            while (counts[--i] == 0);
            return median + i;
        }
        else
            return i * 2;
    }
}
