package designpattern.behavioral.visitor;

public abstract class AbstractEngine implements Engine {
    private int size;
    private boolean turbo;

    private Camshaft camshaft;
    private Piston piston;
    private SparkPlug[] sparkPlugs;

    public AbstractEngine(int size, boolean turbo) {
        this.size = size;
        this.turbo = turbo;

        // Create a camshaft, piston and 4 spark plugs...
        camshaft = new Camshaft();
        piston = new Piston();
        sparkPlugs = new SparkPlug[]{
            new SparkPlug(), new SparkPlug(), new SparkPlug(), new SparkPlug()};
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isTurbo() {
        return turbo;
    }

    @Override // put Some Engine visitor (EngineDiagnostics or EngineInventory)
    public void acceptEngineVisitor(EngineVisitor visitor) {
        // Visit each component first
        camshaft.acceptEngineVisitor(visitor);
        piston.acceptEngineVisitor(visitor);
        for (SparkPlug sparkPlug : sparkPlugs) {
            sparkPlug.acceptEngineVisitor(visitor);
        }

        visitor.visit(this);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " (" + size + ")";
    }
}
