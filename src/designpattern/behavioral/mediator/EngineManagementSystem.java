package designpattern.behavioral.mediator;

public class EngineManagementSystem {

    private Ignition ignition;
    private Gearbox gearbox;
    private Accelerator accelerator;
    private Brake brake;

    private int speed = 0;

    public EngineManagementSystem() {}

    // Methods that enable registration with this mediator...

    public void registerIgnition(Ignition ignition) {
        this.ignition = ignition;
    }

    public void registerGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    public void registerAccelerator(Accelerator accelerator) {
        this.accelerator = accelerator;
    }

    public void registerBrake(Brake brake) {
        this.brake = brake;
    }

    // Methods that handle object interactions...

    public void ignitionTurnedOn() {
        gearbox.enable();
        accelerator.enable();
        brake.enable();
    }

    public void ignitionTurnedOff() {
        brake.disable();
        accelerator.disable();
        gearbox.disable();
    }

    public void gearboxEnabled() {
        System.out.println("EMS now controlling the gearbox");
    }

    public void gearboxDisabled() {
        System.out.println("EMS no longer controlling the gearbox");
    }

    public void gearChanged() {
        System.out.println("EMS disengaging revs while gear changing");
    }

    public void acceleratorEnabled() {
        System.out.println("EMS now controlling the accelerator");
    }

    public void acceleratorDisabled() {
        System.out.println("EMS no longer controlling the accelerator");
    }


    public void acceleratorPressed() {
        brake.disable();

        while (speed < accelerator.getSpeed()) {
            speed++;

            System.out.println("Speed currently " + speed);

            if (speed <= 10)
                gearbox.setGear(Gearbox.Gear.FIRST);
            else if (speed <= 20)
                gearbox.setGear(Gearbox.Gear.SECOND);
            else if (speed <= 30)
                gearbox.setGear(Gearbox.Gear.THIRD);
            else if (speed <= 50)
                gearbox.setGear(Gearbox.Gear.FOURTH);
            else
                gearbox.setGear(Gearbox.Gear.FIFTH);
        }

        brake.enable();
    }

    public void brakeEnabled() {
        System.out.println("EMS now controlling the brakes");
    }

    public void brakeDisabled() {
        System.out.println("EMS no longer controlling the brakes");
    }

    public void brakePressed() {
        accelerator.disable();
        speed = 0;
    }

    public void brakeReleased() {
        gearbox.setGear(Gearbox.Gear.FIRST);
        accelerator.enable();
    }

}
