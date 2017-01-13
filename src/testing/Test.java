package testing;

import sorting.radix.RadixSort;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        int[] arr = new Random().ints(100000, 0, 10000).toArray();

        int[] sorted = RadixSort.radixSort(arr, 32, 4);

        for (int i : sorted)
            System.out.println(i);
    }
}











