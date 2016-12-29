package testing;

import sorting.heap.IndexMinPQ;

import java.io.IOException;

public class Test {

    public static final int SIZE = 100;
    public static final int RANGE = 100;

    public static void main(String[] args) throws IOException{


/*        Scanner input = new Scanner(new File("src/testing/data.txt"));

        int n = input.nextInt();
        int p = input.nextInt();

        int notifications = 0;

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();

            if (i >= p) {

                // get sorted array [i-p..i-1] size p
                int[] sortedArr = countingSort(arr, i - p, i, 200);
                if (arr[i] >= getMedian(sortedArr))
                    notifications++;
            }
        }

        System.out.println(notifications);*/

        IndexMinPQ<Character> heap = new IndexMinPQ<>();

        heap.insert(3, 'P');
        heap.insert(2, 'Q');
        heap.insert(1, 'E');
        //System.out.println("Removed: " + heap.removeMax());
        System.out.println(heap);
        heap.insert(5, 'X');
        heap.insert(4, 'A');
        heap.insert(6, 'M');
        //System.out.println("Removed: " + heap.removeMax());
        System.out.println(heap);
        heap.insert(9, 'P');
        heap.insert(7, 'L');
        heap.insert(8, 'E');
        //System.out.println("Removed: " + heap.removeMax());

        System.out.println(heap);

    }

    public static int[] countingSort(int[] arr, int lo, int hi, int range) {

        int[] counts = new int[range];
        int[] newArr = new int[hi - lo];

        for (int i = lo; i < hi; i++)
            counts[arr[i]]++;

        for (int i = 0; i < counts.length - 1; i++)
            counts[i + 1] += counts[i];

        for (int i = hi - 1; i >= lo; i--)
            newArr[--counts[arr[i]]] = arr[i];

        return newArr;
    }

    public static int insert(int[] arr, int i, int v) {

        while (i > 0 && arr[i - 1] > v) {
            arr[i] = arr[i - 1];
            i--;
        }

        while (i < arr.length - 1 && arr[i + 1] < v) {
            arr[i] = arr[i + 1];
            i++;
        }

        arr[i] = v;
        return i;
    }

    public static int getMedian(int[] arr) {

        int center = arr.length / 2;

        if (arr.length % 2 == 0) // even
            return (arr[center - 1] + arr[center]);
        else
            return arr[center] * 2;
    }
}
