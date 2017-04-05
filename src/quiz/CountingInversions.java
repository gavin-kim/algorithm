package quiz;
/**
 *  Counts swap (insertion) using Merge-Sort
 *
 *  arr [. . . .   . . .]     aux [1 3 5 6 | 2 4 7]
 *       k                         i         j
 *
 *  arr [1 . . .   . . .]     aux [. 3 5 6 | 2 4 7] inversion []
 *         k                         i       j
 *
 *  arr [1 2 . .   . . .]     aux [. 3 5 6 | . 4 7] inversion [3 5 6] len = j - k
 *           k                       i         j
 *
 *  arr [1 2 3 .   . . .]     aux [. . 5 6 | . 4 7] inversion []
 *             k                       i       j
 *
 *  arr [1 2 3 4   . . .]     aux [. . 5 6 | . . 7] inversion [5 6] len = j - k
 *                 k                   i         j
 */
public class CountingInversions {

    public static long solve(int[] arr) {
        int[] aux = new int[arr.length];
        return divide(arr, aux, 0, arr.length - 1);
    }

    private static long divide(int[] arr, int[] aux, int lo, int hi) {
        if (lo < hi) {
            int mid = (lo + hi) / 2;
            long count = divide(arr, aux, lo, mid);
            count += divide(arr, aux, mid + 1,  hi);
            count += merge(arr, aux, lo, mid, hi);
            return count;
        }
        return 0;
    }
    private static long merge(int[] arr, int[] aux, int lo, int mid, int hi) {

        long count = 0;
        int i = lo;
        int j = mid + 1;

        for (int p = lo; p <= hi; p++)
            aux[p] = arr[p];

        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                arr[k] = aux[j++];
            else if (j > hi)
                arr[k] = aux[i++];
            else if (aux[i] <= aux[j])
                arr[k] = aux[i++];
            else {
                count += j - k;
                arr[k] = aux[j++];
            }
        }
        return count;
    }
}
