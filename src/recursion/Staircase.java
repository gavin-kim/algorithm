package recursion;

/**
 * Count cases of combination, Different orders are different cases.
 * Each jump is 1, 2 or 3 step(s)
 *
 * f(1): 1     | + 3
 *
 * f(2): 1 1   | + 2  => f(4): 1 3
 *       2     |               1 1 2
 *                             2 2
 * f(3): 1 1 1 | + 1           1 1 1 1
 *       2 1   |               2 1 1
 *       1 2   |               1 2 1
 *       3     |               3 1
 *
 * f(n) = f(n-1) + f(n-2) + f(n-3)
 */
public class Staircase {

    public static int solve(int n) {
        return solve(n, new int[n + 1]);
    }

    // n: number of stairs
    private static int solve(int n, int[] memo) {
        if (n < 1)
            return 0;

        switch (n) {
            case 1: return 1;
            case 2: return 2;
            case 3: return 4;
            default:
                if (memo[n] == 0)
                    memo[n] = solve(n - 3, memo) +
                        solve(n - 2, memo) +
                        solve(n - 1, memo);
        }
        return memo[n];
    }
}
