package designpattern.structural.bridge;

public class AbstractDriverControls {

    private Engine engine;

    public AbstractDriverControls(Engine engine) {
        this.engine = engine;
    }

    public void ingnitionOn() {
        engine.start();
    }

    public void ignitionOff() {
        engine.stop();
    }

    public void accelerate() {
        engine.increasePower();
    }

    public void brake() {
        engine.decreasePower();
    }
}
