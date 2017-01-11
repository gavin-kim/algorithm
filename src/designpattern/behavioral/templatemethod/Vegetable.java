package designpattern.behavioral.templatemethod;

public abstract class Vegetable {
    private Weather weather = new Weather();

    /** Default method */
    public void grow() {
        digHole();
        plantSeeds();
        mulch();

        while (!weather.frost()) {
            water();
            prune();
        }
    }

    /** common method, most subclasses need */
    protected void digHole() {
        System.out.println("Dig hole");
    }
    protected void water() {
        System.out.println("Water plant");
    }

    /** many subclasses need it but implement uniquely */
    protected abstract void plantSeeds();

    /** An option method */
    protected void mulch() {}
    protected void prune() {}

    /** Delegate method available to subclasses */
    protected final boolean isWarmClimate() {
        return weather.isWarmClimate();
    }
}
