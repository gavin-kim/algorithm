package designpattern.behavioral.visitor;

public class Camshaft implements Visitable {
    @Override
    public void acceptEngineVisitor(EngineVisitor visitor) {
        visitor.visit(this);
    }
}
