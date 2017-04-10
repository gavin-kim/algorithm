package quiz;
/**
 *  Counts swap (insertion) using Merge-Sort
 *
 *  arr [. . . .   . . .]     aux [1 3 5 6 | 2 4 7]
 *       k                         l         r
 *
 *  arr [1 . . .   . . .]     aux [. 3 5 6 | 2 4 7] inversion []
 *         k                         l       r
 *
 *  arr [1 2 . .   . . .]     aux [. 3 5 6 | . 4 7] inversion [3 5 6] len = r - k
 *           k                       l         r
 *
 *  arr [1 2 3 .   . . .]     aux [. . 5 6 | . 4 7] inversion []
 *             k                       l       r
 *
 *  arr [1 2 3 4   . . .]     aux [. . 5 6 | . . 7] inversion [5 6] len = r - k
 *                 k                   l         r
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
        int left = lo;
        int right = mid + 1;

        for (int p = lo; p <= hi; p++)
            aux[p] = arr[p];

        for (int i = lo; i <= hi; i++) {
            if (left > mid)
                arr[i] = aux[right++];
            else if (right > hi)
                arr[i] = aux[left++];
            else if (aux[left] <= aux[right])
                arr[i] = aux[left++];
            else {
                count += right - i; // count steps between i and right
                arr[i] = aux[right++];
            }
        }
        return count;
    }
}
