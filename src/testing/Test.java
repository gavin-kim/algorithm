package testing;


import com.sun.org.apache.bcel.internal.util.ByteSequence;
import structure.hashtable.SeparateChainingHashTable;
import structure.tree.bst.BST;
import structure.tree.redblack.RedBlackTree;
import structure.tree.tree24.Tree24;


import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {

    private static final int ARRAY_SIZE = 100;
    private static final int RANGE = 1000;
    private static final int BUCKET_SIZE = 10;
    private static final int RADIX_SIZE = 10;

    public static void main(String[] args) {
        SeparateChainingHashTable<Integer, Integer> ht = new SeparateChainingHashTable<>();
        int[] arr = new Random().ints(ARRAY_SIZE, 0, RANGE).toArray();

        for (int v : arr)
            ht.put(v, v);

        int sizeAfterPut = ht.size();
        int count = 0;

        for (int v : arr) {
            Integer i = ht.delete(v);
            if (i != null) {
                count++;
            }
        }

        System.out.println("unique count: " + IntStream.of(arr).distinct().count());
        System.out.println("delete hits: " + count);
        System.out.println("size after put: " + sizeAfterPut);
        System.out.println("size after delete: " + ht.size());

        for (int v : arr)
            ht.put(v, v);


        for (int v : arr) {
            Integer i = ht.delete(v);
            if (i != null) {
                count++;
            }
        }
        System.out.println("size after delete: " + ht.size());
    }



    public static int returnItself(int n) {
        System.out.println(n);
        return n;
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

    public static void testRedBlackTree() {
        RedBlackTree<Integer, Integer> redBlackTree = new RedBlackTree<>();
        int[] arr = new Random().ints(500000, 0, 100000).distinct().toArray();

        IntStream.of(arr).forEach(v -> redBlackTree.put(v, v));


        System.out.println(redBlackTree.size());
        redBlackTree.validateTree();
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
    static class Inner {
        private class P {
            private void method() {
                System.out.println("p");
            }
        }
    }

    public static boolean isRansom(Map<String, Integer> magazine, Map<String, Integer> note) {
        return note.entrySet()
            .stream()
            .allMatch(entry -> magazine.containsKey(entry.getKey()) &&
                magazine.get(entry.getKey()) >= entry.getValue());
    }


}









