package designpattern.behavioral.visitor;

public class EngineDiagnostics implements EngineVisitor {

    @Override
    public void visit(Camshaft camshaft) {
        System.out.println("Diagnosing the camshaft");
    }

    @Override
    public void visit(Engine engine) {
        System.out.println("Diagnosing the unit engine");
    }

    @Override
    public void visit(Piston piston) {
        System.out.println("Diagnosing the piston");
    }

    @Override
    public void visit(SparkPlug sparkPlug) {
        System.out.println("Diagnosing a single spark plug");
    }
}
