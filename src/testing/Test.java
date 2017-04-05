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
        
    }

    public static int mergeSort(int[] arr) {
        int[] aux = new int[arr.length];
        return divide(arr, aux, 0, arr.length - 1);
    }

    public static int divide(int[] arr, int[] aux, int lo, int hi) {
        if (lo < hi) {
            int mid = (lo + hi) / 2;
            int a = divide(arr, aux, lo, mid);
            int b = divide(arr, aux, mid + 1,  hi);
            return a + b + merge(arr, aux, lo, mid, hi);
        }
        return 0;
    }
    public static int merge(int[] arr, int[] aux, int lo, int mid, int hi) {

        int count = 0;
        int i = lo;
        int j = mid + 1;
        int k = lo;

        for (int p = lo; p <= hi; p++)
            aux[p] = arr[p];

        while (k <= hi) {
            if (i > mid)
                arr[k++] = aux[j++];
            else if (j > hi)
                arr[k++] = aux[i++];
            else if (aux[i] > aux[j]) {
                count += j - k;
                arr[k++] = aux[j++];
            }
            else
                arr[k++] = aux[i++];
        }
        return count;
    }

}





