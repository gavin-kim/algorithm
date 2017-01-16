package sorting.distribution;

public class RadixSort {

    /**
     * Radix sort
     *
     * Stable
     * runtime: O(n)
     *
     * O((b / r) * (n + 2^r)) : parts * counting
     *
     * Can be used for string (b = 8, 8 bits) and integer keys
     * In order for radix sort to work correctly, the digit sorts must be stable.
     *
     * @param arr Array
     * @param b the number of bits for the max integer in the array (size)
     * @param r the number of bits for counting sort each time (radix)
     * @return sorted array
     */
    public static int[] radixSort(int[] arr, int b, int r) {
        int[] output = null;
        int numOfCountingSort = b / r;
        int range = 1 << r;            // 0 ~ 2^r: a range of integers to count each time

        for (int p = 0; p < numOfCountingSort; p++) {
            int[] countingArr = new int[range]; // 1, 2, 4, 8, ...

            // counting sort
            output = new int[arr.length];

            for (int i = 0; i < arr.length; i++) // count occurrence
                countingArr[(arr[i] >> r * p) & (range - 1)]++; // bit & operation in a specific range

            for (int i = 1; i < range; i++)
                countingArr[i] += countingArr[i - 1];

            for (int i = arr.length - 1; i >= 0; i--)
                output[--countingArr[arr[i] >> r * p & (range - 1)]] = arr[i];

            arr = output; // store the result and use it next time
        }
        return output;
    }
}
