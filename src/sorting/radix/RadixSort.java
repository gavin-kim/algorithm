package sorting.radix;

public class RadixSort {

    /**
     * In order for radix sort to work correctly, the digit sorts must be stable.
     *
     * @param arr Array
     * @param w the total of bits for integers in the array
     * @param d the number of bits for counting sort each time
     * @return sorted array
     */
    public static int[] radixSort(int[] arr, int w, int d) {
        int[] output = null;
        int numOfCountingSort = w / d;
        int range = 1 << d;            // 0 ~ 2^d: a range of integers to count each time

        for (int p = 0; p < numOfCountingSort; p++) {
            int[] countingArr = new int[range]; // 1, 2, 4, 8, ...

            // counting sort

            output = new int[arr.length];

            for (int i = 0; i < arr.length; i++) // count occurrence
                countingArr[(arr[i] >> d * p) & (range - 1)]++; // bit & operation in a specific range

            for (int i = 1; i < range; i++)
                countingArr[i] += countingArr[i - 1];

            for (int i = arr.length - 1; i >= 0; i--)
                output[--countingArr[arr[i] >> d * p & (range - 1)]] = arr[i];

            arr = output; // store the result and use it next time
        }
        return output;
    }
}
