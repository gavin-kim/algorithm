package testing;


import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

public class TestUtils {

    // doubling size test 250, 500, 1000, 2000 ...
    public static void doublingTest(Testable testable, int range) {

        Random random = new Random();
        long lastTime = 0;

        System.out.printf("%12s %12s %10s %10s%n", "Units", "Time(ms)", "Increment", "Rate");
        System.out.println("---------------------------------------------------------------");

        for (int size = 250; true; size <<= 1) {
            int[] arr = random.ints(size, -range, range).toArray();

            Stopwatch stopwatch = new Stopwatch();
            testable.test(arr);
            long time = stopwatch.getNanoSeconds();

            System.out.printf("%12d %12d %10d %10.2f%%%n", size, time,
                time - lastTime, (double)time / lastTime * 100);

            lastTime = time;
        }
    }

    public static long runningTimeTest(Testable testable, int arraySize, int range, boolean print) {
        Random random = new Random();
        int[] arr = random.ints(arraySize, 0, range).toArray();

        Stopwatch stopwatch = new Stopwatch();
        testable.test(arr);

        if (print)
            printArray(arr);

        return stopwatch.getNanoSeconds();
    }

    public static void printArray(int[] arr) {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {

            if (i % 20 == 0)
                builder.append('\n');

            builder.append(arr[i]).append(' ');

        }

        System.out.println(builder);
    }

    private static final String AB =
        "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static SecureRandom random = new SecureRandom();

    public static String getRandomString(int length) {
        char[] letters = new char[length];

        for (int i = 0; i < length; i++)
            letters[i] = AB.charAt(random.nextInt(AB.length()));

        return String.valueOf(letters);
    }
}
