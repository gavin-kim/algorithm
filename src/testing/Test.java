package testing;


import com.sun.org.apache.bcel.internal.util.ByteSequence;
import structure.tree.bst.BST;
import structure.tree.tree24.Tree24;


import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {

    private static final int ARRAY_SIZE = 1000000;
    private static final int RANGE = 100000000;
    private static final int BUCKET_SIZE = 10;
    private static final int RADIX_SIZE = 10;

    public static void main(String[] args) throws Exception{
        //System.out.println(BigInteger.valueOf(31).isProbablePrime(100));
        MessageDigest md5 = java.security.MessageDigest.getInstance("SHA-256");
        md5.update("HelloWorld".getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : md5.digest())
            sb.append(String.format("%02x", b));
        boolean a = true;

        System.out.println(sb);
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


}










