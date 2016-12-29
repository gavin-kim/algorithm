package testing;

import sorting.heap.HeapSort;
import sorting.quick.QuickSort;

import java.io.IOException;

public class Test {

    public static final int SIZE = 10000000;
    public static final int RANGE = 10000;

    public static void main(String[] args) throws IOException{

        Testable quickSortTest = QuickSort::quickSort;
        Testable heapSortTest = HeapSort::heapSort;

        //System.out.println(TestUtils.simpleTest(heapSortTest, SIZE, RANGE, false));
        //System.out.println(TestUtils.simpleTest(quickSortTest, SIZE, RANGE, false));

        TestUtils.doublingTest(quickSortTest, RANGE);
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
