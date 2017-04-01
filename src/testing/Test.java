package testing;


import com.sun.org.apache.bcel.internal.util.ByteSequence;
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

    private static final int ARRAY_SIZE = 1000000;
    private static final int RANGE = 100000000;
    private static final int BUCKET_SIZE = 10;
    private static final int RADIX_SIZE = 10;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        PriorityQueue<Integer> lower = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> higher = new PriorityQueue<>();

        lower.add(in.nextInt());
        System.out.println(lower.peek());

        for(int i = 1; i < n; i++){

            int v = in.nextInt();

            if (lower.size() < higher.size()) { // lower
                if (v > higher.peek()) {
                    higher.add(v);
                    lower.add(higher.poll());   // balance
                } else
                    lower.add(v);

            } else { // higher
                if (v < lower.peek()) {
                    lower.add(v);
                    higher.add(lower.poll());
                } else
                    higher.add(v);
            }

            System.out.println(getMedian(lower, higher));
        }
    }

    public static double getMedian(Queue<Integer> lower, Queue<Integer> higher) {
        int len = lower.size() + higher.size();

        if ((len & 1) == 1)
            return lower.size() > higher.size() ? lower.peek() : higher.peek();
        else
            return lower.peek() + higher.peek() / 2.0;
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










