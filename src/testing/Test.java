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

import static sorting.heap.HeapSort.swap;


public class Test {

    private static final int ARRAY_SIZE = 1000000;
    private static final int RANGE = 100000000;
    private static final int BUCKET_SIZE = 10;
    private static final int RADIX_SIZE = 10;
    private static MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    public static void main(String[] args) throws Exception{
        Wrapper<Integer> wrapper = new Wrapper<>(1);
        new Test().getHello(1, wrapper);
        System.out.println(wrapper.value);
    }

    static class Wrapper<V> {
        V value;

        Wrapper (V value) {
            this.value = value;
        }
    }

    public String outer = "outer";

    public void getHello(int local, Wrapper<Integer> wrapper) {

        // Main reason: When run-time, anonymous class is initialized and
        // local variables are copied to anonymous class with final

        new Thread() {

            // private final int local = local; --> this happens when runtime

            public void run() {
                // do something...

                // local = 2 // can not modify a final variable
                wrapper.value = 2;         // use a wrapper to modify
                System.out.println(outer); // use outer variables (outer instance exists after getHello is done)
                outer = "outer2";
            }
        }.run();

        // local = 3; // there will be some unexpected data synchronization problems
        wrapper.value = 3;
        outer = "outer3";
    }

    public static double getMean(int[] arr) {
        long sum = 0;
        for (int v : arr)
            sum += v;

        return sum / (double)arr.length;
    }

    public static double getMedian(int[] arr) {

        int mid = arr.length / 2;

        if ((arr.length & 1) == 1)
            return arr[mid];
        else
            return (arr[mid - 1] + arr[mid]) / (double)2;
    }

    public static int getMode(int[] arr) {

        int maxCount = 1;
        int mode = arr[0];

        int count = 1;
        int previous = -1;

        for (int i = 0; i < arr.length; i++) {
            if (previous == arr[i])
                count++;
            else {
                if (count > maxCount) {
                    maxCount = count;
                    mode = previous;
                }

                count = 1;
            }

            previous = arr[i];
        }

        return mode;
    }


    public static void testAVL() {
        Tree24<String, String> tree24 = new Tree24<>();
        tree24.put("S", "S");
        tree24.put("E", "E");
        tree24.put("X", "X");
        tree24.put("A", "A");
        tree24.put("R", "R");
        tree24.put("C", "C");
        tree24.put("H", "H");
        tree24.put("M", "M");
        tree24.put("L", "L");
        tree24.put("P", "P");
        tree24.put("R", "sdf");
        System.out.println(tree24.size());
        System.out.println(tree24.get("C"));

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











