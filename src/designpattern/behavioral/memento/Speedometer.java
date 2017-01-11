package designpattern.behavioral.memento;

public class Speedometer {

    // private but has accessor method        : accessible with a getter method
    private int currentSpeed;

    // package-private and no accessor method : only accessible in the package
    int previousSpeed;

    public Speedometer() {
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
}
