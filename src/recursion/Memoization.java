package recursion;

public class Memoization {

    /**
     * Recursion with memoization
     * Time  Complexity O(n)
     * Space Complexity O(n)
     *
     *                      fib(6)
     *                      /   \
     *                  fib(5) fib(4) <- return memo[4]
     *                  /   \
     *              fib(4) fib(3) <- return memo[3]
     *              /   \
     *          fib(3) fib(2) <- return memo[2]
     *          /   \
     *      fib(2) fib(1)
     *      /   \
     *  fib(1) fib(0)
     */
    public static int fib(int n) {
        int[] memo = new int[n + 1];
        return fib(n, memo);
    }

    private static int fib(int n, int[] memo) {
        if (n <= 0) return 0;
        else if (n == 1) return 1;
        else if (memo[n] == 0) // hit only memo[n] is null
            memo[n] = fib(n - 1, memo) + fib(n - 2, memo);

        return memo[n];
    }
}
