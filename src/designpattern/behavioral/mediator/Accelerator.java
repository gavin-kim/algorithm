package designpattern.behavioral.mediator;

public class Accelerator {

    private EngineManagementSystem mediator;
    private boolean enabled;
    private int speed;

    public Accelerator(EngineManagementSystem mediator) {
        this.mediator = mediator;
        enabled = false;
        speed = 0;
        mediator.registerAccelerator(this);
    }

    public void enable() {
        enabled = true;
        mediator.acceleratorEnabled();
        System.out.println("Accelerator enabled");
    }

    public void disable() {
        enabled = false;
        mediator.acceleratorDisabled();
        System.out.println("Accelerator disabled");
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void accelerateToSpeed(int speed) {
        if (enabled) {
            this.speed = speed;
            mediator.acceleratorPressed();
            System.out.println("Speed now " + this.speed);
        }
    }

    public int getSpeed() {
        return speed;
    }
}
