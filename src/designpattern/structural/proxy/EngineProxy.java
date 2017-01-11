package designpattern.structural.proxy;

public class EngineProxy implements Engine {

    private Engine engine;

    public EngineProxy(Engine engine) {
        this.engine = engine;
    }

    public EngineProxy(int size, boolean turbo) {
        if (turbo)
            engine = new TurboEngine(size);
        else
            engine = new StandardEngine(size);
    }

    @Override
    public int getSize() {
        return engine.getSize();
    }

    @Override
    public boolean isTurbo() {
        return engine.isTurbo();
    }

    @Override
    public void diagnose(DiagnosticTool diagnosticTool) {
        new Thread(() -> {
            System.out.println("Running tool as thread");
            engine.diagnose(diagnosticTool);
            System.out.println("EngineProxy diagnose() method finished");
        }).start();
    }
}
