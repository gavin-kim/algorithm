package dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * Find the minimum number of coins for the specific amount
 *
 * O (total * length of coins[])
 *
 * T[i] = min(T[i], T[i - coins[j]])
 *
 * Total: 13
 * Coins: [7,2,3,6]
 * -----------------
 * | 0 | 1 | 2 | 3 |
 * -----------------
 * | 7 | 2 | 3 | 6 |
 * -----------------
 *
 * T[] (Total, M = infinity) ----->
 * ---------------------------------------------------------
 * | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |10 |11 |12 |13 | amount
 * ---------------------------------------------------------
 * | 0 | M | 1 | 1 | 2 | 2 | 1 | 1 | 2 | 2 | 2 | 3 | 2 | 2 | min total of coins
 * ---------------------------------------------------------
 *
 * IndexOf[] (index of the last coin) ----->
 * ---------------------------------------------------------
 * | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |10 |11 |12 |13 | amount
 * ---------------------------------------------------------
 * |-1 |-1 | 1 | 2 | 1 | 2 | 3 | 0 | 3 | 1 | 2 | 1 | 3 | 3 | index of coin in Coins[]
 * ---------------------------------------------------------
 *
 * Total: 13, At least 2 coins are needed. {6, 7}
 *
 * Coins[IndexOf[13]] = 6, Next: 7 = 13 - 6
 * Coins[IndexOf[7]]  = 0, Next: 0 = 7 - 7
 * IndexOf[0] = -1, End
 */
public class CoinChangeMin {

    public static List<Integer> solve(int target, int[] coins) {
        int[] T = new int[target + 1];
        int[] coinIndices = new int[target + 1];

        for (int i = 0; i <= target; i++) { // Initialize
            T[i] = Integer.MAX_VALUE;
            coinIndices[i] = -1;
        }

        T[0] = 0; // important!!

        for (int i = 0; i < coins.length; i++) {

            // Coins[i] ~ target
            for (int j = coins[i]; j <= target; j++) {

                // Total Without current coin and 1(current coin)
                // [j - coin]: combination of [0, 1, 2... j - coin]
                int amount = T[j - coins[i]];
                if (amount != Integer.MAX_VALUE && T[j] > amount + 1) {
                    T[j] = amount + 1;
                    coinIndices[j] = i;
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        int amount = target;
        while (coinIndices[amount] != -1) {
            int coin = coins[coinIndices[amount]];
            result.add(coin);
            amount -= coin;
        }
        return result;
    }
}
