package designpattern.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

public class EngineFactory {

    private Map<Integer, Engine> standardEnginePool;
    private Map<Integer, Engine> turboEnginePool;

    public EngineFactory() {
        standardEnginePool = new HashMap<>();
        turboEnginePool = new HashMap<>();
    }

    public Engine getStandardEngine(int size) {
        Engine engine = standardEnginePool.get(size);

        if (engine == null) {
            engine = new StandardEngine(size);
            standardEnginePool.put(size, engine);
        }

        return engine;
    }

    public Engine getTurboEngine(int size) {
        Engine engine = turboEnginePool.get(size);

        if (engine == null) {
            engine = new TurboEngine(size);
            turboEnginePool.put(size, engine);
        }
        return engine;
    }
}
