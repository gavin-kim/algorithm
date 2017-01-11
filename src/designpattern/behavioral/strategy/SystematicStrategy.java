package designpattern.behavioral.strategy;

import java.util.Arrays;
import java.util.Random;

public class SystematicStrategy implements Strategy {

    @Override
    public int[] sample(int[] samples, int sampleSize) {

        int[] subset = new int[sampleSize];

        Random random = new Random();

        int[] sortedSamples = Arrays.copyOf(samples, sampleSize);

        Arrays.sort(sortedSamples);

        return sortedSamples;
    }
}
