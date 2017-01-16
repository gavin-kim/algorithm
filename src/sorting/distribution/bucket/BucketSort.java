package sorting.distribution.bucket;

import sorting.basicsorts.BasicSorts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BucketSort {

    private static final int DEFAULT_BUCKET_SIZE = 10;

    /**
     * Bucket sort
     *
     * Worst:   O(n^2)
     * Best:    O(n + k)
     * Average: O(n + k)
     *
     * k: number of buckets (stable sort: insertion or merge)
     *
     * min                       max
     *  {[   ][   ]=> bucket size } => range
     *
     * range: max - min
     * range / bucket size: the number of buckets (last index of bucket[])
     *
     *
     * @param arr An array to be sorted
     * @param bucketSize Indicates a size of the bucket.
     */
    public static void bucketSort(int[] arr, final int bucketSize) {

        int min = arr[0];
        int max = arr[0];

        // Determine minimum and maximum values
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min)
                min = arr[i];
            else if (arr[i] > max)
                max = arr[i];
        }

        // Initialize buckets (e.g. (100 - 50) / 10 + 1 = 6)
        int numOfBuckets = (max - min) / bucketSize + 1;
        List<LinkedList<Integer>> buckets = new ArrayList<>(numOfBuckets); // to avoid expending a size of the array
        for (int i = 0; i < numOfBuckets; i++)
            buckets.add(new LinkedList<>());

        // Distribute input array values into buckets (e.g. value - min / size = index)
        for (int i = 0; i < arr.length; i++)
            buckets.get((arr[i] - min) / bucketSize).add(arr[i]);

        // Insertion sort each bucket and place back into input array
        int currentIndex = 0;
        for (int i = 0; i < numOfBuckets; i++) {
            int[] bucketArray = buckets.get(i).stream().mapToInt(Integer::new).toArray();
            BasicSorts.insertionSort(bucketArray); // insertion sort

            for (int j = 0; j < bucketArray.length; j++)
                arr[currentIndex++] = bucketArray[j];
        }
    }
}
