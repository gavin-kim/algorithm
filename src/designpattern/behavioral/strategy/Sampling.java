package designpattern.behavioral.strategy;

public class Sampling {

    private int[] samples;
    private Strategy strategy;

    public Sampling(int[] samples, Strategy strategy) {
        this.samples = samples;
        this.strategy = strategy;
    }

    public int[] selectSubset(int sampleSize) {
        return strategy.sample(samples, sampleSize);
    }
}
