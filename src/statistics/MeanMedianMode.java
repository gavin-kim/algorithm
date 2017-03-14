package statistics;

import java.lang.reflect.Method;

public class MeanMedianMode {

    /**
     *  get Mean(Average) value in the array
     */
    public static double getMean(int[] arr) {
        long sum = 0;
        for (int v : arr)
            sum += v;

        return sum / (double)arr.length;
    }

    /**
     *  get Median value in the array. Values in the array must be in order
     */
    public static double getMedian(int[] arr) {

        return getMedian(arr, 0, arr.length);
    }

    /**
     *  get median value from beginIndex to endIndex - -1.
     *  Thus a length of the range is endIndex - beginIndex.
     */
    public static double getMedian(int[] arr, int beginIndex, int endIndex) {

        int mid = (beginIndex + endIndex) / 2;

        if ((endIndex - beginIndex & 1) == 1) // length is odd
            return arr[mid];
        else                                  // length is even
            return (arr[mid - 1] + arr[mid]) / (double)2;

    }

    /**
     *  get Mode (most occurrence) value in the array
     */
    public static int getMode(int[] arr) {

        int maxCount = 1;
        int mode = arr[0];

        int count = 1;
        int previous = -1;

        for (int i = 0; i < arr.length; i++) {
            if (previous == arr[i])
                count++;
            else {
                if (count > maxCount) {
                    maxCount = count;
                    mode = previous;
                }
                count = 1;
            }
            previous = arr[i];
        }
        return mode;
    }

}
