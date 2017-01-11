package designpattern.behavioral.mediator;

public class Gearbox {

    public enum Gear {
        NEUTRAL,
        FIRST,
        SECOND,
        THIRD,
        FOURTH,
        FIFTH,
        REVERSE
    }

    private EngineManagementSystem mediator;
    private boolean enabled;
    private Gear gear;

    public Gearbox(EngineManagementSystem mediator) {
        this.mediator = mediator;
        enabled = false;
        gear = Gear.NEUTRAL;
        mediator.registerGearbox(this);
    }

    public void enable() {
        enabled = true;
        mediator.gearboxEnabled();
        System.out.println("Gearbox enabled");
    }

    public void disable() {
        enabled = false;
        mediator.gearboxDisabled();
        System.out.println("Gearbox disabled");
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setGear(Gear gear) {
        if (enabled && (this.gear != gear)) {
            this.gear = gear;
            mediator.gearChanged();
            System.out.println("Now in " + getGear() + " gear");
        }
    }

    public Gear getGear() {
        return gear;
    }
}
