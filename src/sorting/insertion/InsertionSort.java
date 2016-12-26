package sorting.insertion;

public class InsertionSort {

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
}