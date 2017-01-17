package testing;

import sorting.distribution.counting.CountingSort;
import sorting.distribution.bucket.BucketSort;
import sorting.distribution.radix.RadixSort;

import java.util.Random;

public class Test {

    private static final int ARRAY_SIZE = 1000000;
    private static final int RANGE = 10000;
    private static final int BUCKET_SIZE = 10;
    private static final int RADIX_SIZE = 10;


    public static void main(String[] args) {

        int[] array = new Random().ints(ARRAY_SIZE, 0, RANGE).toArray();

        Testable counting = (arr) -> CountingSort.countingSort(array, RANGE);
        Testable bucket = (arr) -> BucketSort.bucketSort(arr, 10);
        Testable radix = (arr) -> RadixSort.radixSort(arr, getLengthOfBinary(RANGE), 4);


        System.out.println(TestUtils.simpleTest(counting, 100000, 1000000, false));

        for (int v : array)
            System.out.println(v);
    }

    public static int getLengthOfBinary(int v) {
        int i = 0;

        while (v > 0) {
            v >>>= 1;
            i++;
        }

        return i;
    }
}











