package designpattern.structural.bridge;

public class SportControls extends AbstractDriverControls {

    public SportControls(Engine engine) {
        super(engine);
    }

    public void accelerateHard() {
        accelerate();
        accelerate();
    }
}
