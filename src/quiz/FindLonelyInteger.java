package quiz;

/**
 * Find a lonely Integer,
 *
 * There is only one unique number, Others are must have the same number.
 *
 * XOR operation for each number at the last the unique number will be remained.
 *
 *
 */

public class FindLonelyInteger {
    public static int solve(int[] arr) {
        int result = 0;
        for (int value : arr)
            result ^= value;
        return result;
    }
}
