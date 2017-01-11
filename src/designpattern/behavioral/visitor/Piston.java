package designpattern.behavioral.visitor;

public class Piston implements Visitable {
   @Override
    public void acceptEngineVisitor(EngineVisitor visitor) {
        visitor.visit(this);
    }
}
