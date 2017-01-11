package designpattern.structural.facade;

public class Shutter {
    private double speed;

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public void trigger() {
        System.out.printf("Open shutter for %.3f seconds%n", speed);
    }
}
