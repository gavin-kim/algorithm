package designpattern.behavioral.visitor;

public class SparkPlug implements Visitable {
    @Override
    public void acceptEngineVisitor(EngineVisitor visitor) {
        visitor.visit(this);
    }
}
