package designpattern.structural.flyweight;

/**
 * Purpose: Use sharing to support large numbers of fine-grained objects efficiently
 *
 * Reference: java.lang.Integer.valueOf()
 *
 * Flyweight Factory caches a new object and before it returns an object
 * checks whether there is the same object that can be shared of not in the cache
 * then return existing or new one
 *
 */

public class Example {
    public static void main(String[] args) {

        EngineFactory factory = new EngineFactory();

        // get engines with the same value(cc)
        Engine engine1 = factory.getStandardEngine(2000);
        Engine engine2 = factory.getStandardEngine(2000);
        Engine engine3 = factory.getStandardEngine(2000);

        // with different values(cc)
        Engine engine4 = factory.getStandardEngine(2500);
        Engine engine5 = factory.getStandardEngine(3000);

        System.out.println(engine1.hashCode());
        System.out.println(engine2.hashCode());
        System.out.println(engine3.hashCode());
        System.out.println(engine4.hashCode());
        System.out.println(engine5.hashCode());
    }
}
