package designpattern.behavioral.strategy;

/**
 * Strategy: Strategy is a synonym for algorithm or behavior
 *
 * According to situations, algorithms vary at runtime
 *
 * All concrete strategy class must implement the same interface
 *
 * (e.g. Comparator interface)
 *
 */
public class Example {

    public static void main(String[] args) {
        int[] zipCodes =
            {66290, 64113, 10162, 90210, 61701, 55901, 48823, 62901, 50014};

        Strategy strategy = new RandomStrategy();

        Sampling sampling = new Sampling(zipCodes, strategy);

        int[] sample = sampling.selectSubset(3);

        strategy = new SystematicStrategy();

        Sampling sampling2 = new Sampling(zipCodes, strategy);

        int[] sample2 = sampling2.selectSubset(3);
    }
}
