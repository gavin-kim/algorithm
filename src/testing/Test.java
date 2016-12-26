package testing;

import sorting.merge.MergeSort;
import sorting.quick.QuickSort;

public class Test {

    public static final int SIZE = 10000000;
    public static final int RANGE = 1000000;

    public static void main(String[] args) {

        Testable mergeTopDown = MergeSort::mergeSortTD;
        Testable quickSort = QuickSort::quickSort;
        Testable quickSortMedian3 = QuickSort::quickSortMedian3;

        System.out.println(TestUtils.simpleTest(mergeTopDown, SIZE, RANGE));
        System.out.println(TestUtils.simpleTest(quickSort, SIZE, RANGE));
        System.out.println(TestUtils.simpleTest(quickSortMedian3, SIZE, RANGE));

    }
}
