import java.io.*;
import java.util.*;

public class CountingSort {

    public static void main(String[] args) {
    }

    public static int[] countingSort(int[] arr, int k) {

        // array to count
        int[] countOcc = new int[k];

        // array to store sorted values
        int[] sortedArr = new int[arr.length];

        // count occurence
        for (int i = 0; i < arr.length; i++) {
            countOcc[arr[i]]++;
        }

        // add counted occurence
        for (int i = 1; i < k; i++) {
            countOcc[i] += countOcc[i - 1];
        }

        // create a sorted array
        for (int i = 0; i < arr.length; i++) {
            sortedArr[--countOcc[arr[i]]] = arr[i];
        }

        return sortedArr;
    }



    public static int[] countingSortWithValues(int[] keys, String[] values, int k) {

        int[] count = new int[k]; // count occurence
        int[] sortedKeys = new int[keys.length]; // keys
        int[] valueIndex = new int[keys.length]; // value's index (optional)


        for (int i = 0; i < keys.length; i++) {
            count[keys[i]]++;
        }

        for (int i = 1; i < k; i++) {
            count[i] += count[i - 1];
        }

        // to keep the order n-1, n-2, n-3 ... 1, 0
        for (int i = keys.length - 1; i >= 0; i--) {
            sortedKeys[--count[keys[i]]] = keys[i];
            valueIndex[count[keys[i]]] = i;
        }

        for (int i = 0; i < keys.length; i++) {
            if (valueIndex[i] < keys.length / 2)
                builder.append("- ");
            else
                builder.append(values[valueIndex[i]] + " ");
        }

        return sortedKeys;
    }


}
