package testing;

import structure.tree.avl.AVLTree;
import structure.tree.bst.BST;
import structure.tree.tree24.Tree24;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.math.BigInteger;
import java.sql.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static sorting.heap.HeapSort.swap;


public class Test {

    private static final int ARRAY_SIZE = 1000000;
    private static final int RANGE = 100000000;
    private static final int BUCKET_SIZE = 10;
    private static final int RADIX_SIZE = 10;
    private static MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    public static void main(String[] args) throws Exception{
        System.out.println(10 - 2 & 1);
    }
    public static void test24Tree() {
        Tree24<Integer, Integer> tree24 = new Tree24<>();
        int[] arr = new Random().ints(500, 0, 1000).distinct().toArray();

        Arrays.sort(arr);
        IntStream.of(arr).forEach(v -> tree24.put(v, v));
        tree24.printStructure();

        IntStream.of(arr).forEach(key -> System.out.println("key: " + key + ", " + tree24.delete(key)));

        System.out.println(tree24.size());
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


}











