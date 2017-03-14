package designpattern.behavioral.visitor;

/**
 *  Purpose: Visitor lets you define a new method
 *           without changing the classes of components (open/closed principle)
 *
 *                Visitable ------------------>  interface Visitable {
 *                    |                              void acceptVisitor(Visitor visitor);
 *      -----------------------------            }
 *      |        |        |         |
 *    Engine  Camshaft  Piston  SparkPlug
 *      |
 *    AbstractEngine
 *      |        |
 *    Standard Turbo
 *    ------------------------------------------------
 *    | @Override                                    |
 *    | public void acceptVisitor(Visitor visitor) { |
 *    |     visitor.action(this);                    |
 *    | }                                            |
 *    ------------------------------------------------
 *
 *    public interface Visitor {          * Visitor has actions for each component
 *        void action(Camshaft camshaft);
 *        void action(Engine engine);
 *        void action(Piston piston);
 *        void action(SparkPlug sparkPlug);
 *    }
 */

public class Example {

    public static void main(String[] args) {
        Engine engine = new StandardEngine(1300);

        /**
         * engine has components (Camshaft, Piston, SparkPlug and Engine(itself))
         *
         * When EngineVisitor visits, engine invoke visit(component) for each component
         */
        engine.acceptEngineVisitor(new EngineVisitor() {
               @Override
               public void visit(Camshaft camshaft) {}
               @Override
               public void visit(Engine engine) {}
               @Override
               public void visit(Piston piston) {}
               @Override
               public void visit(SparkPlug sparkPlug) {}
           }
        );

        // EngineDiagnostics object to diagnose each component
        engine.acceptEngineVisitor(new EngineDiagnostics());

        // EngineInventory object to count each component
        engine.acceptEngineVisitor(new EngineInventory());
    }
}
