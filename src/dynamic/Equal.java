package dynamic;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Find minimum operations to distribute chocolates to n: guys equally
 *
 * ways of distribution {1, 2, 5} every time
 *
 * 1
 * 4
 * 2 2 3 7
 *
 * 2  2 (3) 8  +1 except 3rd
 * 3  3  3 (8) +5 except 4th
 * 8  8  8  8
 *
 */

public class Equal {

    public static int[] createMemo(int n, int[] chocolates) {
        int[] memo = new int[n + 1];
        for (int i = 0; i < memo.length; i++)
            memo[i] = Integer.MAX_VALUE;
        memo[0] = 0;

        for (int chocolate : chocolates) {
            for (int i = chocolate; i <= n; i++) {
                int amount = memo[i - chocolate];
                if (amount != Integer.MAX_VALUE && amount + 1 < memo[i])
                    memo[i] = amount + 1;
            }
        }
        return memo;
    }

    public static int countOperations(int[] chocolates, int[] memo) {
        Arrays.sort(chocolates);
        int minCount = Integer.MAX_VALUE;
        int min = chocolates[0];

        for (int opt : new int[]{0, 1, 2}) {
            int count = 0;

            for (int i = 0; i < chocolates.length; i++)
                count += memo[chocolates[i] - min + opt];

            if (count < minCount)
                minCount = count;
        }
        return minCount;
    }
}
