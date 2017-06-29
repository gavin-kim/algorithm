package dynamic;

import java.util.stream.LongStream;

/**
 * Calculate a sum of substrings
 *
 * For example, the sub-strings of 123 are 1, 2, 3, 12, 23, 123 which sums to 164
 *
 * Time complexity
 *
 * Brutal Force = O(n^2)
 * Dynamic Programming = O(n)
 *
 * 1                   a                                    = a
 * 2, 12               b 10a+b                              = 10a + 2b
 * 3, 23, 123          c 10b+c 100a+10b+c                   = 100a + 20b + 3c
 * 4, 23, 234, 1234    d 10c+d 100b+10c+d 1000a+100b+10c+d  = 1000a + 200b + 30c + 4d
 * ...                 ...                                n = (n-1)*10 + n*n
 *
 */
public class SumOfSubstrings {
    public static long solve(String str) {
        int len = str.length();
        long[] sums = new long[len];
        sums[0] = str.charAt(0) - '0';

        for (int i = 1; i < len; i++) {
            int digit = str.charAt(i) - '0';
            sums[i] = sums[i - 1] * 10 + (i + 1) * digit;
        }

        return LongStream.of(sums).sum();
    }
}
