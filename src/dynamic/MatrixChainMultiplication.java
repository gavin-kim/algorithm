package dynamic;

import java.util.*;

/**
 * ** Matrix Chain Multiplication
 *
 *
 * To determine an order for multiplying matrices. Not actually multiplying.
 *
 * ((A1 A2) A3) => ((10x100 100x5) 5x50): (10*100*5) + (10*5*50) = 7500
 * (A1 (A2 A3)) => (10x100 (100x5 5x50)): (10*100*50) + (100*5*50) = 75000
 *
 *
 * 5 distinct ways to fully parenthesize for {A1, A2, A3, A4}
 *
 * (A1 (A2 (A3 A4))), (A1 ((A2 A3) A4)), ((A1 A2) (A3 A4)),
 * ((A1 (A2 A3)) A4), (((A1 A2) A3) A4)
 *
 *
 * Step 1: The structure of an optimal parenthesizing.
 *
 * m[i,j]: the minimum cost of parenthesizing Ai Ai+1 ... Aj
 * m[i,i]: Ai = 0 (trivial)
 * p[] = dimensions (e.g. Ai = p-1 x p matrix)
 *
 *
 * Step 2: A recursive solution.
 *
 * m[i,j] = { 0                                          } if i = j
 *          { i <= k < j (m[i,k] + m[k+1,j] + p-1*pk*pj) } if i < j
 *
 * s[i,j] = k
 *
 *
 * Step 3: Computing the optimal costs
 *
 * m[] (i <= j)
 * -------------------------
 * |i\j| 1 | 2 | 3 | 4 | 5 |
 * -------------------------
 * | 1 | 0 |   |   |   |  <--- for m[1,5] = {A1 A2 A3 A4 A5}
 * -------------------------            p [p0 p1 p2 p3 p4 p5] dimensions
 * | 2 | - | 0 |   |   |   |
 * -------------------------   A length of chain starts from 2 to n
 * | 3 | - | - | 0 |   |   |   e.g. m[1,2] = min (m[1,1] + m[2,2] + p0*p1*p2)
 * -------------------------   e.g. m[2,4] = min (m[2,2] + m[3,4] + p1*p3*p4, k2
 * | 4 | - | - | - | 0 |   |                      m[2,3] + m[4,4] + p1*p2*p4) k3
 * -------------------------
 * | 5 | - | - | - | - | 0 |
 * -------------------------
 *
 *  [1,2] [2,3] [3,4] [4,5]  len:2
 *     [1,3] [2,4] [3,5]     len:3 (i:1,j:3,k:1) => m[1,1] + m[2,3] + p0*p1*p3
 *        [1,4] [2,5]        len:4
 *           [1,5]           len:5
 *
 *
 * Time complexity: O(n^3)
 * Space complexity: O(n^2)
 */
public class MatrixChainMultiplication {

    public static final int[] DIMENSIONS = {
        30, 35, 15, 5, 10, 20, 25
    };


    /**
     * @param p dimensions of each matrix
     * @return
     */
    public static int[][] solve(int[] p) {
        int n = p.length - 1;              // table dimension

        int[][] m = new int[n + 1][n + 1]; // minimum matrix chain multiplication
        int[][] s = new int[n + 1][n + 1]; // store index k

        for (int len = 2; len <= n; len++) { // chain length = i~j
            for (int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) { // k: i <= k < j
                    int product = m[i][k] + m[k+1][j] + p[i-1] * p[k] * p[j];
                    if (product < m[i][j]) {  // store smaller product
                        m[i][j] = product;
                        s[i][j] = k;
                    }
                }
            }
        }

        System.out.println(printOptimalCombination(s, 1, n));
        return m;
    }

    private static String printOptimalCombination(int[][] s, int i, int j) {

        if (i == j)
            return "A" + i;
        else
            return "(" + printOptimalCombination(s, i, s[i][j]) +
                        printOptimalCombination(s, s[i][j] + 1, j) + ")";
    }
}
