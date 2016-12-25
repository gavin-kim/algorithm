public class InsertionSort {

    public static void main(String[] args) {

    }

    public static int insertionSort(int[] arr, int count) {

        for (int i = 1; i < arr.length; i++) {
            int p = arr[i];
            int j = i;

            while(j > 0 && arr[j - 1] > p) {
                arr[j] = arr[j - 1];
                j--;
                count++;
            }
            arr[j] = p;
        }
        return count;
    }
}