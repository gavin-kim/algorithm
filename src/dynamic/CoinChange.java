package dynamic;

/**
 * ---------------------------------
 *    n          Ways         F(n)        F(n) = Number of ways
 * ---------------------------------
 *    1           1            1
 * ---------------------------------
 *    2           2            2
 *                1+1
 * ---------------------------------
 *    3           3            3
 *                2+1
 *                1+1+1
 * ---------------------------------
 *    4           4            5
 *                3+1
 *                2+2
 *                2+1+1     -----------> {2+1+1, Not consider the order
 *                1+1+1+1+1               1+2+1,
 * ---------------------------------      1+1+2}
 *
 *
 *            Target Amount ---->
 *            -------------------------
 *       Ways | 0 | 1 | 2 | 3 | 4 | 5 |     A. A number of ways Without the new coin
 *      -------------------------------     B. A number of ways With the new coin
 * Coin | 0~0 |*1 | 0 | 0 | 0 | 0 | 0 |     C. A + B
 *   |  -------------------------------
 *   |  | 0~1 | 1 |*1 | 1 | 1 | 1 | 1 |     e.g. For 5, using {0,1,2} coins
 *   V  -------------------------------          A: Make 5 with out (2)
 *      | 0~2 | 1 | 1 |*2 | 2 | 3 | 3 |             For 5 using {0,1} = 1 ways
 *      -------------------------------          B: Make 5 with (2), (2) must be contained at least 1
 *      | 0~3 | 1 | 1 | 2 |*3 | 4 | 5 |             5 - 2 = 3
 *      -------------------------------             For 3 using {0,1,2} = 2 ways
 *      | 0~4 | 1 | 1 | 2 | 3 |*5 | 6 |          C: A + B = 3
 *      -------------------------------
 *      | 0~5 | 1 | 1 | 2 | 3 | 5 |*7 |
 *      -------------------------------
 *
 * Time  Complexity: O(target * coins)
 * Space Complexity: O(n)
 *
 * We need 0 for the edge condition
 *
 *  T[] (Total, M = infinity) ----->
 * ---------------------------------------------------------
 * | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |10 |11 |12 |13 | amount
 * ---------------------------------------------------------
 * | 1 | 0 | 0 | 1 | 0 | 0 | 2 | 0 | 0 | 2 | 0 | 0 | 3 | 0 | min total of coins
 * ---------------------------------------------------------
 *
 * T[i - coins[i]]: ways with the new coin
 * T[i]: ways without the new coin
 *
 * e.g. coins[3, 6] For 12
 * T[6]:  with the new coin    {6, 3+3}  (+ 6)
 * T[12]: without the new coin {3+3+3+3}
 */
public class CoinChange {
    public static int solve(int target, int[] coins){
        int[] T = new int[target + 1];
        T[0] = 1; // important!!
        for (int coin : coins) {
            for (int i = coin; i < T.length; i++) { // start from coin
                T[i] += T[i - coin];
            }
        }
        return T[target];
    }
}
