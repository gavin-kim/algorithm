package dynamic;

public class CutRod {

    public static final int PRICES[] = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

    /**
     * Get max price for the rod in specific length.
     * using Top-Down approach with memoization.
     *
     * Time complexity: O(n^2)
     * Space complexity: O(n)
     *
     * prices: prices for each length.
     * len: a length of the rod
     */
    public static int topDownSolve(int[] prices, int len) {
        int[] memo = new int[len + 1];
        for (int i = 0; i < len + 1; i++)
            memo[i] = Integer.MIN_VALUE;
        memo[0] = 0;
        return topDownSolve(prices, len, memo);
    }

    /**
     * Each Solution need to be saved to avoid recomputing again
     */
    private static int topDownSolve(int[] prices, int len, int[] memo) {

        if (memo[len] < 0) {  // first computing
            for (int i = 1; i <= len; i++)
                memo[len] = Math.max( // memo[i] vs (current price + remainder)
                    memo[i], prices[i] + topDownSolve(prices, len - i, memo));
        }
        return memo[len];
    }

    /**
     * Bottom-Up approach
     *
     * Time complexity: O(n^2)
     * Space complexity: O(n)
     *
     * f(0): 0
     * f(1): max(p[1], f(0))
     * f(2): max(p[1], f(1)), max(p[2], f(0))
     * f(3): max(p[1], f(2)), max(p[2], f(1)), max(p[3], f(0))
     *  ...
     * f(n): max(p[1], f(n-1), max(p[2], f(n-1)) ... max(p[n], f(0))
     */

    public static int bottomUpSolve(int[] prices, int len) {
        int[] memo = new int [len + 1];
        int[] firstPieceOf = new int[len + 1]; // the first sub coin for an index

        memo[0] = 0;
        for (int i = 1; i <= len; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 1; j <= i; i++)
                if (max < prices[j] + memo[i - j]) {
                    max = prices[j] + memo[i - j];
                    firstPieceOf[i] = j;
                }

            memo[i] = max; // store sub-problem
        }
        return memo[len];
    }
}
