package testing;

import sorting.distribution.CountingSort;
import sorting.distribution.BucketSort;
import sorting.distribution.RadixSort;

public class Test {

    private static final int RANGE = 10000;
    private static final int BUCKET_SIZE = 10;
    private static final int RADIX_SIZE = 10;


    public static void main(String[] args) {

        Testable counting = (arr) -> CountingSort.countingSort(arr, RANGE);
        Testable bucket = (arr) -> BucketSort.bucketSort(arr, 10);
        Testable radix = (arr) -> RadixSort.radixSort(arr, getLengthOfBinary(RANGE), 4);




        //System.out.println(TestUtils.simpleTest(bucket, 100000, 1000000, true));
        System.out.println(getLengthOfBinary(7));
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











