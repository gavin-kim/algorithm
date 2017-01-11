package designpattern.behavioral.visitor;

public class EngineInventory implements EngineVisitor {

    private int camshaftCount;
    private int pistonCount;
    private int sparkPlugCount;

    public EngineInventory() {
    }

    @Override
    public void visit(Camshaft camshaft) {
        camshaftCount++;
    }

    @Override
    public void visit(Engine engine) {
        StringBuilder builder = new StringBuilder("The engine has: ")
            .append(camshaftCount).append(" camshaft(s), ")
            .append(pistonCount).append(" piston(s), ")
            .append(sparkPlugCount).append(" spark plug(s)");

        System.out.println(builder);
    }

    @Override
    public void visit(Piston piston) {
        pistonCount++;
    }

    @Override
    public void visit(SparkPlug sparkPlug) {
        sparkPlugCount++;
    }
}
