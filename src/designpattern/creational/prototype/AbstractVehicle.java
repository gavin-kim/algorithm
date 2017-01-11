package designpattern.creational.prototype;

public class AbstractVehicle implements Vehicle {

    private Engine engine;
    private Color color;

    public AbstractVehicle() {}

    public AbstractVehicle(Engine engine) {
        this(engine, Color.UNPAINTED);
    }

    public AbstractVehicle(Engine engine, Color color) {
        this.engine = engine;
        this.color = color;

        // Do something...
    }

    @Override
    public Engine getEngine() {
        return engine;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void paint(Color color) {
        this.color = color;
    }

    @Override
    public Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        return obj;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " (" + engine + ", " + color + ")";
    }
}
