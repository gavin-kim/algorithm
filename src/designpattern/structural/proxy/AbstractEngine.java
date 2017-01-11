package designpattern.structural.proxy;

public abstract class AbstractEngine implements Engine {
    private int size;
    private boolean turbo;
    private boolean running;
    private int power;

    public AbstractEngine(int size, boolean turbo) {
        this.size = size;
        this.turbo = turbo;
        running = false;
        power = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isTurbo() {
        return turbo;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " (" + size  + ")";
    }

    @Override
    public void diagnose(DiagnosticTool diagnosticTool) {
        diagnosticTool.runDiagnosis(this);
    }
}
