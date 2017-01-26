package testing;

import java.time.LocalDateTime;

public class Stopwatch {

    private LocalDateTime localDateTime;
    private long startTime;
    private long stopTime;

    public Stopwatch() {
        localDateTime = LocalDateTime.now();
        startTime = System.nanoTime();
    }

    public void stop() {
        stopTime = System.nanoTime();
    }

    public long getMilliSeconds() {
        return (System.nanoTime() - startTime) / 1000000;
    }

    public long getMicroSeconds() {
        return (System.nanoTime() - startTime) / 1000;
    }

    public long getNanoSeconds() {
        return (System.nanoTime() - startTime);
    }

    @Override
    public String toString() {
        return localDateTime.toString();
    }
}
