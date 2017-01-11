package designpattern.behavioral.memento;

public class SpeedometerMemento {

    private Speedometer speedometer;

    private int copyOfCurrentSpeed;
    private int copyOfPreviousSpeed;

    public SpeedometerMemento(Speedometer speedometer) {
        this.speedometer = speedometer;
        copyOfCurrentSpeed = speedometer.getCurrentSpeed();
        copyOfPreviousSpeed = speedometer.previousSpeed;
    }

    public void restoreState() {
        speedometer.setCurrentSpeed(copyOfCurrentSpeed);
        speedometer.previousSpeed = copyOfPreviousSpeed;
    }
}
