package sorting.string;

/**
 * LSD (Least Significant Digit first) sort for fixed length strings
 *
 * N = a number of words
 * L = a word length
 *
 * O (L * N)
 */
public class LSD {

    public static String[] sort(String[] arr, int wordLen) {

        int N = arr.length; // a length of array
        int R = 256;        // a range of character

        String[] result = new String[N];

        for (int w = wordLen - 1; w >= 0; w--) { //

            int[] count = new int[R];

            for (int i = 0; i < N; i++)
                count[arr[i].charAt(w)]++;

            for (int i = 1; i < R; i++)
                count[i] += count[i - 1];

            for (int i = N - 1; i >= 0; i--)
                result[--count[arr[i].charAt(w)]] = arr[i];
        }

        return result;
    }
}
