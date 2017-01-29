package testing;

import structure.tree.avl.AVLTree;
import structure.tree.bst.BST;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

import static sorting.heap.HeapSort.swap;


public class Test {

    private static final int ARRAY_SIZE = 1000000;
    private static final int RANGE = 100000000;
    private static final int BUCKET_SIZE = 10;
    private static final int RADIX_SIZE = 10;
    private static MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    public static void main(String[] args) {
        //testBST();
        System.out.println("---");
        testAVL();

    }

    public static void testAVL() {
        AVLTree<String, String> avlTree = new AVLTree<>();
        avlTree.put("S", "S");
        avlTree.put("E", "E");
        avlTree.put("X", "X");
        avlTree.put("A", "A");
        avlTree.put("R", "R");
        avlTree.put("C", "C");
        avlTree.put("H", "H");
        avlTree.put("M", "M");
        avlTree.put("L", "L");
        avlTree.put("P", "P");

        System.out.println(avlTree.delete("C"));
        System.out.println(avlTree.delete("H"));
        System.out.println(avlTree.delete("P"));
        System.out.println(avlTree.delete("S"));
        System.out.println(avlTree.delete("E"));
        System.out.println(avlTree.delete("A"));
        System.out.println(avlTree.delete("X"));
        System.out.println(avlTree.delete("M"));
        System.out.println(avlTree.delete("L"));
        System.out.println(avlTree.delete("P"));
        System.out.println(avlTree.delete("M"));
        avlTree.put("sdf", "sdf");
        System.out.println(avlTree.min());
        System.out.println(avlTree.max());
        System.out.println(avlTree.size());
        System.out.println(avlTree.getHeight());

        for (String k : avlTree.keys())
            System.out.println(k);
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











