package designpattern.behavioral.visitor;

/**
 *  Purpose: Visitor lets you define a new method
 *           without changing the classes of components
 *
 *              Engine                     * Visitor visits each components including Engine itself
 *                 |                                 has actions for each components
 *           AbstractEngine ------------>  AbstractEngine.acceptVisitor(Visitor visitor)
 *           |            |                -Camshaft  -> acceptVisitor(visitor); --> visitor.action(this);
 *    StandardEngine TurboEngine           -Piston    -> acceptVisitor(visitor); --> visitor.action(this);
 *                                         -SparkPlug -> acceptVisitor(visitor); --> visitor.action(this);
 *                                         -Engine    -> visitor.action(this);
 *
 *                Visitable
 *      |        |        |         |
 *    Engine  Camshaft  Piston  SparkPlug
 *
 *    public interface Visitable {              * interface Visitable
 *        void acceptVisitor(Visitor visitor);  * Visitor can visit the visitable component
 *    }
 *
 *    @Override
 *    public void acceptVisitor(Visitor visitor) {  * Visitable component invokes action that the Visitor offers
 *        visitor.action(this);
 *    }
 *
 *    public interface EngineVisitor {          * Visitor has actions for each component
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
