package testing;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class Test {

    public static final int SIZE = 10000000;
    public static final int RANGE = 10000;

    public static void main(String[] args) throws IOException{

        Scanner input = new Scanner(new File("resources/data/data.txt"));
        int count = 0;

        int len = input.nextInt();
        int p = input.nextInt();

        int[] arr = new int[len];
        int[] counts = new int[201];

        LinkedList<Integer> queue = new LinkedList<>();


        for (int i = 0; i < len; i++) {
            arr[i] = input.nextInt();


            if (i >= p && arr[i] >= getMedian(counts, p))
                count++;

            queue.addLast(arr[i]);
            counts[arr[i]]++;

            if (queue.size() > p)
                counts[queue.poll()]--;


        }
        System.out.println(count);
    }


    public static int getMedian(int[] counts, int p) {

        int center = p / 2;
        int i = 0;
        int sum = counts[0];
        int range = 0;

        // sum <= center < next sum
        while (i < counts.length - 1 && sum <= center) {
            range = center - sum;
            sum += counts[++i];
        }

        System.out.println(range);

        if (p % 2 == 0 && range < 1) {

            int median = i;
            while (counts[--i] == 0);
            return median + i;
        }
        else
            return i * 2;
    }
}
