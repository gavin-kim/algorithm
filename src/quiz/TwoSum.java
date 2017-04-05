package quiz;

import java.util.HashMap;

/**
 * Finds two integer x, y (must be different indices), that x + y == sum
 *
 * O(n)
 */
public class TwoSum {

    public static int[] solve(int[] arr, int sum) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int complement = sum - arr[i]; // look for the complement

            if (map.containsKey(complement))
                return new int[] {i + 1, map.get(complement)}; // found
            else
                map.put(arr[i], i + 1);
        }
        return null;
    }
}
