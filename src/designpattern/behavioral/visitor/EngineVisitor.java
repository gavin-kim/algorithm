package designpattern.behavioral.visitor;

public interface EngineVisitor {
    void visit(Camshaft camshaft);
    void visit(Engine engine);
    void visit(Piston piston);
    void visit(SparkPlug sparkPlug);
}
