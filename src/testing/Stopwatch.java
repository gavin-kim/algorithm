package testing;

public class Stopwatch {

    private long startTime;

    public Stopwatch() {
        startTime = System.nanoTime();
    }

    public long elapsedTime() {
        return (System.nanoTime() - startTime) / 1000;
    }
}
