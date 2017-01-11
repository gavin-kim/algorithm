package designpattern.behavioral.memento;

import java.io.Serializable;

public class Speedometer2 implements Serializable {

    private static final long serialVersionUID = -2432494850134543344L;

    // all private fields
    private int currentSpeed;
    private int previousSpeed;

    public Speedometer2() {
        currentSpeed = 0;
        previousSpeed = 0;
    }

    public void setCurrentSpeed(int speed) {
        previousSpeed = currentSpeed;
        currentSpeed = speed;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    // Only defined to test
    public int getPreviousSpeed() {
        return previousSpeed;
    }
}