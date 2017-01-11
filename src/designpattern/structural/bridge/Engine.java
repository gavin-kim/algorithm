package designpattern.structural.bridge;

public interface Engine {
    int getSize();
    boolean isTurbo();

    void start();
    void stop();
    void increasePower();
    void decreasePower();
}
