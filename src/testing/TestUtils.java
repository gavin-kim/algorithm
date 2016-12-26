package testing;

import javafx.scene.paint.Stop;

import java.util.Random;

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
            long time = stopwatch.elapsedTime();

            System.out.printf("%12d %12d %10d %10.2f%%%n", size, time,
                time - lastTime, (double)time / lastTime * 100);

            lastTime = time;
        }
    }

    public static long simpleTest(Testable testable, int size, int range) {
        Random random = new Random();
        int[] arr = random.ints(size, -range, range).toArray();

        Stopwatch stopwatch = new Stopwatch();
        testable.test(arr);
        return stopwatch.elapsedTime();
    }
}