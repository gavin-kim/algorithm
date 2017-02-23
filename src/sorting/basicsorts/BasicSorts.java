package sorting.basicsorts;

public class BasicSorts {

    /**
     * Stable
     * O(n*n): compares
     * O(n): swaps
     */
    public static void insertionSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int p = arr[i];
            int j = i;

            while(j > 0 && arr[j - 1] > p) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = p;
        }

    }

    /**
     * Unstable
     * O(n*n): compares and swaps
     */
    public static void bubbleSort(int[] arr) {

        // i = depth, j = length
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++)
                if (arr[j] > arr[j + 1])
                    swap(arr, j, j + 1);
        }
    }

    /**
     *  Unstable
     *  O(n*n): compares
     *  O(n)  : swaps
     */
    public static void selectionSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {

            int minIndex = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j])
                    minIndex = j;
            }
            swap(arr, i, minIndex);
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
