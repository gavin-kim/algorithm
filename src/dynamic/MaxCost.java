package dynamic;

/**
 * Find the maximum cost S
 *
 * S = |A(i)   - A(i-1)| +
 *     |A(i-1) - A(i-2)| +
 *           ......      +
 *     |A(2)   -   A(1)| +
 *
 * Ai = x (between 1 and Bi)
 *
 * Inputs:
 * T = number of test cases
 * N = number of integers in Array B
 * B1 B2 B3 ... Bn
 *
 * A1 = 1 <= x <= B1, if B1 = 100, A1 = 1 ~ 100
 *
 * L(i) = max cost using B[1 ~ i], ending with 1 at ith position. (... 1)
 * H(i) = max cost using B[1 ~ i], ending with Bi at ith position.(... B[i])
 *
 *  ... |   [i-1]   |   [i]
 * ---------------------------
 *      |     1 (L) |    1(L)    L -> L     // next Low
 *      | B[i-1](H) |    1(L)    H -> L     // next Low
 *      |     1 (L) | B[i](H)    L -> H     // next High
 *      | B[i-1](H) | B[i](H)    H -> H     // next High
 *
 */
public class MaxCost {
    public static int solve(int[] B) {
        int L = 0;
        int H = 0;

        for (int i = 1; i < B.length; i++) {
            int HtoL = Math.abs(B[i - 1] - 1);    // H -> L
            int LtoH = Math.abs(1 - B[i]);        // L -> H
            int HtoH = Math.abs(B[i - 1] - B[i]); // H -> H

            int nextL = Math.max(L, H + HtoL);
            int nextH = Math.max(H + HtoH, L + LtoH);

            L = nextL;
            H = nextH;
        }
        return Math.max(L, H);
    }
}
