package designpattern.structural.proxy;

public interface Engine {
    int getSize();
    boolean isTurbo();

    void diagnose(DiagnosticTool diagnosticTool);
}
