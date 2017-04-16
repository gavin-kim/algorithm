package sorting.string;

import sorting.basicsorts.BasicSorts;

/**
 * MSD (Most Significant Digit first) sort
 */
public class MSD {

    private static int R = 256; // radix
    private static final int CUTOFF_SIZE = 15; // minimum size for partition
    private static String[] aux;

    private static int charAt(String s, int index) {
        return index >= s.length() ? 0 : s.charAt(index) + 1;
    }

    public static void sort(String[] arr)  throws Exception{
        int N = arr.length;
        aux = new String[N];
        quickSort(arr, 0, 0, N - 1);
    }

    private static int compare(String s1, String s2, int index) {
        return s1.substring(index).compareTo(s2.substring(index));
    }

    private static void insertionSort(String[] arr, int index, int lo, int hi) {

        for (int i = lo + 1; i <= hi; i++) {
            String s = arr[i];
            int p = i;

            while (p > lo && compare(arr[p - 1], s, index) > 0) {
                arr[p] = arr[p - 1];
                p--;
            }
            arr[p] = s;
        }
    }

    /**
     * Sort string array only compare characters at an index
     *
     * @param arr array
     * @param index of character to compare in a string
     * @param lo low index
     * @param hi high index
     */
    private static void quickSort(String[] arr, int index, int lo, int hi) throws Exception {
        if (hi <= lo + CUTOFF_SIZE) {
            insertionSort(arr, index, lo, hi);
            return;
        }

        int[] count = new int[R + 2];

        // count[0]  : counts index >= length,
        // count[1~R]: counts lo ~ hi
        // count[R+1]: sum of counts
        for (int i = lo; i <= hi; i++)
            count[charAt(arr[i], index)]++;

        for (int r = 0; r <= R ; r++)
            count[r + 1] += count[r];

        for (int i = hi; i >= lo; i--) // aux[0~n]
            aux[--count[charAt(arr[i], index)]] = arr[i];

        for (int i = lo; i <= hi; i++)
            arr[i] = aux[i - lo];      // arr[i]: lo~hi, aux[0~n]

        for (int r = 0; r <= R; r++) // [1~2], [2~3] ... [r ~ r+1]
            quickSort(arr, index + 1, lo + count[r], lo + count[r + 1] - 1);
    }
}
