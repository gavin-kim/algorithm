package quiz;

import java.util.HashMap;

/**
 * Finds two integer x, y, that x + y == sum
 * (NOTE: x and y must be different indices)
 *
 * O(n)
 */
public class TwoSum {

    public static int[] solve(int[] arr, int sum) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int complement = sum - arr[i]; // look for the complement

            if (map.containsKey(complement))
                return new int[] {i, map.get(complement)}; // found
            else
                map.put(arr[i], i);
        }
        return null;
    }
}
