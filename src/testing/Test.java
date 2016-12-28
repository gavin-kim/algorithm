package testing;

import sorting.merge.MergeSort;
import sorting.quick.QuickSort;


import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class Test {

    public static final int SIZE = 100;
    public static final int RANGE = 100;

    public static void main(String[] args) throws IOException{


        Scanner input = new Scanner(new File("src/testing/data.txt"));

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

        System.out.println(notifications);

/*        Testable mergeTopDown = MergeSort::mergeSortTD;
        Testable quickSort = QuickSort::quickSort;
        Testable quickSortMedian3 = QuickSort::quickSortMedian3;

        System.out.println(TestUtils.simpleTest(mergeTopDown, SIZE, RANGE , true));
        System.out.println(TestUtils.simpleTest(quickSort, SIZE, RANGE, true));
        System.out.println(TestUtils.simpleTest(quickSortMedian3, SIZE, RANGE, true));*/

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
