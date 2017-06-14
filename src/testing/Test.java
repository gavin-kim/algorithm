package testing;

import dynamic.Equal;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Test {

    private static final int ARRAY_SIZE = 100;
    private static final int RANGE = 1000;
    private static final int BUCKET_SIZE = 10;
    private static final int RADIX_SIZE = 10;
    private static final String RESOURCE_ROOT = "resources/data/";

    public static void main(String[] args) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        int[] memo = Equal.createMemo(1200, new int[]{1, 2, 5});

        Scanner input = new Scanner(new File("resources/data/ex1.txt"));
        Scanner input2 = new Scanner(new File("resources/data/ex2.txt"));
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            int N = input.nextInt();
            int[] chocolates = new int[N];

            for (int j = 0; j < N; j++)
                chocolates[j] = input.nextInt();

            System.out.println(Equal.countOperations(chocolates, memo) + ", " + input2.next());
        }
    }

}



