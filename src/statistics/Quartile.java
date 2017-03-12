package statistics;

import static statistics.MeanMedianMode.getMean;
import static statistics.MeanMedianMode.getMedian;

/**
 * Quartiles: The 3 points that split the data set into 4 equal groups
 * IQR(Inter Quartile Range): The maximum difference for the middle 50%
 *
 *         62.5       85.5        92
 *          Q1         Q2         Q3
 * 18 45 55 | 70 76 83 | 88 90 92 | 92 95 98
 *          |<---     IQR     --->|
 *           IQR = Q3 - Q1 =  29.5
 *
 */

public class Quartile {
    /**
     * get 3 Quartiles from the sorted array
     */
    public static double[] getQuartiles(int[] arr) {
        double[] quartiles = new double[3];

        quartiles[1] = getMedian(arr); // Q2

        int mid = arr.length / 2;

        quartiles[0] = getMedian(arr, 0, mid); // Q1

        if ((arr.length & 1) == 1) // odd
            quartiles[2] = getMedian(arr, mid + 1, arr.length); // Q3
        else // even
            quartiles[2] = getMedian(arr, mid, arr.length);      // Q3

        return quartiles;
    }

    /**
     * Inter Quartile Range = Q3 - Q1
     */
    public static double getIQR(int[] arr) {
        double[] quartiles = getQuartiles(arr);
        return quartiles[2] - quartiles[0];
    }

    /**
     * u = mean
     * M = (x0 - u) ^ 2 + (x1 - u) ^ 2 + (x2 - u) ^ 2 + ... + (x(n-1) - u) ^ 2
     * Standard Deviation = root(M / n)
     */
    public static double getStandardDeviation(int[] arr) {
        double mean = getMean(arr);
        double sum = 0;

        for (double v : arr)
            sum += Math.pow(v - mean, 2);

        return Math.sqrt(sum / arr.length);
    }
}
