package designpattern.behavioral.strategy;

import java.util.ArrayList;
import java.util.Random;

public class RandomStrategy implements  Strategy {

    /** get random samples as many as sampleSize */
    @Override
    public int[] sample(int[] samples, int sampleSize) {

        int[] subset = new int[sampleSize];

        Random random = new Random();

        ArrayList<Integer> list = new ArrayList<>();

        for (int i : samples)
            list.add(samples[i]);

        for (int i = 0; i < sampleSize; i++)
            subset[i] = list.remove(random.nextInt(list.size()));

        return subset;
    }
}
