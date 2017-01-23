package testing;

import com.sun.javaws.util.JavawsConsoleController;
import graph.GST;
import graph.UndirectedGraph;
import structure.tree.BST;

import java.io.File;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Test {

    private static final int ARRAY_SIZE = 1000000;
    private static final int RANGE = 10000;
    private static final int BUCKET_SIZE = 10;
    private static final int RADIX_SIZE = 10;
    private static MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    public static void main(String[] args) {

        File file = new File("resources/data/10000EWD.txt");
        int[] arr = new Random().ints(ARRAY_SIZE, 0, RANGE).toArray();

        arr = countingSort(arr, RANGE);

        for (int v : arr)
            System.out.println(v);
    }


    public static int[] countingSort(int[] arr, int maxVal) {

        int[] countValues = new int[maxVal + 1];
        int len = arr.length;
        int[] sortedArr = new int[len];

        for (int value : arr)
            countValues[value]++;

        for (int i = 1; i < countValues.length; i++)
            countValues[i] += countValues[i - 1];

        for (int i = 0; i < len; i++) {
            sortedArr[--countValues[arr[i]]] = arr[i];
        }

        return sortedArr;
    }

    public static int[] radixSort(int[] arr, int maxVal, int r) {
        int[] sortedArr = null;
        int numOfCountingSort = maxVal / r;
        int range = 1 << r;


        for (int p = 0; p < numOfCountingSort; p++) {
            int[] countArr = new int[range];

            sortedArr = new int[arr.length];

        }
    }


    public static void testBST(int number) {
        BST<String, String> bst = new BST<>();
        bst.put("F", "F");
        bst.put("B", "B");
        bst.put("G", "G");
        bst.put("A", "A");
        bst.put("D", "D");
        bst.put("C", "C");
        bst.put("E", "E");
        bst.put("I", "I");
        bst.put("H", "H");

        System.out.println(bst.size());

        for (String k : bst.postOrder())
            System.out.println(k);
    }




    // N, M, K
    // N: number of shopping centers, M: number of roads, K: number of types of fish

    // Ti(number of types), T(type)

    // Edge: U(Node), V(Node), W(Weight)
}











