package testing;

import com.sun.javaws.util.JavawsConsoleController;
import graph.GST;
import graph.UndirectedGraph;
import sorting.quick.QuickSort;
import structure.tree.BST;

import java.io.File;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static sorting.heap.HeapSort.swap;
import static testing.TestUtils.printArray;


public class Test {

    private static final int ARRAY_SIZE = 1000000;
    private static final int RANGE = 100000000;
    private static final int BUCKET_SIZE = 10;
    private static final int RADIX_SIZE = 10;
    private static MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    public static void main(String[] args) {

        File file = new File("resources/data/10000EWD.txt");

        testBST();
    }

    public static void testBST() {
        BST<String, String> bst = new BST<>();
        bst.put("S", "S");
        bst.put("E", "E");
        bst.put("X", "X");
        bst.put("A", "A");
        bst.put("R", "R");
        bst.put("C", "C");
        bst.put("H", "H");
        bst.put("M", "M");
        bst.put("L", "L");
        bst.put("P", "P");


        for (String k : bst.keys("F", "T"))
            System.out.println(k);
    }
    public static void quickSort(int[] arr, int left, int right) {
        int index = partition(arr, left, right);
        if (left< index - 1) { // Sort left half
            quickSort(arr, left, index - 1);
        }
        if (index< right) { // Sort right half
            quickSort(arr, index, right);
        }
    }

    public static int partition(int[] arr, int left, int right) {
        int pivot = arr[(left + right) / 2]; // Pick pivot point

        while (left<= right) {
            //Find element on left that should be on right
            while (arr[left] < pivot) left++;

            //Find element on right that should be on left
            while (arr[right] > pivot) right--;

            if (left<= right) {
                swap(arr, left, right); // swaps elements
                left++;
                right--;
            }
        }
        return left;
    }
}











